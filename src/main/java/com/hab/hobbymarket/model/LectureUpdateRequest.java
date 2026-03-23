package com.hab.hobbymarket.model;

// 강의 수정(Update) 요청용 DTO

public class LectureUpdateRequest {

    // 수정할 강의 번호
    private int lectureId;

    // 변경할 카테고리 번호
    private int categoryId;

    // 변경할 강의 제목
    private String title;

    // 변경할 강의 설명
    private String description;

    // 수정 요청 생성자
    public LectureUpdateRequest(int lectureId, int categoryId, String title, String description) {
        this.lectureId = lectureId;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
    }

    public int getLectureId() {
        return lectureId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}