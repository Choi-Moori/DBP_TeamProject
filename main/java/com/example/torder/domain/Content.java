package com.example.torder.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Content {
    // db에 저장된 content내용을 저장해서 특정 정보 추출할 때 사용
    private int PK_content_id, category_id;
    private String title, body, location, end_time;
    private boolean content_state;
}