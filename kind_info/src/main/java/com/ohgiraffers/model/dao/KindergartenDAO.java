package com.ohgiraffers.model.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
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
    Scanner sc = new Scanner(System.in);


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
                TeacherDTO teacherDTO = new TeacherDTO();

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

        List<TeacherDTO> teacherList = new ArrayList<>();

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
                                TeacherDTO teacherDTO = new TeacherDTO();

                                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                                teacherDTO.setTeacherBirth(rset.getString("teacher_birth"));
                                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                                teacherDTO.setTeacherOff(rset.getString("teacher_off"));

                                System.out.println("specificTeacher = " + teacherDTO);;
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } finally {
                            close(pstmt);
                            close(rset);
                        }

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
                                TeacherDTO teacherDTO = new TeacherDTO();

                                teacherDTO.setTeacherId(rset.getInt("teacher_id"));
                                teacherDTO.setTeacherName(rset.getString("teacher_name"));
                                teacherDTO.setTeacherGrade(rset.getString("teacher_grade"));
                                teacherDTO.setTeacherClass(rset.getString("teacher_class"));
                                teacherDTO.setTeacherBirth(rset.getString("teacher_birth"));
                                teacherDTO.setTeacherPhone(rset.getString("teacher_phone"));
                                teacherDTO.setTeacherSalary(rset.getInt("teacher_salary"));
                                teacherDTO.setTeacherOff(rset.getString("teacher_off"));

                                System.out.println("specificTeacher = " + teacherDTO);;

                            }

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

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

                                TeacherDTO teacherDTO = new TeacherDTO();

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
                                TeacherDTO teacherDTO = new TeacherDTO();

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

    public int maxTeacherID(Connection con){

        Statement stmt = null;
        ResultSet rset = null;

        int lastTeacherId = 0;


        try {
            String query = prop.getProperty("idOfLastTeacher");
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                lastTeacherId = rset.getInt("MAX(A.teacher_id)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            close(stmt);
            close(rset);
        }

        return lastTeacherId;
    }

    public int addNewTeacher(Connection con){
        KindergartenDAO kindergartenDAO = new KindergartenDAO();
        int lastTeacherId = kindergartenDAO.maxTeacherID(con);

        PreparedStatement pstmt = null;
        int result = 0;

        TeacherDTO teacherDTO = new TeacherDTO();

        System.out.println("새로운 직원의 사번을 입력해주세요 : ");
        System.out.println("지정 가능한 사번은 " + (lastTeacherId + 1) + " 부터 가능합니다.");
        int newTeacherId = sc.nextInt();
        System.out.println("새로운 직원의 이름을 입력해주세요 : ");
        sc.nextLine();
        String newTeacherName = sc.nextLine();
        System.out.println("새로운 직원의 직급을 선택해주세요.  ");
        System.out.println(" 1. 담임 ");
        System.out.println(" 2. 보조 ");
        int newTeacherGrade = sc.nextInt();
        System.out.println("새로운 직원의 담당 반을 배정해주세요. ");
        System.out.println(" 1.햇님반 ");
        System.out.println(" 2.달님반 ");
        System.out.println(" 3.별님반 ");
        System.out.println(" 4.구름반 ");
        int newTeacherClass = sc.nextInt();
        System.out.println("새로운 직원의 생년월일 8자리 숫자 전체를 입력해주세요 : ");
        sc.nextLine();
        String newTeacherBirth = sc.nextLine();
        System.out.println("추가할 직원의 연락처를 입력해주세요 (예.010-XXXX-XXXX) : ");
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        if(result > 0){
            System.out.println("새로운 직원 정보를 정상적으로 등록했습니다.");
            System.out.println("등록된 내용을 확인하시겠습니까? ");
            System.out.println(" 1. 예   2. 아니요 ");
            int checkNum = sc.nextInt();
            if(checkNum == 1){
                allTeacherInfo(con);
            }
        } else {
            System.out.println("새로운 직원 정보 등록을 실패했습니다.");
        }

        return result;
    }

    public TeacherDTO selectedTeacherId(Connection con, int selectedTeacherId){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        TeacherDTO teacherDTO = null;

        try {
            String query = prop.getProperty("selectTeacherById");
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, selectedTeacherId);

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
        } //finally {
          //  close(pstmt);
          //  close(rset);
       // }

        return teacherDTO;
    }

    public void updateTeacherInfo(Connection con){

        PreparedStatement pstmt = null;
        int result = 0;
        allTeacherInfo(con);
        System.out.println("정보를 수정할 직원의 사번을 입력해주세요 : ");
        int teacherId = sc.nextInt();

        TeacherDTO teacherDTO = selectedTeacherId(con, teacherId);

        System.out.println("수정할 정보를 선택해주세요 : ");
        System.out.println(" 1. 전체 (이름, 직급, 반, 연락처, 연봉, 휴가) ");
        System.out.println(" 2. 이름 ");
        System.out.println(" 3. 직급 ");
        System.out.println(" 4. 반 ");
        System.out.println(" 5. 연락처 ");
        System.out.println(" 6. 연봉 ");
        System.out.println(" 7. 휴가 ");
        int selectedTypeOfInfo = sc.nextInt();

        String query = prop.getProperty("updateTeacherInfo");
        try {
            pstmt = con.prepareStatement(query);

            switch(selectedTypeOfInfo) {
                case 1 :
                    System.out.println("수정할 이름을 입력해주세요 : ");
                    sc.nextLine();
                    String newName1 = sc.nextLine();
                    System.out.println("수정할 직급을 선택해주세요 : ");
                    System.out.println(" 1. 담임 ");
                    System.out.println(" 2. 보조 ");
                    int newGrade1 = sc.nextInt();
                    System.out.println("수정할 반을 선택해주세요 : ");
                    System.out.println(" 1. 햇님반 ");
                    System.out.println(" 2. 달님반 ");
                    System.out.println(" 3. 별님반 ");
                    System.out.println(" 4. 구름반 ");
                    int newClass1 = sc.nextInt();
                    System.out.println("수정할 연락처를 입력해주세요 : ");
                    sc.nextLine();
                    String newPhone1 = sc.nextLine();
                    System.out.println("수정할 연봉을 입력해주세요 : ");
                    int newSalary1 = sc.nextInt();
                    System.out.println("수정할 휴가 내용을 입력해주세요 : ");
                    sc.nextLine();
                    String newOff1 = sc.nextLine();

                    teacherDTO.setTeacherName(newName1);
                    switch(newGrade1){
                        case 1 : teacherDTO.setTeacherGrade("담임"); break;
                        case 2 : teacherDTO.setTeacherGrade("보조");break;
                    }
                    switch(newClass1){
                        case 1 : teacherDTO.setTeacherClass("햇님반"); break;
                        case 2 : teacherDTO.setTeacherClass("달님반"); break;
                        case 3 : teacherDTO.setTeacherClass("별님반"); break;
                        case 4 : teacherDTO.setTeacherClass("구름반"); break;
                    }
                    teacherDTO.setTeacherPhone(newPhone1);
                    teacherDTO.setTeacherSalary(newSalary1);
                    teacherDTO.setTeacherOff(newOff1);
                    break;
                case 2 :
                    System.out.print("수정할 이름을 입력해주세요 : ");
                    sc.nextLine();
                    String newName = sc.nextLine();
                    teacherDTO.setTeacherName(newName);
                    break;
                case 3 :
                    System.out.println("수정할 직급을 선택해주세요 : ");
                    System.out.println(" 1. 담임 ");
                    System.out.println(" 2. 보조 ");
                    int newGrade = sc.nextInt();
                    switch(newGrade){
                        case 1 : teacherDTO.setTeacherGrade("담임"); break;
                        case 2 : teacherDTO.setTeacherGrade("보조");break;
                    }
                    break;
                case 4 :
                    System.out.println("수정할 반을 선택해주세요 : ");
                    System.out.println(" 1. 햇님반 ");
                    System.out.println(" 2. 달님반 ");
                    System.out.println(" 3. 별님반 ");
                    System.out.println(" 4. 구름반 ");
                    int newClass = sc.nextInt();
                    switch(newClass){
                        case 1 : teacherDTO.setTeacherClass("햇님반"); break;
                        case 2 : teacherDTO.setTeacherClass("달님반"); break;
                        case 3 : teacherDTO.setTeacherClass("별님반"); break;
                        case 4 : teacherDTO.setTeacherClass("구름반"); break;
                    }
                    break;
                case 5 :
                    System.out.println("수정할 연락처를 입력해주세요 : ");
                    sc.nextLine();
                    String newPhone = sc.nextLine();
                    teacherDTO.setTeacherPhone(newPhone);
                    break;
                case 6 :
                    System.out.println("수정할 연봉을 입력해주세요 : ");
                    int newSalary = sc.nextInt();
                    teacherDTO.setTeacherSalary(newSalary);
                    break;
                case 7 :
                    System.out.println("수정할 휴가 내용을 입력해주세요 : ");
                    sc.nextLine();
                    String newOff = sc.nextLine();
                    teacherDTO.setTeacherOff(newOff);
                    break;
            }

            pstmt.setString(1, teacherDTO.getTeacherName());
            pstmt.setString(2, teacherDTO.getTeacherGrade());
            pstmt.setString(3, teacherDTO.getTeacherClass());
            pstmt.setString(4, teacherDTO.getTeacherPhone());
            pstmt.setInt(5, teacherDTO.getTeacherSalary());
            pstmt.setString(6, teacherDTO.getTeacherOff());
            pstmt.setInt(7, teacherDTO.getTeacherId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        if(result > 0){
            System.out.println("직원 정보를 성공적으로 수정하였습니다.");
            System.out.println("수정된 내용을 확인하시겠습니까? ");
            System.out.println(" 1. 예   2. 아니요 ");
            int checkNum = sc.nextInt();
            if(checkNum == 1){
                allTeacherInfo(con);
            }
        } else {
            System.out.println("직원 정보를 수정하는데 실패하였습니다.");
        }
    }

    public void deleteTeacherInfo(Connection con){

        PreparedStatement pstmt = null;
        int result = 0;

        allTeacherInfo(con);

        System.out.println("삭제할 직원의 사번을 입력해주세요 : ");
        int selectedteacherId = sc.nextInt();

        TeacherDTO teacherDTO = selectedTeacherId(con,selectedteacherId);

        try {
            String query = prop.getProperty("deleteTeacherInfo");
            pstmt = con.prepareStatement(query);
            teacherDTO.setTeacherId(selectedteacherId);
            pstmt.setInt(1, teacherDTO.getTeacherId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        if(result > 0){
            System.out.println("직원의 정보를 성공적으로 삭제했습니다.");
        } else {
            System.out.println("직원의 정보를 삭제하는데 실패하였습니다.");
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

    public void specificKidsInfo(Connection con){

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<KidsDTO> listKidsInfo = new ArrayList<>();

        System.out.println("조회할 기준을 선택해주세요 : ");
        System.out.println(" 1. 원생 ID ");
        System.out.println(" 2. 원생 이름 ");
        System.out.println(" 3. 원생 생일 ");
        System.out.println(" 4. 원생 반 ");
        System.out.println(" 5. 원생 성별 ");
        int standard = sc.nextInt();

        try {
            String query = prop.getProperty("specificKidsInfo");
            pstmt = con.prepareStatement(query);

            int kidsId = 0;
            String kidsName = null;
            String kidsBirth = null;
            String kidsClass = null;
            String kidsGender = null;
            switch(standard){
                case 1 :
                    System.out.println("조회하실 원생의 ID를 입력해주세요 : ");
                    kidsId = sc.nextInt();
                    break;
                case 2 :
                    System.out.println("조회하실 원생의 이름을 입력해주세요 : ");
                    sc.nextLine();
                    kidsName = sc.nextLine();
                    break;
                case 3 :
                    System.out.println("조회하실 원생의 생일인 달을 입력해주세요(ex. 9월 -> 09 / 12월 -> 12) : ");
                    sc.nextLine();
                    kidsBirth = sc.nextLine();
                    break;
                case 4 :
                    System.out.println("조회하실 원생의 반을 선택해주세요 : ");
                    System.out.println(" 1. 햇님반 ");
                    System.out.println(" 2. 달님반 ");
                    System.out.println(" 3. 별님반 ");
                    System.out.println(" 4. 구름반 ");
                    int classNum = sc.nextInt();
                    switch(classNum){
                        case 1 : kidsClass = "햇님반";
                                 break;
                        case 2 : kidsClass = "달님반";
                                 break;
                        case 3 : kidsClass = "별님반";
                                 break;
                        case 4 : kidsClass = "구름반";
                                 break;
                    }
                    break;
                case 5 :
                    System.out.println("원생의 성별을 선택해주세요 : ");
                    System.out.println(" 1. 남  2. 여  ");
                    int genderNum = sc.nextInt();
                    switch(genderNum){
                        case 1 :
                            kidsGender = "남";
                            break;
                        case 2 :
                            kidsGender = "여";
                            break;
                    }
                    break;
            }
            pstmt.setInt(1, kidsId);
            pstmt.setString(2, kidsName);
            pstmt.setString(3, "____" + kidsBirth + "__");
            pstmt.setString(4, kidsClass);
            pstmt.setString(5, kidsGender);

            rset = pstmt.executeQuery();

            while(rset.next()){
                KidsDTO newKidsDTO = new KidsDTO();

                newKidsDTO.setKidsId(rset.getInt("kids_id"));
                newKidsDTO.setKidsName(rset.getString("kids_name"));
                newKidsDTO.setKidsBirth(rset.getString("kids_birth"));
                newKidsDTO.setKidsClass(rset.getString("kids_class"));
                newKidsDTO.setKidsAdress(rset.getString("kids_adress"));
                newKidsDTO.setKidsGender(rset.getString("kids_gender"));
                newKidsDTO.setParentsPhone(rset.getString("parents_phone"));
                newKidsDTO.setKidsSignificant(rset.getString("kids_significant"));

                listKidsInfo.add(newKidsDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        for(KidsDTO selectedKids : listKidsInfo){
            System.out.println("selectedKids = " + selectedKids);
        }
    }

    public int maxKidsId(Connection con){
        Statement stmt = null;
        ResultSet rset = null;
        int maxNumOfKid = 0;
        try {
            String query = prop.getProperty("lastKidsId");
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                maxNumOfKid = rset.getInt("MAX(A.kids_id)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxNumOfKid;
    }

    public int addNewKid(Connection con){
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            System.out.println("새로운 원생의 이름을 입력해주세요 : ");
            String newKidName = sc.nextLine();
            System.out.println("새로운 원생의 생년월일 8자리를 입력해주세요 : ");
            String newKidBirth = sc.nextLine();
            System.out.println("새로운 원생의 반을 선택해주세요 : ");
            System.out.println(" 1. 햇님반  2. 달님반  3. 별님반  4. 구름반 ");
            int newKidClass = sc.nextInt();
            System.out.println("새로운 원생의 주소를 입력해주세요 : ");
            sc.nextLine();
            String newKidAdress = sc.nextLine();
            System.out.println("새로운 원생의 성별을 입력해주세요 : ");
            System.out.println(" 1. 남  2. 여 ");
            int newKidGender = sc.nextInt();
            System.out.println("새로운 원생의 양육자의 연락처를 입력해주세요 : ");
            sc.nextLine();
            String newParentPhone = sc.nextLine();
            System.out.println("새로운 원생의 특이사항이 있으신가요? : ");
            System.out.println(" 1. 있다.  2. 없다. ");
            int significantNum = sc.nextInt();

            String query = prop.getProperty("insertNewKid");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, newKidName);
            pstmt.setString(2, newKidBirth);
            switch(newKidClass){
                case 1 : pstmt.setString(3, "햇님반"); break;
                case 2 : pstmt.setString(3, "달님반"); break;
                case 3 : pstmt.setString(3, "별님반"); break;
                case 4 : pstmt.setString(3, "구름반"); break;
            }
            pstmt.setString(4, newKidAdress);
            switch(newKidGender){
                case 1 : pstmt.setString(5, "남"); break;
                case 2 : pstmt.setString(5, "여"); break;
            }
            pstmt.setString(6, newParentPhone);
            switch(significantNum){
                case 1 :
                    System.out.println("새로운 원생의 특이사항을 입력해주세요 : ");
                    sc.nextLine();
                    String newKidSignificant = sc.nextLine();
                    pstmt.setString(7, newKidSignificant);
                    break;
                case 2 : pstmt.setString(7, null); break;

            }

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(result > 0){
            System.out.println("새로운 원생 정보를 성공적으로 등록했습니다.");
            System.out.println("확인하시겠습니까?");
            System.out.println(" 1. 예  2. 아니요 ");
            int checkNum = sc.nextInt();
            if(checkNum == 1){
                allKidsInfo(con);
            }
        } else {
            System.out.println("새로운 원생 정보 등록에 실패하였습니다.");
        }
        return result;
    }

    public KidsDTO selectKidsById(Connection con, int kidsId){

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        KidsDTO kidsDTO = new KidsDTO();

        try {
            String query = prop.getProperty("selectKidsById");
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, kidsId);
            rset = pstmt.executeQuery();

            if(rset.next()){

                kidsDTO.setKidsId(rset.getInt("kids_id"));
                kidsDTO.setKidsName(rset.getString("kids_name"));
                kidsDTO.setKidsBirth(rset.getString("kids_birth"));
                kidsDTO.setKidsClass(rset.getString("kids_class"));
                kidsDTO.setKidsAdress(rset.getString("kids_adress"));
                kidsDTO.setKidsGender(rset.getString("kids_gender"));
                kidsDTO.setParentsPhone(rset.getString("parents_phone"));
                kidsDTO.setKidsSignificant(rset.getString("kids_significant"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kidsDTO;
    }

    public void updateKidsInfo(Connection con){

        PreparedStatement pstmt = null;
        int result = 0;

        allKidsInfo(con);
        System.out.println("정보를 수정할 원생의 ID를 입력해주세요 : ");
        int updateKidId = sc.nextInt();

        KidsDTO kidsDTO = selectKidsById(con, updateKidId);

        System.out.println("수정할 정보를 선택해주세요 : ");
        System.out.println(" 1. 전체 (이름, 생일, 반, 주소, 부모님 연락처, 특이사항) ");
        System.out.println(" 2. 이름 ");
        System.out.println(" 3. 생일 ");
        System.out.println(" 4. 반 ");
        System.out.println(" 5. 주소 ");
        System.out.println(" 6. 부모님 연락처 ");
        System.out.println(" 7. 특이사항 ");
        int selectedType = sc.nextInt();

        try {
            String query = prop.getProperty("updateKidInfo");
            pstmt = con.prepareStatement(query);

            switch(selectedType){
                case 1 :
                    System.out.println("수정할 원생의 이름을 입력해주세요 : ");
                    sc.nextLine();
                    String updateKidName = sc.nextLine();
                    System.out.println("수정할 원생의 생일을 입력해주세요 : ");
                    String updateKidsBirth = sc.nextLine();
                    System.out.println("수정할 원생의 반을 선택해주세요 : ");
                    System.out.println(" 1. 햇님반 ");
                    System.out.println(" 2. 달님반 ");
                    System.out.println(" 3. 별님반 ");
                    System.out.println(" 4. 구름반 ");
                    int updateKidsClass = sc.nextInt();
                    System.out.println("수정할 원생의 주소를 입력해주세요 : ");
                    sc.nextLine();
                    String updateKidsAdress = sc.nextLine();
                    System.out.println("수정할 원생 부모님의 연락처를 입력하세요 : ");
                    String updateParentsPhone = sc.nextLine();
                    System.out.println("수정할 원생의 특이사항 입력 여부를 선택해주세요 : ");
                    System.out.println(" 1. 특이사항 입력 및 수정 ");
                    System.out.println(" 2. 특이사항 내용 삭제 ");
                    int significant = sc.nextInt();

                    kidsDTO.setKidsName(updateKidName);
                    kidsDTO.setKidsBirth(updateKidsBirth);
                    switch(updateKidsClass){
                        case 1 : kidsDTO.setKidsClass("햇님반"); break;
                        case 2 : kidsDTO.setKidsClass("달님반"); break;
                        case 3 : kidsDTO.setKidsClass("별님반"); break;
                        case 4 : kidsDTO.setKidsClass("구름반"); break;
                    }
                    kidsDTO.setKidsAdress(updateKidsAdress);
                    kidsDTO.setParentsPhone(updateParentsPhone);
                    switch(significant){
                        case 1 :
                            System.out.println("수정할 특이사항을 입력해주세요 : ");
                            sc.nextLine();
                            String updatekidsSignificant = sc.nextLine();
                            kidsDTO.setKidsSignificant(updatekidsSignificant);
                            break;
                        case 2 :
                            System.out.println("원생의 특이사항을 삭제합니다.");
                            kidsDTO.setKidsSignificant(null);
                            break;
                    }
                    break;
                case 2 :
                    System.out.println("수정할 원생의 이름을 입력해주세요 : ");
                    sc.nextLine();
                    String updateKidName1 = sc.nextLine();
                    kidsDTO.setKidsName(updateKidName1);
                    break;
                case 3 :
                    System.out.println("수정할 원생의 생일을 입력해주세요 : ");
                    sc.nextLine();
                    String updateKidsBirth1 = sc.nextLine();
                    kidsDTO.setKidsBirth(updateKidsBirth1);
                    break;
                case 4 :
                    System.out.println("수정할 원생의 반을 선택해주세요 : ");
                    System.out.println(" 1. 햇님반 ");
                    System.out.println(" 2. 달님반 ");
                    System.out.println(" 3. 별님반 ");
                    System.out.println(" 4. 구름반 ");
                    int updateKidsClass1 = sc.nextInt();
                    switch(updateKidsClass1){
                        case 1 : kidsDTO.setKidsClass("햇님반"); break;
                        case 2 : kidsDTO.setKidsClass("달님반"); break;
                        case 3 : kidsDTO.setKidsClass("별님반"); break;
                        case 4 : kidsDTO.setKidsClass("구름반"); break;
                    }
                    break;
                case 5 :
                    System.out.println("수정할 원생의 주소를 입력해주세요 : ");
                    sc.nextLine();
                    String updateKidsAdress1 = sc.nextLine();
                    kidsDTO.setKidsAdress(updateKidsAdress1);
                    break;
                case 6 :
                    System.out.println("수정할 원생 부모님의 연락처를 입력하세요 : ");
                    sc.nextLine();
                    String updateParentsPhone1 = sc.nextLine();
                    kidsDTO.setParentsPhone(updateParentsPhone1);
                    break;
                case 7 :
                    System.out.println("수정할 원생의 특이사항 입력 여부를 선택해주세요 : ");
                    System.out.println(" 1. 특이사항 입력 및 수정 ");
                    System.out.println(" 2. 특이사항 내용 삭제 ");
                    int significant1 = sc.nextInt();
                    switch(significant1){
                        case 1 :
                            System.out.println("수정할 특이사항을 입력해주세요 : ");
                            sc.nextLine();
                            String updatekidsSignificant1 = sc.nextLine();
                            kidsDTO.setKidsSignificant(updatekidsSignificant1);
                            break;
                        case 2 :
                            System.out.println("원생의 특이사항을 삭제합니다.");
                            kidsDTO.setKidsSignificant(null);
                            break;
                    }
                    break;
            }

            pstmt.setString(1, kidsDTO.getKidsName());
            pstmt.setString(2, kidsDTO.getKidsBirth());
            pstmt.setString(3, kidsDTO.getKidsClass());
            pstmt.setString(4, kidsDTO.getKidsAdress());
            pstmt.setString(5, kidsDTO.getParentsPhone());
            pstmt.setString(6, kidsDTO.getKidsSignificant());
            pstmt.setInt(7, kidsDTO.getKidsId());
             result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            close(pstmt);
        }

        if(result > 0){
            System.out.println("수정된 정보가 정상적으로 등록되었습니다. ");
            System.out.println("확인해보시겠습니까 ? ");
            System.out.println(" 1. 예  2. 아니오 ");
            int checkNum = sc.nextInt();
            if(checkNum == 1){
                KidsDTO checkKidsInfo = selectKidsById(con, updateKidId);
                System.out.println("checkKidsInfo = " + checkKidsInfo);
                
            }
        } else {
            System.out.println("수정된 정보 등록에 실패했습니다. ");
        }
    }

    public void deleteKidInfo(Connection con){
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String query = prop.getProperty("deleteKidInfo");
            pstmt = con.prepareStatement(query);

            allKidsInfo(con);
            System.out.println(" 삭제할 원생의 ID를 입력해주세요 : ");
            int deleteKidId = sc.nextInt();
            pstmt.setInt(1, deleteKidId);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(result > 0){
            System.out.println("원생 정보를 성공적으로 삭제하였습니다.");
            System.out.println("확인해보시겠습니까 ? ");
            System.out.println(" 1. 예  2. 아니오 ");
            int checkNum = sc.nextInt();
            if(checkNum == 1){
                allKidsInfo(con);
            }
        } else {
            System.out.println("선택하신 원생의 정보 삭제를 실패했습니다. ");
        }
    }
}
