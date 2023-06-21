package com.example.torder.controller;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.torder.domain.Member;
import com.example.torder.service.MemberService;

@Controller
public class HomeController {
    Member member = new Member();
    private final MemberService memberService;

    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 홈페이지 불러오는 함수
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 로그인 화면 불러오는 함수
    @GetMapping("/login/new")
    public String loginForm() {
        memberService.existMembersave();
        return "logForm";
    }

    /* 로그인 ID 확인 */
    @PostMapping("/login")
    public String checkLogin(@RequestBody Map<String, String> data) {
        member.setId(data.get("inputID"));
        member.setPassword(data.get("inputPW"));
        return "redirect:/login";
    }

    // 로그인 성공 시 login사용자 정보 전달하는 함수
    @GetMapping("/login")
    @ResponseBody
    public String checkLoginHandler() {
        JSONArray ja = new JSONArray(); // [] 대괄호 생성
        JSONObject jo = new JSONObject(); // {} 중괄호 생성

        jo.put("id", member.getId());
        jo.put("password", member.getPassword());
        jo.put("nickname", member.getNickname());

        if (memberService.Login(member)) {
            jo.put("login", "true");
        } else {
            jo.put("login", "false");
        }
        ja.add(jo);
        return ja.toJSONString();
    }

    /* ID 생성 및 중복확인 */
    @PostMapping("/login/id/new")
    public String createID(@RequestBody Map<String, String> data) {
        member.setId(data.get("id"));
        memberService.join(member);
        return "redirect:/login/id/new";
    }

    /* ID 생성가능여부 판단 */
    @GetMapping("login/id/new")
    @ResponseBody
    public String createIDHandler() {
        if (member.getId().equals("")) {
            return "false";
        } else {
            return "true";
        }
    }

    /* PW 생성 여부 */
    @PostMapping("/login/pw/new")
    public String createPW(@RequestBody Map<String, String> data) {
        if (data.get("pwd1").equals(data.get("pwd2"))) {
            member.setPassword(data.get("pwd2"));
        } else {
            member.setPassword("");
        }
        return "redirect:/login/pw/new";
    }

    /* PW 일치 여부 반환 */
    @GetMapping("/login/pw/new")
    @ResponseBody
    public String createPWHandler() {
        if (member.getPassword().equals("")) {
            return "false";
        } else {
            return "true";
        }
    }

    /* 닉네임 생성 및 중복확인 */
    @PostMapping("/login/nick/new")
    public String createNick(@RequestBody Map<String, String> data) {
        member.setNickname(data.get("nickname"));
        memberService.join(member);
        return "redirect:/login/nick/new";
    }

    /* 닉네임 생성가능여부 판단 */
    @GetMapping("login/nick/new")
    @ResponseBody
    public String createNickHandler() {
        if (member.getNickname().equals("")) {
            return "false";
        } else {
            return "true";
        }
    }

    /* 회원 생성 후 db 저장 */
    @GetMapping("/login/check/new")
    @ResponseBody
    public String createMember() {
        if (member.getId().equals("") ||
                member.getPassword().equals("") ||
                member.getNickname().equals("")) {
            return "false";
        } else {
            memberService.save(member);
            return "true";
        }
    }
}
