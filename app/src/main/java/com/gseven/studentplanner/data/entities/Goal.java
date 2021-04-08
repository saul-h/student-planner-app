package com.gseven.studentplanner.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Goal {
    @PrimaryKey(autoGenerate = true)
    public int gid;

    @ColumnInfo(name = "goal_name")
    public String goalName;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "is_complete")
    public boolean isComplete;

    @ColumnInfo(name = "date_completed")
    public String dateCompleted;
}
