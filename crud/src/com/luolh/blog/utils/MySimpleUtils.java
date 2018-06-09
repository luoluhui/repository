package com.luolh.blog.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 简易工具类
 * @author LuoLH
 * @since 20180601
 */
public class MySimpleUtils {
	
	/**
	 * 将map转化为实体类（依赖BeanUtils）
	 * @param map
	 * @param clazz
	 * @return 对应实体类
	 */
	public static <T> T convertMapToBean(Map<String, ?> map, Class<T> clazz) {
		T bean = null;
		try {
			bean = clazz.newInstance();
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
