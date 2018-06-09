<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.luolh.blog.entities.Blog"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客管理系统</title>
<%@ include file="common-page/import.jsp"%>
<style type="text/css">
.container {
	margin: auto;
}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="common-page/head.jsp"%>
		<!--  -->
		<div class="row">
			<div class="col-md-10">
				<a href="add.jsp">发表</a>
			</div>
			<div class="col-md-2" style="text-align: left">
				<a href="search.jsp">条件搜索</a>
			</div>
		</div>
		<div class="clearfix" style="margin-bottom: 1%;"></div>
		<!-- 表格区 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-bordered">
					<tr>
						<th>#</th>
						<th>标题</th>
						<th>发表时间</th>
						<th>内容</th>
						<th>类型</th>
						<th>访问次数</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${page.list}" var="list">
						<tr>
							<td>${list.blogId}</td>
							<td>${list.blogTitle}</td>
							<td><fmt:formatDate value="${list.createTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${list.content}</td>
							<td>${list.blogType}</td>
							<td>${list.visitTimes}</td>
							<td><a href="blog?method=findById&blogId=${list.blogId}">修改</a>
								<a href="blog?method=deleteById&deleteBlogId=${list.blogId}">删除</a>
							</td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
		<!-- 分页条区 -->
		<div class="row">
			<div class="col-md-8">
				<nav aria-label="Page navigation">
				<ul class="pagination">
					<c:if test="${page.pageNo>1}">
						<li><a href="${page.url}&pageNo=1"><span>首页</span></a></li>
						<li><a href="${page.url}&pageNo=${page.pageNo-1}"><span>&laquo;</span></a></li>
					</c:if>
					<c:forEach items="${page.numberList}" var="number">
						<li><a href="${page.url}&pageNo=${number}">${number}</a></li>
					</c:forEach>
					<c:if test="${page.pageNo < page.totalPage}">
						<li><a href="${page.url}&pageNo=${page.pageNo+1}"><span>&raquo;</span></a></li>
						<li><a href="${page.url}&pageNo=${page.totalPage}"><span>尾页</span></a></li>
					</c:if>
				</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>