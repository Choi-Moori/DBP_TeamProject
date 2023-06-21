package com.example.torder.controller;

import com.example.torder.domain.NowContent;
import com.example.torder.service.NowContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyController {

    private final NowContentService nowcontentService;

    @Autowired
    public MyController(NowContentService nowcontentService) {
        this.nowcontentService = nowcontentService;
    }

    // 테이블의 내용이 클릭되었을때 변경하도록 한다. 즉 경로 수정 필요함.
    @GetMapping("/main/{contentId}/page")
    public String mypage(@PathVariable int contentId, Model model) {
        System.out.println("start---------------------");
        NowContent content = nowcontentService.findContent(contentId);
        model.addAttribute("content", content);

        return "page";
    }

}
