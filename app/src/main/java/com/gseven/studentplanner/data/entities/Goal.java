package com.gseven.studentplanner.data.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Goal {
    @PrimaryKey(autoGenerate = true)
    public int gid;

    @ColumnInfo(name = "name")
    public String name; // Goal

    @ColumnInfo(name = "description")
    public String description; // Optional description

    @ColumnInfo(name = "current_progress")
    public int currentProgress;

    @ColumnInfo(name = "total_needed")
    public int totalNeeded;

    @ColumnInfo(name = "completed")
    public boolean completed;
}
