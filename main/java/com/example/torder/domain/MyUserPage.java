package com.example.torder.domain;

import lombok.Data;

public class MyUserPage {
    @Data
    public static class Simple{
        private Long PKUserId;
        private String userId;
        private String nickname;
    }

    @Data
    public static class Matching{
        private Long PKMatchingId;
        private Long PKContentId;
        private String title;
    }

}
