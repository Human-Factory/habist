package com.hab.hobbymarket.model;

public class Lecture {

    private int lectureId; // 강의 번호
    private String title; // 강의 제목
    private String instructorName; // 강의자 이름
    private int viewCount; // 조회수
    private int likeCount; // 좋아요의 수

    // 아무 값 없이 Lecture 객체를 만들 때 사용 / 나중에 setter 값을 하나씩 넣을 목적
    public Lecture() {}

    // 5개의 값을 Lecture에 한번에 넣고 객체 생성에 사용
    public Lecture(int lectureId, String title, String instructorName,
                   int viewCount, int likeCount) {
        this.lectureId = lectureId;
        this.title = title;
        this.instructorName = instructorName;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }


    /* comment. 이 구조를 사용한 이유
        카테고리별 강의 목록 조회에서 사용될 예정
        getter & setter를 통해서 값을 꺼내고 넣기를 진행
     */

    public int getLectureId() { return lectureId; }
    public String getTitle() { return title; }
    public String getInstructorName() { return instructorName; }
    public int getViewCount() { return viewCount; }
    public int getLikeCount() { return likeCount; }

    public void setLectureId(int lectureId) { this.lectureId = lectureId; }
    public void setTitle(String title) { this.title = title; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }

}
