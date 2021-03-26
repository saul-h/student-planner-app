package com.gseven.studentplanner.data.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Goal {
    @PrimaryKey
    public int gid; // Goal ID

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "target_comp_date")
    public Date targetCompDate;

    @ColumnInfo(name = "date_completed")
    public Date dateCompleted;

    @ColumnInfo(name = "current_progress")
    public double current_progress;
}
