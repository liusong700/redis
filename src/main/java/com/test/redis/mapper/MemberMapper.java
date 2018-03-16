package com.test.redis.mapper;

import com.test.redis.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    Member findByLoginName(String username);

    List<Member> getAll();

    int updateLoginInfo(Member member);
}
