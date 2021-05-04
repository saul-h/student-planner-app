package com.gseven.studentplanner.data.daos;


//import androidx.room.Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gseven.studentplanner.data.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User...users);

    @Query("SELECT * FROM user_table")
    public List<User> load_user_list();

    @Query("Select * FROM user_table where uid = :input_uid")
    public User getUser(String input_uid);


}

