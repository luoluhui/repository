package com.luolh.blog.common;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author LuoLH
 * @since 20180602
 */
public class BaseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 请求传入的参数
	 * */
	private final String METHOD_NAME = "method";

	/**
	 * 请求传入的参数
	 * */
	private final String PARAM_MESSAGE = "请传入method参数";

	/**
	 * 请求传入的参数
	 * */
	private final String METHOD_NOT_FOUND_MESSAGE = "不存在该方法";

	/**
	 * 冒号
	 * */
	private final String COLON = ":";

	/**
	 * 转发  f
	 * */
	private final String FORWARD = "f";

	/**
	 * 重定向 r
	 * */
	private final String REDIRECT = "r";

	/**
	 * 异常信息
	 * */
	private final String EXECPTION_MESSAGE = "页面访问升级";

	/**
	 *通过method参数调用与之对应的方法
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 规定每次请求都要传入参数method
		// 获取method参数
		String methodName = request.getParameter(METHOD_NAME);
		if (StringUtils.isEmpty(methodName)) {
			throw new RuntimeException(PARAM_MESSAGE);
		}
		// 利用反射进行方法调用
		Class<? extends BaseController> clazz = this.getClass();
		Method method = null;
		try {
			method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException(METHOD_NOT_FOUND_MESSAGE + methodName);
		}
		try {
			// return f:/add.jsp 等于跳转到add.jsp
			String url = (String) method.invoke(this, request, response);
			if (StringUtils.isEmpty(url)) {
				return;
			} else if (url.contains(COLON)) {

				int index = url.indexOf(COLON);
				String prefix = url.substring(0, index);
				String path = url.substring(index + 1);
				if (prefix.equals(FORWARD)) {
					request.getRequestDispatcher(path).forward(request, response);
				} else if (prefix.equals(REDIRECT)) {
					// TODO
					response.sendRedirect(path);
				} else {
					throw new RuntimeException(EXECPTION_MESSAGE);
				}
			} else {
				request.getRequestDispatcher(url).forward(request, response);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
