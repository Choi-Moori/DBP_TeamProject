package com.example.torder.domain;

import java.sql.Time;

import lombok.Data;

@Data
public class NowContent {
    private int PKContentId;
    private int categoryId;
    private String title;
    private String body;
    private Time endTime;
    private String location;
    private int contentState;
}