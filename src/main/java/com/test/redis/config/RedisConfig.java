package com.test.redis.config;

import com.test.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@Slf4j
public class RedisConfig {

    private StringRedisTemplate template;

    @Autowired
    public RedisConfig(StringRedisTemplate template) {
        log.info("init StringRedisTemplate begin");
        this.template = template;
        RedisUtil.setRedisTemplate(template);
        log.info("init StringRedisTemplate success");
    }
}
