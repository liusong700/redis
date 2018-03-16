package com.test.redis.service;

import com.test.redis.entity.Member;
import com.test.redis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<Member> getAll() {
        return memberMapper.getAll();
    }

    public Member findByLoginName(String userName){
        return memberMapper.findByLoginName(userName);
    }

    public int updateLoginInfo(Member member) {
        return memberMapper.updateLoginInfo(member);
    }
}
