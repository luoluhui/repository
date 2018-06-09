package com.luolh.blog.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.luolh.blog.entities.Blog;
import com.luolh.blog.utils.JdbcUtils;

/**
 * 数据处理Dao
 * @author LuoLH
 * @since 20180529
 */
public class BlogDao {

	/**
	 * 查询所有数据sql
	 * */
	private final String SELECT_ALL_SQL = "SELECT blog_id blogId, blog_title blogTitle,create_time createTime,"
			+ "content,blog_type blogType, visit_times visitTimes FROM blog WHERE 1=1";

	private final String LIMIT_SQL = " limit ?,?";
	/**
	 * 通过id查找
	 * */
	private final String SELECT_BY_ID_SQL = "SELECT blog_id blogId, blog_title blogTitle,create_time createTime,"
			+ "content,blog_type blogType, visit_times visitTimes FROM blog WHERE blog_id=?";

	/**
	 * 添加sql
	 * */
	private final String ADD_SQL = "INSERT INTO blog(blog_title,content,blog_type) VALUES(?,?,?)";

	/**
	 * 修改sql
	 * */
	private final String UPDATE_SQL = "UPDATE blog SET blog_title=?, content=?,blog_type=? WHERE blog_id = ?";

	/**
	 * 删除sql
	 * */
	private final String DELETE_SQL = "DELETE FROM blog WHERE blog_id = ?";

	/**
	 * 查询总条数sql
	 * */
	private final String COUNT_SQL = "SELECT COUNT(*) FROM blog WHERE 1=1";

	/**
	 * 查询所有数据
	 * @return Blog list
	 * */
	public List<Blog> findAll(int pageNo, int pageNumber) {
		int index = pageNo == 1 ? 0 : (pageNo - 1) * pageNumber;
		StringBuilder sql = new StringBuilder(SELECT_ALL_SQL);
		sql.append(LIMIT_SQL);
		List<Blog> list = JdbcUtils.queryAll(Blog.class, sql.toString(), index, pageNumber);
		return list;
	}

	/**
	 * 查询总条数
	 * @return 总条数
	 * */
	public int totalCount() {
		return JdbcUtils.totalCount(COUNT_SQL);
	}

	public int totalCountWithCondition(Blog blog) {
		String blogTitle = blog.getBlogTitle();
		String content = blog.getContent();
		String blogType = blog.getBlogType();
		// 查询主要部分
		StringBuilder sql = new StringBuilder(COUNT_SQL);

		// 存放查询条件
		List<Object> conditions = new ArrayList<>();

		// 判断查询条件
		if (!StringUtils.isEmpty(blogTitle)) {
			sql.append(" and blog_title like ?");
			conditions.add("%" + blogTitle + "%");
		}
		if (!StringUtils.isEmpty(content)) {
			sql.append(" and content like ?");
			conditions.add("%" + content + "%");
		}
		if (!StringUtils.isEmpty(blogType)) {
			sql.append(" and blog_type = ?");
			conditions.add(blogType);
		}
		return JdbcUtils.totalCount(sql.toString(), conditions.toArray());
	}

	/**
	 * 通过id查找
	 * @param blogId
	 * @return blog实体
	 */
	public Blog findById(int blogId) {
		return JdbcUtils.query(Blog.class, SELECT_BY_ID_SQL, blogId);
	}

	/**
	 * 添加数据
	 * */
	public void addBlog(Blog blog) {

		JdbcUtils.update(ADD_SQL, blog.getBlogTitle(), blog.getContent(), blog.getBlogType());
	}

	/**
	 * 修改数据
	 * */
	public void updateBlog(Blog blog) {
		JdbcUtils.update(UPDATE_SQL, blog.getBlogTitle(), blog.getContent(), blog.getBlogType(), blog.getBlogId());
	}

	/**
	 * 删除数据
	 * */
	public void deleteByBlogId(int blogId) {
		JdbcUtils.update(DELETE_SQL, blogId);
	}

	/**
	 * 多条件组合查询
	 * @param blog
	 */
	public List<Blog> query(Blog blog, int pageNo, int pageNumber) {
		String blogTitle = blog.getBlogTitle();
		String content = blog.getContent();
		String blogType = blog.getBlogType();
		// 查询主要部分
		StringBuilder sql = new StringBuilder(SELECT_ALL_SQL);

		// 存放查询条件
		List<Object> conditions = new ArrayList<>();

		// 判断查询条件
		if (!StringUtils.isEmpty(blogTitle)) {
			sql.append(" and blog_title like ?");
			conditions.add("%" + blogTitle + "%");
		}
		if (!StringUtils.isEmpty(content)) {
			sql.append(" and content like ?");
			conditions.add("%" + content + "%");
		}
		if (!StringUtils.isEmpty(blogType)) {
			sql.append(" and blog_type = ?");
			conditions.add(blogType);
		}
		sql.append(LIMIT_SQL);
		int index = pageNo == 1 ? 0 : (pageNo - 1) * pageNumber;
		conditions.add(index);
		conditions.add(pageNumber);
		
		// 执行sql语句
		return JdbcUtils.queryAll(Blog.class, sql.toString(), conditions.toArray());
	}
}
