package com.example.torder.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchingForm {
    // content.js에서 받아온 사용자 id, contentid를 저장.
    private String userid, contentid;
}
