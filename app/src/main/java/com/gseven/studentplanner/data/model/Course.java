package com.gseven.studentplanner.data.model;

import java.io.Serializable;


/**
 * Course model class used to store Courses information for User
 */
public class Course implements Serializable {

    private String courseName;
    private int units;
    private String status;
    private String semester;
    private char grade;

    /**
     * Constructor for when Course grade is know at creation
     * @param courseName Name of the Course
     * @param units Units count for Course
     * @param status Current status of Course
     * @param semester Semester Course is planned or completed
     * @param grade Grade received for Course
     */
    public Course(String courseName, int units, String status, String semester, char grade) {
        this.courseName = courseName;
        this.units = units;
        this.status = status;
        this.semester = semester;
        this.grade = grade;
    }

    /**
     * Constructor for when Course grade is NOT known at creation
     * @param courseName Name of the Course
     * @param units Units count for Course
     * @param status Current status of Course
     * @param semester Semester Course is planned or completed
     */
    public Course(String courseName, int units, String status, String semester) {
        this.courseName = courseName;
        this.units = units;
        this.status = status;
        this.semester = semester;
        this.grade = '\0';
    }

    /**
     * Getters/Setters for all Course class attributes
     *
     */

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", units=" + units +
                ", status='" + status + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseName.equals(course.courseName);
    }


}
