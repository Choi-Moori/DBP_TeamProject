package com.example.torder.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "matching")
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PKContentId;

    @Column
    private int categoryId;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private Time endTime;

    @Column
    private String location;

    @Column
    private int contentState;
}
