<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.luolh.blog.entities.Blog"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发表</title>
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
		<% Blog blog = (Blog)request.getAttribute("blog"); %>
		<div class="row">
			<form class="form-horizontal" action="blog" method="post">
				<input type="hidden" name="method" value="updateBlog">
				<input type="hidden" name="blogId" value="<%= blog.getBlogId()%>">
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">标题</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="blogTitle"
							placeholder="标题" value="<%= blog.getBlogTitle()%>">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">内容</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="3" name="content"><%= blog.getContent() %></textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">类型</label>
					<div class="col-sm-10">
						<select class="form-control" name="blogType">
							<option value="<%= blog.getBlogType()%>"><%= blog.getBlogType()%></option>
							<option value="diary">diary</option>
							<option value="technology">technology</option>
							<option value="life">life</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">修改</button>
						<a href="blog?method=list">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>