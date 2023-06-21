package com.example.torder.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    // loginForm.html 에서 입력한 id, pw을 저장.
    private String Input_id, Input_password;
}
