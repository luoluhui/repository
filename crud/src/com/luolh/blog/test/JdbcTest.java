package com.luolh.blog.test;

import java.util.List;

import org.junit.Test;

import com.luolh.blog.entities.Blog;
import com.luolh.blog.service.impl.BlogServiceImpl;

/**
 * 测试数据库连接
 * @author LuoLH
 * @since 20180528
 */
public class JdbcTest {

	@Test
	public void test() {

//		String sql = "INSERT INTO blog values(?,?,?,?,?,?)";
//		String sql = "UPDATE blog SET create_time=?";
//		String sql ="SELECT blog_id blogId, blog_title blogTitle,create_time createTime,content,visit_times visitTimes FROM blog WHERE blog_id = ?";
//		String sql ="SELECT blog_id blogId, blog_title blogTitle,create_time createTime,content,visit_times visitTimes FROM blog";
//		JdbcUtils.update(sql, 2, "new start",new Timestamp(System.currentTimeMillis()),"what a beautifur day!","日记",0L);
		/*List<Blog> blog = new BlogServiceImpl().findAll();
		System.out.println(blog);*/
	}
}
