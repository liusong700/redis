package com.test.redis.controller;

import com.test.redis.entity.Member;
import com.test.redis.service.MemberService;
import com.test.redis.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("login")
    public String login(HttpServletRequest request) {
        String result = "login";
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            Member member = memberService.findByLoginName(userName);
            if (member != null) {
                if (member.getPassword().equals(password)) {
                    result = "index";
                    if (StringUtils.isNotBlank(member.getToken())) {
                        if (StringUtils.isNotBlank(RedisUtil.getValue(member.getToken()))) {
                            RedisUtil.delete(member.getToken());
                        }
                    }
                    String token = request.getSession().getId().replace("-", "");
                    RedisUtil.setValue(token, request.getSession().getId(), 30, TimeUnit.MINUTES);
                    member.setToken(token);
                    member.setLoginTime(new Date());
                    memberService.updateLoginInfo(member);
                } else {
                    request.setAttribute("message", "密码错误");
                }
            } else {
                request.setAttribute("message", "用户不存在");
            }
        } else {
            request.setAttribute("message", "用户名密码不能为空");
        }
        return result;
    }

    @RequestMapping("unlisted")
    public String unlisted(HttpServletRequest request) {
        request.setAttribute("message", "未登录或登录失效");
        return "login";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("first")
    public String first() {
        return "first";
    }
}
