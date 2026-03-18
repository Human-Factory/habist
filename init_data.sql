-- =============================================
-- 초기 테스트 데이터 (팀원 B)
-- =============================================

USE lms_platform;

-- =============================================
-- 1. members
-- =============================================
INSERT INTO members (login_id, password, nickname, name, email, phone, role, status)
VALUES
    ('user01', '1234', '유저1', '홍길동', 'user01@test.com', '010-1111-1111', 'USER', 'ACTIVE'),
    ('user02', '1234', '유저2', '김철수', 'user02@test.com', '010-2222-2222', 'USER', 'ACTIVE'),
    ('inst01', '1234', '강사1', '이영희', 'inst01@test.com', '010-3333-3333', 'INSTRUCTOR', 'ACTIVE'),
    ('admin01', '1234', '관리자', '관리자', 'admin01@test.com', '010-9999-9999', 'ADMIN', 'ACTIVE');

-- =============================================
-- 2. categories
-- =============================================
INSERT INTO categories (category_name)
VALUES
    ('프로그래밍'),
    ('디자인'),
    ('운동');

-- =============================================
-- 3. lectures
-- =============================================
INSERT INTO lectures (instructor_id, category_id, title, description)
VALUES
    (3, 1, 'Java 기초 문법', '자바 기초'),
    (3, 1, 'Spring Boot 입문', '스프링 부트 기초'),
    (3, 2, 'UI/UX 디자인 기초', '디자인 기본'),
    (3, 3, '홈트레이닝 입문', '운동 입문'),
    (3, 3, '기초 스트레칭', '스트레칭 기초');