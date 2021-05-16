package com.gseven.studentplanner.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Defines the database fields for the object User.
 */
@Entity
public class Goal {
    @PrimaryKey(autoGenerate = true)
    public int gid;

    @ColumnInfo(name = "name")
    public String name;

    /**
     * Optional description for goal
     */
    @ColumnInfo(name = "description")
    public String description;

    /**
     * Used to denote amount of progress that's been made
     * with regard to the specific goal.
     */
    @ColumnInfo(name = "current_progress")
    public int currentProgress;

    /**
     * This field is used to define the amount of progress needed
     * to complete a certain goal.
     *
     * Default value is 1, but can be any positive int to define a
     * goal such as "Run 10 miles" or "Submit 5 job applications"
     */
    @ColumnInfo(name = "total_needed")
    public int totalNeeded;

    @ColumnInfo(name = "completed")
    public boolean completed;
}
