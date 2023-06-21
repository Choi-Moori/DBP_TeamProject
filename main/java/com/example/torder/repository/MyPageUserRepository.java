package com.example.torder.repository;

import com.example.torder.entity.MyPageMatchingEntity;
import com.example.torder.entity.MyPageUserEntity;

import java.util.List;

public interface MyPageUserRepository {

    MyPageUserEntity findById(String user_id);
    List<MyPageMatchingEntity> findMatching(Long PK_user_id);

    void delete(Long matchingId);
}
