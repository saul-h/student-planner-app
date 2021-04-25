package com.gseven.studentplanner.data.database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gseven.studentplanner.data.dao.UserDao;
import com.gseven.studentplanner.data.entities.User;


@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase{
    public abstract UserDao userDao();
}
