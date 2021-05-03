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

    @Query("SELECT uid, first_name, last_name, email FROM user_table")
    public List<User> loadFullName();


}

