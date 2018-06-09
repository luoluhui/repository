package com.luolh.blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.luolh.blog.common.BaseController;
import com.luolh.blog.entities.Blog;
import com.luolh.blog.entities.Page;
import com.luolh.blog.factory.ServiceFactory;
import com.luolh.blog.service.BlogService;
import com.luolh.blog.utils.MySimpleUtils;
import com.luolh.blog.utils.PageUtils;

/**
 * Servlet implementation class UserController
 */
public class BlogController extends BaseController {

	private static final long serialVersionUID = 1L;

	/**
	 * 每页分页条显示数
	 */
	private static final int PAGE_NUMBER = 5;

	/**
	 * 每页显示条数
	 */
	private static final int PAGE_COUNT = 5;

	/**
	 * 工厂方法获取BlogService实例
	 * */
	private BlogService blogService = ServiceFactory.getServiceInstance();

	/**
	 * 首页显示
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page<Blog> page = new Page<>();
		// 总条数
		int totalCount = blogService.totalCount();
		page = getPage(request, totalCount);
		List<Blog> list = blogService.findAll(page.getPageNo(), page.getPageNumber());
		page.setList(list);
		request.setAttribute("page", page);
		return "f:/home.jsp";
	}

	/**
	 * 添加博客
	 * @param request
	 * @param response
	 * @return 首页
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		Blog blog = MySimpleUtils.convertMapToBean(map, Blog.class);
		blogService.addBlog(blog);
		return "f:/blog?method=list";
	}

	/**
	 * 通过id查找
	 * @param request
	 * @param response
	 * @return 编辑页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int blogId = Integer.valueOf(request.getParameter("blogId")).intValue();
		Blog blog = blogService.findById(blogId);
		request.setAttribute("blog", blog);
		return "f:/edit.jsp";
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 * @return 首页
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateBlog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		Blog blog = MySimpleUtils.convertMapToBean(map, Blog.class);
		blogService.updateBlog(blog);
		return "f:/blog?method=list";
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return 首页
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int blogId = Integer.valueOf(request.getParameter("deleteBlogId")).intValue();
		blogService.deleteByBlogId(blogId);
		return "f:/blog?method=list";
	}

	/**
	 * 多条件组合查询
	 * @param request
	 * @param response
	 * @return 首页
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page<Blog> page = new Page<>();
		
		Map<String, String[]> map = request.getParameterMap();
		Blog blog = MySimpleUtils.convertMapToBean(map, Blog.class);
		
		int totalCount = blogService.totalCountWithCondition(blog);
		System.out.println(totalCount);
		page = getPage(request, totalCount);
		
		List<Blog> list = blogService.query(blog, page.getPageNo(), page.getPageNumber());
		page.setList(list);
		
		request.setAttribute("page", page);
		return "f:/home.jsp";
	}

	/**
	 * 获取pageBean
	 * @param request
	 * @param totalCount
	 * @return pageBean
	 */
	private static Page<Blog> getPage(HttpServletRequest request, int totalCount) {
		Page<Blog> page = new Page<>();
		page.setTotalCount(totalCount);
		// 当前页码
		String pageNoParam = request.getParameter("pageNo");
		int pageNo = 1;
		if (!StringUtils.isEmpty(pageNoParam)) {
			pageNo = Integer.valueOf(pageNoParam).intValue();
		}
		page.setPageNo(pageNo);
		// 每页显示条数
		int pageCount = PAGE_COUNT;
		page.setPageCount(pageCount);
		// 获取分页条列表
		page.setPageNumber(PAGE_NUMBER);
		int totalPage = page.getTotalPage();
		int pageNumber = page.getPageNumber();
		List<Integer> numberList = PageUtils.getNumberList(pageNumber, pageNo, totalPage);
		page.setNumberList(numberList);
		//获取url
		page.setUrl(getUrl(request));
		return page;
	}
	
	/**
	 * @param request
	 * @return
	 */
	private static String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String queryPath = request.getQueryString();
		if(queryPath.contains("&pageNo=")) {
			int index = queryPath.lastIndexOf("&pageNo=");
			queryPath = queryPath.substring(0, index);
		}
		String url = contextPath + servletPath + "?" + queryPath;
		return url;
	}
}
