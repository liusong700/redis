package com.test.redis.util;


import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static RedisTemplate redisTemplate;

    public static void setRedisTemplate(RedisTemplate _redisTemplate) {
        redisTemplate = _redisTemplate;
    }

    /**
     * 获取值
     */
    public static <T> T getValue(String key, Class<T> clazz) {
        Object object = redisTemplate.opsForValue().get(key);
        if (object == null) {
            return null;
        }
        return JsonUtil.fromJsonToObject(object.toString(), clazz);
    }


    public static String getValue(String key) {
        //解决部分String类型泛型转换失败问题
        Object object = redisTemplate.opsForValue().get(key);
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    public static <T> List<T> getValue(String key, Class<List> listClass, Class<T> clazz) {

        Object object = redisTemplate.opsForValue().get(key);
        if (object == null) {
            return null;
        }
        return JsonUtil.fromJsonToList(object.toString(), listClass, clazz);
    }

    /**
     * 获取值
     */
    public static <T> T getValue(String key, Object hashKey, Class<T> clazz) {
        Object object = redisTemplate.opsForHash().get(key, hashKey);
        if (object == null) {
            return null;
        }
        return JsonUtil.fromJsonToObject(object.toString(), clazz);
    }

    /**
     * 获取值
     */
    public static String getValue(String key, Object hashKey) {
        Object object = redisTemplate.opsForHash().get(key, hashKey);
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    /**
     * 设置值
     */
    public static void setValue(String key, Object object) {
        if(object instanceof String){
            redisTemplate.opsForValue().set(key, object);
        }
        else {
            redisTemplate.opsForValue().set(key, JsonUtil.toJson(object));
        }
    }

    /**
     * 设置值
     */
    public static void setValue(String key, Object object, long time, TimeUnit timeUnit) {
        if(object instanceof String){
            redisTemplate.opsForValue().set(key, object, time, timeUnit);
        }
        else {
            redisTemplate.opsForValue().set(key, JsonUtil.toJson(object), time, timeUnit);
        }
    }

    /**
     * 设置值
     */
    public static void setValue(String key, Object hashKey, Object object) {
        if(object instanceof String){
            redisTemplate.opsForHash().put(key, hashKey, object);
        }
        else {
            redisTemplate.opsForHash().put(key, hashKey, JsonUtil.toJson(object));
        }
    }

    /**
     * 设置过期时间
     */
    public static boolean expire(String key, long time, TimeUnit timeUnit) {
        return redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 设置指定时间过期
     */
    public static boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 获取过期时间
     */
    public static Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 删除
     */
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 指定键位自增
     */
    public static void increment(String key, Object hashKey, long delta) {
        redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * 设置键值
     * 如果key不存在，则设置成功
     */
    public static Boolean setIfAbsent(String key, Object object) {
        return redisTemplate.opsForValue().setIfAbsent(key, object);
    }
}
