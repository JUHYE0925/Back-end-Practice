package com.ohgiraffers.model.dto;

public class TeacherDTO {
    private int teacherId;
    private String teacherName;
    private String teacherGrade;
    private String teacherClass;
    private String teacherBirth;
    private String teacherPhone;
    private int teacherSalary;
    private String teacherOff;

    public TeacherDTO() {}

    public TeacherDTO(int teacherId, String teacherName, String teacherGrade, String teacherClass, String teacherBirth, String teacherPhone, int teacherSalary, String teacherOff) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherGrade = teacherGrade;
        this.teacherClass = teacherClass;
        this.teacherBirth = teacherBirth;
        this.teacherPhone = teacherPhone;
        this.teacherSalary = teacherSalary;
        this.teacherOff = teacherOff;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherGrade() {
        return teacherGrade;
    }

    public void setTeacherGrade(String teacherGrade) {
        this.teacherGrade = teacherGrade;
    }

    public String getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(String teacherClass) {
        this.teacherClass = teacherClass;
    }

    public String getTeacherBirth() {
        return teacherBirth;
    }

    public void setTeacherBirth(String teacherBirth) {
        this.teacherBirth = teacherBirth;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public int getTeacherSalary() {
        return teacherSalary;
    }

    public void setTeacherSalary(int teacherSalary) {
        this.teacherSalary = teacherSalary;
    }

    public String getTeacherOff() {
        return teacherOff;
    }

    public void setTeacherOff(String teacherOff) {
        this.teacherOff = teacherOff;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherGrade='" + teacherGrade + '\'' +
                ", teacherClass='" + teacherClass + '\'' +
                ", teacherBirth='" + teacherBirth + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", teacherSalary=" + teacherSalary +
                ", teacherOff='" + teacherOff + '\'' +
                '}';
    }
}
