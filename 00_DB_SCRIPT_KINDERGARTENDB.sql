USE kindergartendb;

-- 테이블 삭제
-- DROP TABLE IF EXISTS manager;
-- DROP TABLE IF EXISTS teacher;
-- DROP TABLE IF EXISTS kids;
-- DROP TABLE IF EXISTS class;
-- DROP TABLE IF EXISTS manage_kids;
-- DROP TABLE IF EXISTS manage_teacher;

-- 테이블 생성
-- 관리자 테이블 생성
CREATE TABLE IF NOT EXISTS manager(
	manager_code INT AUTO_INCREMENT PRIMARY KEY COMMENT '관리자ID',
    manager_name VARCHAR(10) NOT NULL COMMENT '관리자 이름',
    manager_birth VARCHAR(20) NOT NULL COMMENT '관리자 생년월일',
    manager_grade VARCHAR(20) NOT NULL CHECK(manager_grade IN('원장','부원장')) COMMENT '관리자 직급',
    manager_phone VARCHAR(15) UNIQUE NOT NULL COMMENT '관리자 연락처',
    manager_off VARCHAR(20) DEFAULT 'X' COMMENT '관리자 연차유무'
) ENGINE=INNODB COMMENT '관리자';

-- 선생님 테이블 생성
CREATE TABLE IF NOT EXISTS teacher(
	teacher_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '선생님ID',
    teacher_name VARCHAR(10) NOT NULL COMMENT '선생님 이름',
    teacher_grade VARCHAR(20) NOT NULL CHECK(teacher_grade IN('담임', '보조')) COMMENT '선생님 직급',
    teacher_class VARCHAR(5) CHECK(teacher_class IN('햇님반','달님반','별님반', '구름반')) COMMENT '선생님 담당 반',
    teacher_superior int COMMENT '직속상관 사번',
    teacher_birth VARCHAR(20) NOT NULL COMMENT '선생님 생년월일',
    teacher_phone VARCHAR(15) UNIQUE NOT NULL COMMENT '선생님 연락처',
    teacher_salary INT NOT NULL COMMENT '선생님 연봉',
    teacher_off VARCHAR(20) DEFAULT 'X' COMMENT '선생님 연차유무',
    FOREIGN KEY(manager_code)
    REFERENCES manager(manager_code)
)ENGINE=INNODB COMMENT '선생님';

-- 원생 테이블 생성
CREATE TABLE IF NOT EXISTS kids(
	kids_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '원생ID',
    kids_name VARCHAR(30) NOT NULL COMMENT '원생 이름',
    kids_birth VARCHAR(30) NOT NULL COMMENT '원생 생일',
    kids_class VARCHAR(5) CHECK(kids_class IN('햇님반','달님반','별님반', '구름반')) COMMENT '원생 반',
    kids_adress VARCHAR(30) NOT NULL COMMENT '원생 주소',
    kids_gender VARCHAR(5) NOT NULL CHECK (kids_gender IN('남', '여')) COMMENT '원생 성별',
    parents_phone VARCHAR(20) NOT NULL COMMENT '원생 양육자 연락처',
	kids_significant VARCHAR(255) COMMENT '원생 특이사항'
)ENGINE=INNODB COMMENT '원생';

-- 반 테이블 생성
CREATE TABLE IF NOT EXISTS class(
	class_code INT NOT NULL COMMENT '반 코드',
    class_name VARCHAR(4) CHECK(class_name IN('햇님반','달님반','별님반', '구름반')) COMMENT '반',
    main_teacher_id INT,
    main_teacher_name VARCHAR(30) NOT NULL COMMENT '담임 선생님 이름',
	sub_teacher_id INT,
    sub_teacher_name VARCHAR(30) NOT NULL COMMENT '보조 선생님 이름',
    kids_id INT PRIMARY KEY,
    kids_name VARCHAR(30) NOT NULL COMMENT '원생 이름',
    FOREIGN KEY(kids_id)
    REFERENCES kids(kids_id),
    FOREIGN KEY(main_teacher_id)
    REFERENCES teacher(teacher_id),
    FOREIGN KEY(sub_teacher_id)
    REFERENCES teacher(teacher_id)
) ENGINE=INNODB COMMENT '반';

-- 원생 관리 테이블 생성
CREATE TABLE IF NOT EXISTS manage_kids(
	manager_code INT,
    kid_class VARCHAR(5) CHECK(kid_class IN('햇님반','달님반','별님반', '구름반')),
    kids_id INT PRIMARY KEY,
    kids_name VARCHAR(10) NOT NULL COMMENT '원생 이름',
    FOREIGN KEY(manager_code)
    REFERENCES manager(manager_code),
    FOREIGN KEY(kids_id)
    REFERENCES kids(kids_id)
)ENGINE=INNODB COMMENT '원생관리'; 

-- 직원 관리 테이블 생성
CREATE TABLE IF NOT EXISTS manage_teacher(
	manager_code INT,
    teacher_id INT PRIMARY KEY,
    teacher_name VARCHAR(10) NOT NULL COMMENT '직원(선생님) 이름',
    FOREIGN KEY(manager_code)
    REFERENCES manager(manager_code),
    FOREIGN KEY(teacher_id)
    REFERENCES teacher(teacher_id)
)ENGINE=INNODB COMMENT '직원 관리';

-- 데이터 삽입
-- 관리자 테이블 데이터 입력
INSERT INTO manager VALUES (101, '권지옹', '19610829', '원장', '010-1961-0829', DEFAULT);
INSERT INTO manager VALUES (102, '강말자', '19630532', '부원장', '010-1963-0532', DEFAULT);

-- 선생님 테이블 데이터 입력
INSERT INTO teacher VALUES (201, '나미리', '담임', '햇님반', 101, '19900204', '010-1990-0204', 2500000, DEFAULT);
INSERT INTO teacher VALUES (202, '채성화', '담임', '달님반', 102, '19900706', '010-1990-0706', 2500000, '출산휴가예정');
INSERT INTO teacher VALUES (203, '차은주', '담임', '별님반', 101, '19910123', '010-1991-0123', 2400000, DEFAULT);
INSERT INTO teacher VALUES (204, '이창민', '담임', '구름반', 102, '19920502', '010-1992-0502', 2350000, DEFAULT);
INSERT INTO teacher VALUES (205, '봉미선', '보조', '햇님반', 101, '19951010', '010-1995-1010', 2250000, DEFAULT);
INSERT INTO teacher VALUES (206, '유이슬', '보조', '달님반', 102, '19991123', '010-1999-1123', 2250000, DEFAULT);
INSERT INTO teacher VALUES (207, '김흑곰', '보조', '별님반', 101, '19940915', '010-1994-0915', 2250000, DEFAULT);
INSERT INTO teacher VALUES (208, '엄상현', '보조', '구름반', 102, '19951122', '010-1995-1122', 2200000, DEFAULT);

-- 원생 테이블 테이터 입력
INSERT INTO kids VALUES (301, '강치타', '20180523', '햇님반', '서울특별시 마포구 대흥동', '남', '010-2018-0523', '달리기 잘함');
INSERT INTO kids VALUES (302, '이준호', '20180412', '햇님반', '서울특별시 마포구 대흥동', '남', '010-2018-0412', NULL);
INSERT INTO kids VALUES (303, '박광태', '20181030', '햇님반', '서울특별시 마포구 대흥동', '남', '010-2018-1030', NULL);
INSERT INTO kids VALUES (304, '고지라', '20180612', '햇님반', '서울특별시 마포구 염라동', '남', '010-2018-0612', NULL);
INSERT INTO kids VALUES (305, '강하나', '20180328', '햇님반', '서울특별시 마포구 연남동', '여', '010-2018-0328', NULL);
INSERT INTO kids VALUES (306, '박은하', '20180729', '햇님반', '서울특별시 마포구 도화동', '여', '010-2018-0729', NULL);

INSERT INTO kids VALUES (307, '신짱구', '20190505', '달님반', '서울특별시 마포구 아현동', '남', '010-2019-0505', '피망 싫어함');
INSERT INTO kids VALUES (308, '김철수', '20190719', '달님반', '서울특별시 마포구 상암동', '남', '010-2019-0719', NULL);
INSERT INTO kids VALUES (309, '한유리', '20190605', '달님반', '서울특별시 마포구 도화동', '여', '010-2019-0605', NULL);
INSERT INTO kids VALUES (310, '이훈이', '20190204', '달님반', '서울특별시 마포구 아현동', '남', '010-2019-0204', '겁이 많음');
INSERT INTO kids VALUES (311, '보맹구', '20190910', '달님반', '서울특별시 마포구 동교동', '남', '010-2019-0910', NULL);
INSERT INTO kids VALUES (312, '한수지', '20190327', '달님반', '서울특별시 마포구 상암동', '여', '010-2019-0327', NULL);

INSERT INTO kids VALUES (313, '노미소', '20201107', '별님반', '서울특별시 마포구 아현동', '여', '010-2020-1107', '거미 무서워함');
INSERT INTO kids VALUES (314, '김민지', '20201205', '별님반', '서울특별시 마포구 노고산동', '여', '010-2020-1205', '물을 무서워함');
INSERT INTO kids VALUES (315, '나초롱', '20200510', '별님반', '서울특별시 마포구 동교동', '여', '010-2020-0510', NULL);
INSERT INTO kids VALUES (316, '이새벽', '20201028', '별님반', '서울특별시 마포구 연남동', '남', '010-2020-1028', NULL);
INSERT INTO kids VALUES (317, '박슬기', '20200209', '별님반', '서울특별시 마포구 상수동', '여', '010-2020-0209', NULL);
INSERT INTO kids VALUES (318, '배한별', '20200619', '별님반', '서울특별시 마포구 상암동', '남', '010-2020-0619', NULL);

INSERT INTO kids VALUES (319, '강은하', '20210323', '구름반', '서울특별시 마포구 연남동', '남', '010-2018-0328', '햇님반 강하나 동생');  -- 강하나 동생
INSERT INTO kids VALUES (320, '이다은', '20210916', '구름반', '서울특별시 마포구 동교동', '여', '010-2021-0916', '부모님 사고로 여의고 형이랑 둘이 거주');
INSERT INTO kids VALUES (321, '김경아', '20210412', '구름반', '서울특별시 마포구 아현동', '여', '010-2021-0412', NULL);
INSERT INTO kids VALUES (322, '이동훈', '20211001', '구름반', '서울특별시 마포구 도화동', '남', '010-2021-1001', '이정훈과 쌍둥이');  -- 이동훈 이정훈 쌍둥이
INSERT INTO kids VALUES (323, '이정훈', '20211001', '구름반', '서울특별시 마포구 도화동', '남', '010-2021-1001', '이동훈과 쌍둥이');  -- 이동훈 이정훈 쌍둥이
INSERT INTO kids VALUES (324, '김보나', '20210210', '구름반', '서울특별시 마포구 염라동', '여', '010-2021-0210', NULL);

-- 반 테이블 데이터 입력
INSERT INTO class VALUES (1, '햇님반', 201, '나미리', 205, '봉미선', 301, '강치타');
INSERT INTO class VALUES (1, '햇님반', 201, '나미리', 205, '봉미선', 302, '이준호');
INSERT INTO class VALUES (1, '햇님반', 201, '나미리', 205, '봉미선', 303, '박광태');
INSERT INTO class VALUES (1, '햇님반', 201, '나미리', 205, '봉미선', 304, '고지라');
INSERT INTO class VALUES (1, '햇님반', 201, '나미리', 205, '봉미선', 305, '강하나');
INSERT INTO class VALUES (1, '햇님반', 201, '나미리', 205, '봉미선', 306, '박은하');

INSERT INTO class VALUES (2, '달님반', 202, '채성화', 206, '유이슬', 307, '신짱구');
INSERT INTO class VALUES (2, '달님반', 202, '채성화', 206, '유이슬', 308, '김철수');
INSERT INTO class VALUES (2, '달님반', 202, '채성화', 206, '유이슬', 309, '한유리');
INSERT INTO class VALUES (2, '달님반', 202, '채성화', 206, '유이슬', 310, '이훈이');
INSERT INTO class VALUES (2, '달님반', 202, '채성화', 206, '유이슬', 311, '보맹구');
INSERT INTO class VALUES (2, '달님반', 202, '채성화', 206, '유이슬', 312, '한수지');

INSERT INTO class VALUES (3, '별님반', 203, '차은주', 207, '김흑곰', 313, '노미소');
INSERT INTO class VALUES (3, '별님반', 203, '차은주', 207, '김흑곰', 314, '김민지');
INSERT INTO class VALUES (3, '별님반', 203, '차은주', 207, '김흑곰', 315, '나초롱');
INSERT INTO class VALUES (3, '별님반', 203, '차은주', 207, '김흑곰', 316, '이새롬');
INSERT INTO class VALUES (3, '별님반', 203, '차은주', 207, '김흑곰', 317, '박슬기');
INSERT INTO class VALUES (3, '별님반', 203, '차은주', 207, '김흑곰', 318, '배한별');

INSERT INTO class VALUES (4, '구름반', 204, '이창민', 208, '엄상현', 319, '강은하');
INSERT INTO class VALUES (4, '구름반', 204, '이창민', 208, '엄상현', 320, '이다은');
INSERT INTO class VALUES (4, '구름반', 204, '이창민', 208, '엄상현', 321, '김경하');
INSERT INTO class VALUES (4, '구름반', 204, '이창민', 208, '엄상현', 322, '이동훈');
INSERT INTO class VALUES (4, '구름반', 204, '이창민', 208, '엄상현', 323, '이정훈');
INSERT INTO class VALUES (4, '구름반', 204, '이창민', 208, '엄상현', 324, '김보나');

-- 원생 관리 테이블 데이터 입력
INSERT INTO manage_kids VALUES (101, '햇님반', 301, '강치타');
INSERT INTO manage_kids VALUES (101, '햇님반', 302, '이준호');
INSERT INTO manage_kids VALUES (101, '햇님반', 303, '박광태');
INSERT INTO manage_kids VALUES (101, '햇님반', 304, '고지라');
INSERT INTO manage_kids VALUES (101, '햇님반', 305, '강하나');
INSERT INTO manage_kids VALUES (101, '햇님반', 306, '박은하');

INSERT INTO manage_kids VALUES (102, '달님반', 307, '신짱구');
INSERT INTO manage_kids VALUES (102, '달님반', 308, '김철수');
INSERT INTO manage_kids VALUES (102, '달님반', 309, '한유리');
INSERT INTO manage_kids VALUES (102, '달님반', 310, '이훈이');
INSERT INTO manage_kids VALUES (102, '달님반', 311, '보맹구');
INSERT INTO manage_kids VALUES (102, '달님반', 312, '한수지');

INSERT INTO manage_kids VALUES (101, '별님반', 313, '노미소');
INSERT INTO manage_kids VALUES (101, '별님반', 314, '김민지');
INSERT INTO manage_kids VALUES (101, '별님반', 315, '나초롱');
INSERT INTO manage_kids VALUES (101, '별님반', 316, '이새롬');
INSERT INTO manage_kids VALUES (101, '별님반', 317, '박슬기');
INSERT INTO manage_kids VALUES (101, '별님반', 318, '배한별');

INSERT INTO manage_kids VALUES (102, '구름반', 319, '강은하');
INSERT INTO manage_kids VALUES (102, '구름반', 320, '이다은');
INSERT INTO manage_kids VALUES (102, '구름반', 321, '김경하');
INSERT INTO manage_kids VALUES (102, '구름반', 322, '이동훈');
INSERT INTO manage_kids VALUES (102, '구름반', 323, '이정훈');
INSERT INTO manage_kids VALUES (102, '구름반', 324, '김보나');

-- 직원 관리 테이블 데이터 입력
INSERT INTO manage_teacher VALUES (101, 201, '나미리');
INSERT INTO manage_teacher VALUES (102, 202, '채성화');
INSERT INTO manage_teacher VALUES (101, 205, '봉미선');
INSERT INTO manage_teacher VALUES (102, 206, '유이슬');
INSERT INTO manage_teacher VALUES (101, 203, '차은주');
INSERT INTO manage_teacher VALUES (102, 204, '이창민');
INSERT INTO manage_teacher VALUES (101, 207, '김흑곰');
INSERT INTO manage_teacher VALUES (102, 208, '엄상현');
