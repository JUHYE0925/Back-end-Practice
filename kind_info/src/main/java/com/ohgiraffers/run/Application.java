package com.ohgiraffers.run;

import com.ohgiraffers.model.dao.KindergartenDAO;

import java.sql.Connection;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {



    public static void main(String[] args) {

        Connection con = getConnection();
        KindergartenDAO registDAO = new KindergartenDAO();

        Scanner sc = new Scanner(System.in);
        // 관리자 : 모든 테이블 조회 및 crud 가능

        while(true) {

            System.out.println("사번을 입력해주세요 : ");
            int code = sc.nextInt();

            if (code == 101 || code == 102) {

                while(true) {
                    System.out.println();
                    System.out.println("========= 관리자 전용 메뉴 =========");
                    System.out.println("1. 전체 직원 조회");
                    System.out.println("2. 내가 관리하는 직원 조회");
                    // 내가 관리하는 직원 정보 수정(?)

                    System.out.println("3. 특정 직원 조회");
                    System.out.println("4. 새로운 직원 추가");
                    System.out.println("5. 직원 정보 수정");
                    System.out.println("6. 직원 정보 삭제");
                    System.out.println("7. 전체 원생 조회");
                    System.out.println("8. 특정 원생 조회");
                    System.out.println("9. 새로운 원생 추가");
                    System.out.println("10. 원생 정보 수정");
                    System.out.println("11. 원생 정보 삭제");
                    System.out.println("0. 프로그램 종료");
                    System.out.println("사용할 메뉴를 선택해주세요 : ");
                    int num1 = sc.nextInt();

                    switch (num1) {
                        case 1 : registDAO.allTeacherInfo(con); break;
                        case 2 : registDAO.SelectMySubordinate(con,code); break;


                        case 3 : registDAO.selectSpecificTeacher(con); break;
                        case 4 : registDAO.addNewTeacher(con);break;
                        case 5 : registDAO.updateTeacherInfo(con); break;
                        case 6 : registDAO.deleteTeacherInfo(con); break;
                        case 7 : registDAO.allKidsInfo(con); break;
                        case 8 : registDAO.specificKidsInfo(con); break;
                        case 9 : registDAO.addNewKid(con); break;
                        case 10 : registDAO.updateKidsInfo(con); break;
                        case 11 : registDAO.deleteKidInfo(con); break;

                    }

                    if (num1 == 0) {
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    }
                } break;

            } else if (code == 201 || code == 202 || code == 203 || code == 204 || code == 205 || code == 206 || code == 207 || code == 208) {

                while(true) {
                    System.out.println();
                    System.out.println("========= 사용자 전용 메뉴 =========");
                    System.out.println("1. 전체 원생 조회");
                    System.out.println("2. 특정 원생 조회");
                    System.out.println("3. 새로운 원생 추가");
                    System.out.println("4. 원생 정보 수정");
                    System.out.println("5. 원생 정보 삭제");
                    System.out.println("0. 프로그램 종료");
                    System.out.println("사용할 메뉴를 선택해주세요 : ");
                    int num2 = sc.nextInt();

                    switch (num2) {
                        case 1:
                            registDAO.allKidsInfo(con);
                            break;
                        case 2:
                            registDAO.specificKidsInfo(con);
                            break;
                        case 3:
                            registDAO.addNewKid(con);
                            break;
                        case 4:
                            registDAO.updateKidsInfo(con);
                            break;
                        case 5:
                            registDAO.deleteKidInfo(con);
                            break;
                    }

                    if (num2 == 0) {
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    }
                } break;
            } else {
                System.out.println("사번을 잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }

    }
}
