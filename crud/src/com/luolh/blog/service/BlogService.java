package com.luolh.blog.service;

import java.util.List;

import com.luolh.blog.entities.Blog;

/**
 * BlogService业务处理
 * @author LuoLH
 * @since 20180529
 */
public interface BlogService {

	/**
	 * 查询所有博客
	 * @return 博客list
	 */
	List<Blog> findAll(int pageNo,int pageNumber);
	
	int totalCount();
	/**
	 * @param blogId
	 * @return
	 */
	Blog findById(int blogId);
	
	/**
	 * 添加
	 * @param blog
	 */
	void addBlog(Blog blog);
	
	/**
	 * 修改
	 * @param blog
	 */
	void updateBlog(Blog blog);
	
	/**
	 * 删除
	 * @param blogId
	 */
	void deleteByBlogId(int blogId);

	/**
	 * 多条件组合查询
	 * @param blog
	 * @return 
	 */
	List<Blog> query(Blog blog, int pageNo, int pageNumber);

	/**
	 * @param blog
	 * @return
	 */
	int totalCountWithCondition(Blog blog);
}
