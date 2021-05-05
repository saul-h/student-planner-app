package com.gseven.studentplanner.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.gseven.studentplanner.DegreeTrackerActivity;
import com.gseven.studentplanner.MainMenuActivity;
import com.gseven.studentplanner.R;
import com.gseven.studentplanner.data.database.AppDatabase;
import com.gseven.studentplanner.data.entities.User;
import com.gseven.studentplanner.ui.login.LoginViewModel;
import com.gseven.studentplanner.ui.login.LoginViewModelFactory;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private LoginViewModel loginViewModel;
    //TEST


    public static String global_userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final SignInButton gSignInButton = findViewById(R.id.google_sign_in_button);
        gSignInButton.setSize(SignInButton.SIZE_STANDARD);

        /*
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AppDatabase")
                .allowMainThreadQueries()
                .build();
        */
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        populateWithTestData(db);


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                //validate login
                boolean ok = validate(usernameEditText,passwordEditText);
                if (ok == true){
                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                    startActivity(intent);
                }
                else{

                }
                //loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        gSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();

                // startActivity(signInIntent);
                // TODO: implement startActivityForResult method
                // String welcome = getString(R.string.welcome) + model.getDisplayName();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    public boolean validate(EditText input_email, EditText input_password) {
        boolean valid = false;
        boolean hash_password = false;
        boolean go = false;

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 14) {
            input_password.setError("between 4 and 14 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
        }

        AppDatabase this_db = AppDatabase.getDBInstance(this.getApplicationContext());
        List<User> this_user_list = this_db.userDao().load_user_list();

        for (int i = 0; i < this_user_list.size(); i++)
        {
            String this_user_email = this_user_list.get(i).getEmail();
            String this_user_password = this_user_list.get(i).getPassword();
            String this_user_uid =  this_user_list.get(i).getUserID();

            System.out.println("fired from database user=" + this_user_email + ", pss= "+ this_user_password +", UserID ="+ this_user_uid);
            System.out.println("email =" + email + ", " + password);


            if ( this_user_email.equals(email) ) {
                global_userID = this_user_uid;
                System.out.println("Fired 2");
                hash_password = true;
                if(hash_password == true){
                   if (this_user_password.equals(password)){
                       valid = true;
                   }
                }
                break;
            }
        }


        return valid;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUiWithUser(account.getDisplayName());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // TODO: implement The following code
            // Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(getApplicationContext(), "Failed to login with Google", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // TODO: Hide sign-in button

        try {
            updateUiWithUser(account.getDisplayName());
        } catch (NullPointerException e) {
            // User has not signed in with a google account
        }

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();

        // Display the MainMenuActivity screen
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void updateUiWithUser(String username) {
        String welcome = getString(R.string.welcome) + username;

        // Display the MainMenuActivity screen
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private static User addUser(final AppDatabase db, User user) {
        db.userDao().insertUsers(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db) {

        User user2;
        User user = new User();
        user.firstName = "Tom";
        user.lastName = "Rossu";
        user.email = "tom@rossu.com";
        user.uid = 123456789;
        user.userID ="123456789";
        user.setPassword("tom123456789");

        addUser(db, user);

        /*
        user2 = db.userDao().getUser("123456789");
        System.out.println("Shitt" + user2.getFirstName());
        System.out.println("Shitt" + user2.getEmail());
        System.out.println("Shitt" + user2.getUserID());
        System.out.println("Shitt" + user2.getPassword());
        */

    }
}