package com.example.torder.controller;

import com.example.torder.entity.Contents;
import com.example.torder.service.WriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class WriteController {
    private final WriteService writeService;

    @Autowired
    public WriteController(WriteService writeService) {
        this.writeService = writeService;
    }

    // http://localhost:8080/post/createform
    // 입력 폼 화면으로 이동 (write.html)
    @GetMapping("/createform")
    public String showWriteForm() {
        return "write";
    }

    // http://localhost:8080/post/create
    // 버튼 누르면 해당 post DB에 저장.
    // 아래 log가 뜨면 성공적으로 저장됨을 알림.
    // Hibernate: insert into contents (body, category_id, content_state, end_time,
    // location, title) values (?, ?, ?, ?, ?, ?)
    @PostMapping("/create")
    public String saveContents(Model model, @ModelAttribute("contents") Contents contents,
            @RequestParam("category") String category,
            @RequestParam("title") String title,
            @RequestParam("body") String body,
            @RequestParam("location") String location,
            @RequestParam("endTime") String endTime) {

        model.addAttribute("contents", new Contents());

        contents.setCategory(category);
        contents.setTitle(title);
        contents.setBody(body);
        contents.setLocation(location);
        contents.setEndTime(endTime);

        contents.setContentState(true);
        writeService.saveContents(contents);
        return "redirect:/login/main";
        // 일단 다시 생성 폼으로 리다이렉트 해놨음.
        // 글을 생성한 뒤에 자신의 글이 보이게 하거나
        // 전체 게시판으로 리다이렉트 해야됨. (return 고쳐야된다는 뜻)
    }
}