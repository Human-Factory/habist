package com.hab.hobbymarket.model;

public class LectureCreateRequest {

    private int categoryId;
    private String title;
    private String description;

    public LectureCreateRequest(int categoryId, String title, String description) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
    }

    // ===== Getter =====
    public int getCategoryId() { return categoryId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}