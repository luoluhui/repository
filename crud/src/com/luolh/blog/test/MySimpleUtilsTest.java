package com.luolh.blog.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.luolh.blog.entities.Blog;
import com.luolh.blog.utils.MySimpleUtils;

/**
 * 简易工具测试类
 * @author LuoLH
 * @since 20180601
 */
public class MySimpleUtilsTest {

	@Test
	public void testConvertMapToBean() {
		Map<String,String> map = new HashMap<>();
		map.put("blogTitle", "哈哈");
		Blog blog = MySimpleUtils.convertMapToBean(map, Blog.class);
		System.out.println(blog);
	}
}
