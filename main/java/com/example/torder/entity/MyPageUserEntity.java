package com.example.torder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user")
public class MyPageUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PKUserId;

    @Column
    private String userId;

    @Column
    private String nickname;

}
