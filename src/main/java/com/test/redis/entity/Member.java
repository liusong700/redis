package com.test.redis.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Member implements Serializable {

    private static final long serialVersionUID = -458523363252917938L;

    private int id;
    private String loginName;
    private String password;
    private String nickName;
    private String realName;
    private Date birthday;
    private int mobile;
    private int sex;
    private String token;
    private Date loginTime;

}
