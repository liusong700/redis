package com.test.redis.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * json处理工具类
 * @author ruiliang.zhang
 */
@Ignore
class JsonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	/**
	 * 对象转换成JSON字符串
	 */
    static String toJson(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("convert obj to json error. obj="+obj, e);
		}
		return null;
	}

    static <T> T fromJsonToObject(String json, Class<T> clazz) {
		if(null==json || json.trim().equals("")) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json.trim(), clazz);
		} catch (Exception e) {
			logger.error("convert json to object error. json="+json, e);
		}
		return null;
	}
	
	/**
	 * 将json字符串转换成List
	 */
	static <T> List<T> fromJsonToList(String value, Class<List> listClass, Class<T> objClass) {
		List<T> list = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			list = mapper.readValue(value, getCollectionType(mapper, listClass, objClass));
		} catch (Exception e) {
			logger.error("convert json to list error. value="+value, e);
		}
		return list;
	}
	
	/**
	 * 获取类型
	 */
    static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	/**
	 * JSON转对象，其中排除字段内容
	 */
    static <T> T fromJsonToObject(String json, Class<T> clazz,String ...filterName) {
		if(null==json || json.trim().equals("")) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter", SimpleBeanPropertyFilter.filterOutAllExcept(filterName));
			mapper.setFilterProvider(filterProvider);
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("convert json to object error. json="+json, e);
		}
		return null;
	}
}
