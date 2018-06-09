package com.luolh.blog.service.impl;

import java.util.List;

import com.luolh.blog.dao.BlogDao;
import com.luolh.blog.entities.Blog;
import com.luolh.blog.service.BlogService;

/**
 * blog service实现类
 * @author LuoLH
 * @since 20180529
 */
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao = new BlogDao();
	
	/**
	 * 查询所有博客
	 * */
	@Override
	public List<Blog> findAll(int pageNo,int pageNumber) {
		List<Blog> blog = blogDao.findAll(pageNo, pageNumber);
		return blog;
	}
	
	/**
	 * 添加
	 * */
	@Override
	public void addBlog(Blog blog) {
		blogDao.addBlog(blog);
	}
	
	/**
	 * 修改
	 * */
	@Override
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
	}
	
	/**
	 * 删除
	 * */
	@Override
	public void deleteByBlogId(int blogId) {
		blogDao.deleteByBlogId(blogId);
	}

	/**
	 *通过id查找
	 */
	@Override
	public Blog findById(int blogId) {
		return blogDao.findById(blogId);
	}

	/**
	 * 多条件组合查询
	 */
	@Override
	public List<Blog> query(Blog blog, int pageNo, int pageNumber) {
		return blogDao.query(blog, pageNo, pageNumber);
	}

	/**
	 *查询总条数
	 */
	@Override
	public int totalCount() {
		return blogDao.totalCount();
	}

	@Override
	public int totalCountWithCondition(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.totalCountWithCondition(blog);
	}

	

}
