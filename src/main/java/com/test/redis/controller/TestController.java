package com.test.redis.controller;

import com.test.redis.entity.Member;
import com.test.redis.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public String getMembers(HttpServletRequest request){
        List<Member> members = memberService.getAll();
        return JSONArray.fromObject(members).toString();
    }

}
