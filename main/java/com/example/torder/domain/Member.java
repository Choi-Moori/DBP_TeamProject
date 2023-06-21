package com.example.torder.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    // 회원가입 창에서 입력한 id, pw, nick 정보 저장
    private String id, password, nickname;
    private boolean user_state;

    public void drop() {
        id = "";
        password = "";
        nickname = "";
    }
}
