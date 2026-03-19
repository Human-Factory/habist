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

select * from wishlists;

INSERT INTO wishlists (member_id, lecture_id) VALUES
(1, 1),
(1, 2),
(2, 3);


INSERT INTO subscriptions (member_id, instructor_id) VALUES
                                                         (1, 3),
                                                         (2, 3);

INSERT INTO enrollments (member_id, lecture_id, progress_percent, last_position) VALUES
                                                                                     (1, 1, 30, 'SECTION-1'),
                                                                                     (1, 2, 70, 'SECTION-3'),
                                                                                     (2, 3, 100, 'SECTION-5');


SELECT
    w.wishlist_id,
    w.member_id,
    l.lecture_id,
    l.title,
    w.created_at
FROM wishlists w
         JOIN lectures l ON w.lecture_id = l.lecture_id
WHERE w.member_id = 1;

SELECT
    e.enrollment_id,
    e.member_id,
    l.title,
    e.progress_percent,
    e.last_position,
    e.enrolled_at
FROM enrollments e
         JOIN lectures l ON e.lecture_id = l.lecture_id
WHERE e.member_id = 1;



-- WHERE member_id = ? AND status = 'ACTIVE';
