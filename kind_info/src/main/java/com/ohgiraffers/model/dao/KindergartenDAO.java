package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.KidsDTO;
import com.ohgiraffers.model.dto.TeacherDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class KindergartenDAO {

    private Properties prop = new Properties();

    TeacherDTO teacherDTO = null;
    List<TeacherDTO> teacherList = null;


    public KindergartenDAO(){

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/kindergarten-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void allTeacherInfo(Connection con){
        Statement stmt = null;
        ResultSet rset = null;

        List<TeacherDTO> allTeacher = null;

        String query = prop.getProperty("selectAllTeacherInfo");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            allTeacher = new ArrayList<>();

            while(rset.next()){
                teacherDTO = new TeacherDTO();

                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                teacherDTO.setTeacherBirth(rset.getString("teacher_birth"));
                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                teacherDTO.setTeacherOff(rset.getString("teacher_off"));

                allTeacher.add(teacherDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }

        for(TeacherDTO allTeacherInfo : allTeacher){
            System.out.println("allTeacherInfo = " + allTeacherInfo);
        }
    }

    public void selectSpecificTeacher(Connection con){

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        teacherList = new ArrayList<>();

            Scanner sc = new Scanner(System.in);
            System.out.println("조회할 기준을 선택해주세요 (1. 직원 사번, 2. 직원 이름, 3. 직원 담당 반, 4. 직원 직급) : ");
            int selectStandard = sc.nextInt();

                switch (selectStandard) {
                    case 1:
                        try {
                            System.out.println("조회할 직원의 사번을 입력해주세요 : ");
                            int selectTeacherId = sc.nextInt();

                            String query1 = prop.getProperty("selectTeacherById");
                            pstmt = con.prepareStatement(query1);

                            pstmt.setInt(1, selectTeacherId);
                            rset = pstmt.executeQuery();

                            if (rset.next()) {
                                teacherDTO = new TeacherDTO();

                                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                                teacherDTO.setTeacherBirth(rset.getString("teacher_birth"));
                                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                                teacherDTO.setTeacherOff(rset.getString("teacher_off"));

                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } finally {
                            close(pstmt);
                            close(rset);
                        }

                        System.out.println("specificTeacher = " + teacherDTO);;
                        break;

                    case 2:
                        try {
                            String query2 = prop.getProperty("selectTeacherByName");

                            pstmt = con.prepareStatement(query2);

                            System.out.println("조회할 직원의 이름을 입력해주세요 : ");
                            sc.nextLine();
                            String selectTeacherName = sc.nextLine();

                            pstmt.setString(1, selectTeacherName);
                            rset = pstmt.executeQuery();

                            if (rset.next()) {
                                teacherDTO = new TeacherDTO();

                                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                                teacherDTO.setTeacherBirth(rset.getString("teacher_birth"));
                                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                                teacherDTO.setTeacherOff(rset.getString("teacher_off"));

                            }

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println("specificTeacher = " + teacherDTO);;
                        break;

                    case 3:
                        try {
                            String query3 = prop.getProperty("selectTeacherByClass");
                            pstmt = con.prepareStatement(query3);

                            System.out.println("조회할 직원의 담당 반 명을 적어주세요 : ");
                            sc.nextLine();
                            String selectTeacherClass = sc.nextLine();
                            pstmt.setString(1, selectTeacherClass);
                            rset = pstmt.executeQuery();

                            while (rset.next()) {

                                teacherDTO = new TeacherDTO();

                                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                                teacherDTO.setTeacherOff(rset.getString("teacher_off"));


                                teacherList.add(teacherDTO);
                            }

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for(TeacherDTO specificTeacherList : teacherList){
                            System.out.println("specificTeacherList = " + specificTeacherList);
                        }

                        break;

                    case 4:
                        try {
                            String query4 = prop.getProperty("selectTeacherByGrade");
                            pstmt = con.prepareStatement(query4);

                            System.out.println("조회할 직원의 직급을 선택해주세요 : ");
                            sc.nextLine();
                            String selectTeacherGrade = sc.nextLine();
                            pstmt.setString(1, selectTeacherGrade);
                            rset = pstmt.executeQuery();

                            while (rset.next()) {
                                teacherDTO = new TeacherDTO();

                                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                                teacherDTO.setTeacherBirth(rset.getString("teacher_birth"));
                                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                                teacherDTO.setTeacherOff(rset.getString("teacher_off"));

                                teacherList.add(teacherDTO);

                            }

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for(TeacherDTO specificTeacherList : teacherList){
                            System.out.println("specificTeacherList = " + specificTeacherList);
                        }
                        break;
                }
    }

    public void addNewTeacher(Connection con){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int result = 0;

        teacherDTO = new TeacherDTO();
        teacherList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("새로운 직원의 사번을 입력해주세요 : ");
        int newTeacherId = sc.nextInt();
        System.out.println("새로운 직원의 이름을 입력해주세요 : ");
        sc.nextLine();
        String newTeacherName = sc.nextLine();
        System.out.println("새로운 직원의 직급을 선택해주세요 (1. 담임, 2. 보조) :  ");
        int newTeacherGrade = sc.nextInt();
        System.out.println("새로운 직원의 담당 반을 배정해주세요 : (1.햇님반, 2.달님반, 3.별님반, 4.구름반) : ");
        int newTeacherClass = sc.nextInt();
        System.out.println("새로운 직원의 생일을 입력해주세요 : ");
        sc.nextLine();
        String newTeacherBirth = sc.nextLine();
        System.out.println("추가할 직원의 연락처를 입력해주세요 (예.010-XXXX-XXXX) : ");
        sc.nextLine();
        String newTeacherPhone = sc.nextLine();
        System.out.println("새로운 직원의 연봉을 입력해주세요 : ");
        int newTeacherSalary = sc.nextInt();

        try {
           String query = prop.getProperty("insertNewTeacher");
           pstmt = con.prepareStatement(query);
           pstmt.setInt(1,newTeacherId);
           pstmt.setString(2, newTeacherName);
           switch(newTeacherGrade){
                case 1 : pstmt.setString(3, "담임"); break;
                case 2 : pstmt.setString(3, "보조"); break;
           }
           switch(newTeacherClass){
               case 1 : pstmt.setString(4, "햇님반"); break;
               case 2 : pstmt.setString(4, "달님반"); break;
               case 3 : pstmt.setString(4, "별님반"); break;
               case 4 : pstmt.setString(4, "구름반"); break;
           }
           pstmt.setString(5, newTeacherBirth);
           pstmt.setString(6, newTeacherPhone);
           pstmt.setInt(7, newTeacherSalary);

            result = pstmt.executeUpdate();

            rset = pstmt.executeQuery();

            if(rset.next()){
                teacherDTO = new TeacherDTO();

                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                teacherDTO.setTeacherBirth(rset.getString("teacher_birth"));
                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                teacherDTO.setTeacherOff(rset.getString("teacher_off"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(teacherDTO);

    }


    public void allKidsInfo(Connection con){

        Statement stmt = null;
        ResultSet rset = null;

        KidsDTO oneKids = null;
        List<KidsDTO> allKids = null;

        String query = prop.getProperty("selectAllKidsInfo");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            allKids = new ArrayList<>();

            while(rset.next()){
                oneKids = new KidsDTO();

                oneKids.setKidsId(rset.getInt("kids_id"));
                oneKids.setKidsName(rset.getString("kids_name"));
                oneKids.setKidsBirth(rset.getString("kids_birth"));
                oneKids.setKidsClass(rset.getString("kids_class"));
                oneKids.setKidsAdress(rset.getString("kids_adress"));
                oneKids.setKidsGender(rset.getString("kids_gender"));
                oneKids.setParentsPhone(rset.getString("parents_phone"));
                oneKids.setKidsSignificant(rset.getString("kids_significant"));

                allKids.add(oneKids);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }

        for(KidsDTO allKidsInfo : allKids){
            System.out.println("allKidsInfo = " + allKidsInfo);
        }
    }
}
