package com.ohgiraffers.model.dto;

public class KidsDTO {

    private int kidsId;
    private String kidsName;
    private String kidsBirth;
    private String kidsClass;
    private String kidsAdress;
    private String KidsGender;
    private String parentsPhone;
    private String kidsSignificant;

    public KidsDTO(){}

    public KidsDTO(int kidsId, String kidsName, String kidsBirth, String kidsClass, String kidsAdress, String kidsGender, String parentsPhone, String kidsSignificant) {
        this.kidsId = kidsId;
        this.kidsName = kidsName;
        this.kidsBirth = kidsBirth;
        this.kidsClass = kidsClass;
        this.kidsAdress = kidsAdress;
        KidsGender = kidsGender;
        this.parentsPhone = parentsPhone;
        this.kidsSignificant = kidsSignificant;
    }

    public int getKidsId() {
        return kidsId;
    }

    public void setKidsId(int kidsId) {
        this.kidsId = kidsId;
    }

    public String getKidsName() {
        return kidsName;
    }

    public void setKidsName(String kidsName) {
        this.kidsName = kidsName;
    }

    public String getKidsBirth() {
        return kidsBirth;
    }

    public void setKidsBirth(String kidsBirth) {
        this.kidsBirth = kidsBirth;
    }

    public String getKidsClass() {
        return kidsClass;
    }

    public void setKidsClass(String kidsClass) {
        this.kidsClass = kidsClass;
    }

    public String getKidsAdress() {
        return kidsAdress;
    }

    public void setKidsAdress(String kidsAdress) {
        this.kidsAdress = kidsAdress;
    }

    public String getKidsGender() {
        return KidsGender;
    }

    public void setKidsGender(String kidsGender) {
        KidsGender = kidsGender;
    }

    public String getParentsPhone() {
        return parentsPhone;
    }

    public void setParentsPhone(String parentsPhone) {
        this.parentsPhone = parentsPhone;
    }

    public String getKidsSignificant() {
        return kidsSignificant;
    }

    public void setKidsSignificant(String kidsSignificant) {
        this.kidsSignificant = kidsSignificant;
    }

    @Override
    public String toString() {
        return "KindergartenDTO{" +
                "kidsId=" + kidsId +
                ", kidsName='" + kidsName + '\'' +
                ", kidsBirth='" + kidsBirth + '\'' +
                ", kidsClass='" + kidsClass + '\'' +
                ", kidsAdress='" + kidsAdress + '\'' +
                ", KidsGender='" + KidsGender + '\'' +
                ", parentsPhone='" + parentsPhone + '\'' +
                ", kidsSignificant='" + kidsSignificant + '\'' +
                '}';
    }
}
