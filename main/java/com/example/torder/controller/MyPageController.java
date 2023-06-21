package com.example.torder.controller;

import com.example.torder.domain.MyUserPage;
import com.example.torder.service.MyPageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MyPageController {
    private final MyPageUserService userService;

    @Autowired
    public MyPageController(MyPageUserService userService) {
        this.userService = userService;
    }

    // 마이페이지를 클릭했을 때 화면이 전환되도록 한다.
    @GetMapping("/mypage/{userId}")
    public String mypage(@PathVariable String userId, Model model) {
        System.out.println("start---------------------");
        MyUserPage.Simple user = userService.findUser(userId);
        model.addAttribute("user_id", user.getUserId());
        model.addAttribute("nickname", user.getNickname());

        List<MyUserPage.Matching> myMatching = userService.findMatching(user.getPKUserId());
        if (myMatching.isEmpty()) {
            model.addAttribute("Empty", "참여 내역 없음");
        } else {
            model.addAttribute("myMatching", myMatching);
        }
        return "mypage";
    }

    @GetMapping("/mypage/{matchingId}/delete")
    public String getContentDeleteForm(@PathVariable Long matchingId) {
        userService.deleteMatching(matchingId);
        return "redirect:/login/main";
    }

}
