<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>
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
		<div class="row">
			<form class="form-horizontal" action="blog" method="get">
				<input type="hidden" name="method" value="query">
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">标题</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="blogTitle"
							placeholder="标题">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">内容</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="3" name="content"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">类型</label>
					<div class="col-sm-10">
						<select class="form-control" name="blogType">
							<option></option>
							<option value="diary">diary</option>
							<option value="technology">technology</option>
							<option value="life">life</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">查询</button>
						<button type="reset" class="btn btn-primary">重置</button>
						<a href="blog?method=list">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>