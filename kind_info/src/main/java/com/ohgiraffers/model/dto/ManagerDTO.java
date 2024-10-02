package com.ohgiraffers.model.dto;

public class ManagerDTO {
    private int managerCode;
    private String managerName;
    private String managerBirth;
    private String managerGrade;
    private String managerPhone;
    private String managerOff;

    public ManagerDTO(){}

    public ManagerDTO(int managerCode, String managerName, String managerBirth, String managerGrade, String managerPhone, String managerOff) {
        this.managerCode = managerCode;
        this.managerName = managerName;
        this.managerBirth = managerBirth;
        this.managerGrade = managerGrade;
        this.managerPhone = managerPhone;
        this.managerOff = managerOff;
    }

    public int getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(int managerCode) {
        this.managerCode = managerCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerBirth() {
        return managerBirth;
    }

    public void setManagerBirth(String managerBirth) {
        this.managerBirth = managerBirth;
    }

    public String getManagerGrade() {
        return managerGrade;
    }

    public void setManagerGrade(String managerGrade) {
        this.managerGrade = managerGrade;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerOff() {
        return managerOff;
    }

    public void setManagerOff(String managerOff) {
        this.managerOff = managerOff;
    }

    @Override
    public String toString() {
        return "ManagerDTO{" +
                "managerCode=" + managerCode +
                ", managerName='" + managerName + '\'' +
                ", managerBirth='" + managerBirth + '\'' +
                ", managerGrade='" + managerGrade + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", managerOff='" + managerOff + '\'' +
                '}';
    }
}
