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

        TeacherDTO oneTeacher = null;
        List<TeacherDTO> allTeacher = null;

        String query = prop.getProperty("selectAllTeacherInfo");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            allTeacher = new ArrayList<>();

            while(rset.next()){
                oneTeacher = new TeacherDTO();

                oneTeacher.setTeacherId(rset.getInt("teacher_id"));
                oneTeacher.setTeacherName(rset.getString("teacher_name"));
                oneTeacher.setTeacherGrade(rset.getString("teacher_grade"));
                oneTeacher.setTeacherClass(rset.getString("teacher_class"));
                oneTeacher.setTeacherBirth(rset.getString("teacher_birth"));
                oneTeacher.setTeacherPhone(rset.getString("teacher_phone"));
                oneTeacher.setTeacherSalary(rset.getInt("teacher_salary"));
                oneTeacher.setTeacherOff(rset.getString("teacher_off"));

                allTeacher.add(oneTeacher);
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

        TeacherDTO specificTeacher = null;

        while(true) {

            Scanner sc = new Scanner(System.in);
            System.out.println("조회할 기준을 선택해주세요 (1. 직원 사번, 2. 직원 이름, 3. 직원 담당 반, 4. 직원 직급) : ");
            int selectStandard = sc.nextInt();

            try {
                String query = prop.getProperty("selectSpecificTeacher");


                pstmt = con.prepareStatement(query);
                rset = pstmt.executeQuery();

                specificTeacher = new TeacherDTO();

//                if(selectStandard == 1){
//
//                    System.out.println("조회할 직원의 사번을 입력해주세요 : ");
//                    int selectTeacherId = sc.nextInt();
//
//                    pstmt.setString(1, "teacher_id");
//                    pstmt.setInt(2, selectTeacherId);
//
//                    specificTeacher.setTeacherId(rset.getInt("teacher_id"));
//                    specificTeacher.setTeacherName(rset.getString("teacher_name"));
//                    specificTeacher.setTeacherGrade(rset.getString("teacher_grade"));
//                    specificTeacher.setTeacherClass(rset.getString("teacher_class"));
//                    specificTeacher.setTeacherBirth(rset.getString("teacher_birth"));
//                    specificTeacher.setTeacherPhone(rset.getString("teacher_phone"));
//                    specificTeacher.setTeacherSalary(rset.getInt("teacher_salary"));
//                    specificTeacher.setTeacherOff(rset.getString("teacher_off"));
//
//                } else if(selectStandard == 2){
//
//                    System.out.println("조회할 직원의 이름을 입력해주세요 : ");
//                    sc.next();
//                    String selectTeacherName = sc.nextLine();
//
//                    pstmt.setString(1, "teacher_name");
//                    pstmt.setString(2, '%' + selectTeacherName + '%');
//
//                    specificTeacher.setTeacherId(rset.getInt("teacher_id"));
//                    specificTeacher.setTeacherName(rset.getString("teacher_name"));
//                    specificTeacher.setTeacherGrade(rset.getString("teacher_grade"));
//                    specificTeacher.setTeacherClass(rset.getString("teacher_class"));
//                    specificTeacher.setTeacherBirth(rset.getString("teacher_birth"));
//                    specificTeacher.setTeacherPhone(rset.getString("teacher_phone"));
//                    specificTeacher.setTeacherSalary(rset.getInt("teacher_salary"));
//                    specificTeacher.setTeacherOff(rset.getString("teacher_off"));
//
//                } else if(selectStandard == 3){
//
//                    System.out.println("조회할 직원의 담당 반 명을 적어주세요 : ");
//                    sc.nextLine();
//                    String selectTeacherClass = sc.nextLine();
//
//                    pstmt.setString(1,"teacher_class");
//                    pstmt.setString(2, selectTeacherClass);
//
//                    specificTeacher.setTeacherId(rset.getInt("teacher_id"));
//                    specificTeacher.setTeacherName(rset.getString("teacher_name"));
//                    specificTeacher.setTeacherGrade(rset.getString("teacher_grade"));
//                    specificTeacher.setTeacherClass(rset.getString("teacher_class"));
//                    specificTeacher.setTeacherBirth(rset.getString("teacher_birth"));
//                    specificTeacher.setTeacherPhone(rset.getString("teacher_phone"));
//                    specificTeacher.setTeacherSalary(rset.getInt("teacher_salary"));
//                    specificTeacher.setTeacherOff(rset.getString("teacher_off"));
//
//                } else if(selectStandard == 4){
//
//                    System.out.println("조회할 직원의 직급을 선택해주세요 : ");
//                    sc.nextLine();
//                    String selectTeacherGrade = sc.nextLine();
//
//                    pstmt.setString(1, "teacher_grade");
//                    pstmt.setString(2, selectTeacherGrade);
//
//                    specificTeacher.setTeacherId(rset.getInt("teacher_id"));
//                    specificTeacher.setTeacherName(rset.getString("teacher_name"));
//                    specificTeacher.setTeacherGrade(rset.getString("teacher_grade"));
//                    specificTeacher.setTeacherClass(rset.getString("teacher_class"));
//                    specificTeacher.setTeacherBirth(rset.getString("teacher_birth"));
//                    specificTeacher.setTeacherPhone(rset.getString("teacher_phone"));
//                    specificTeacher.setTeacherSalary(rset.getInt("teacher_salary"));
//                    specificTeacher.setTeacherOff(rset.getString("teacher_off"));
//                }

                switch (selectStandard) {
                    case 1:
                        System.out.println("조회할 직원의 사번을 입력해주세요 : ");
                        int selectTeacherId = sc.nextInt();
                        pstmt.setInt(1, rset.getInt("teacher_id"));
                        pstmt.setInt(2, selectTeacherId);
                        break;
                    case 2:
                        System.out.println("조회할 직원의 이름을 입력해주세요 : ");
                        sc.next();
                        String selectTeacherName = sc.nextLine();
                        pstmt.setString(1, rset.getString("teacher_name"));
                        pstmt.setString(2, '%' + selectTeacherName + '%');
                        break;
                    case 3:
                        System.out.println("조회할 직원의 담당 반 명을 적어주세요 : ");
                        sc.nextLine();
                        String selectTeacherClass = sc.nextLine();
                        pstmt.setString(1, rset.getString("teacher_class"));
                        pstmt.setString(2, selectTeacherClass);
                        break;
                    case 4:
                        System.out.println("조회할 직원의 직급을 선택해주세요 : ");
                        sc.nextLine();
                        String selectTeacherGrade = sc.nextLine();
                        pstmt.setString(1, rset.getString("teacher_grade"));
                        pstmt.setString(2, selectTeacherGrade);
                        break;
                }

                if (rset.next()) {
                    specificTeacher = new TeacherDTO();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(rset);
                close(pstmt);
            }
            System.out.println("specificTeacher = " + specificTeacher);
        }
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
