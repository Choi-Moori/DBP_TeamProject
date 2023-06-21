package com.example.torder.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "contents")
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_content_id")
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "location")
    private String location;

    @Column(name = "content_state")
    private boolean contentState;

    public void setCategory(String category) {
        // 카테고리 값에 따라 categoryId 설정
        int categoryId;
        switch (category) {
            case "한식":
                categoryId = 2;
                break;
            case "일식":
                categoryId = 3;
                break;
            case "양식":
                categoryId = 4;
                break;
            case "중식":
                categoryId = 5;
                break;
            case "카페":
                categoryId = 6;
                break;
            case "패스트푸드":
                categoryId = 7;
                break;
            default:
                categoryId = 1;
        }
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContentState(boolean contentState) {
        this.contentState = contentState;
    }

    public boolean isContentState() {
        return contentState;
    }
}
