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
/**
    @ColumnInfo(name = "date_created")
    public Date dateCreated;

    @ColumnInfo(name = "target_comp_date")
    public Date targetCompDate;

    @ColumnInfo(name = "date_completed")
    public Date dateCompleted;
*/
    @ColumnInfo(name = "current_progress")
    public int currentProgress;

    @ColumnInfo(name = "completed")
    public boolean completed;
}
