package com.example.torder.service;

import com.example.torder.domain.MyUserPage;
import com.example.torder.entity.MyPageMatchingEntity;
import com.example.torder.entity.MyPageUserEntity;
import com.example.torder.repository.MyPageUserRepository;

import java.util.ArrayList;
import java.util.List;

public class MyPageUserService {

    private final MyPageUserRepository userRepository;

    public MyPageUserService(MyPageUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public MyUserPage.Simple findUser(String user_id){
        MyPageUserEntity myUserEntity = userRepository.findById(user_id);
        MyUserPage.Simple user = new MyUserPage.Simple();
        System.out.println("nickname : " + myUserEntity.getNickname());
        System.out.println("user_id : " + myUserEntity.getUserId());
        System.out.println("PK_user_id : " + myUserEntity.getPKUserId());
        user.setPKUserId(myUserEntity.getPKUserId());
        user.setUserId(myUserEntity.getUserId());
        user.setNickname(myUserEntity.getNickname());

        return user;
    }

    public List<MyUserPage.Matching> findMatching(Long PK_user_id){
        List<MyUserPage.Matching> list = new ArrayList<>();
        for(MyPageMatchingEntity matchingEntity : userRepository.findMatching(PK_user_id)){
            MyUserPage.Matching user = new MyUserPage.Matching();
            user.setPKMatchingId(matchingEntity.getPKMatchingId());
            user.setPKContentId(matchingEntity.getContentsId());
            user.setTitle(matchingEntity.getTitle());
            list.add(user);
        }
        return list;
    }

    public void deleteMatching(Long matchingId){
        userRepository.delete(matchingId);
    }
}
