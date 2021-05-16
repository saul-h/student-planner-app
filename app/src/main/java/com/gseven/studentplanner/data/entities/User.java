package com.gseven.studentplanner.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Defines the database fields for the object User.
 */
@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "password")
    private String password;
}
