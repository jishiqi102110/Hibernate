<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>SearchPage</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/Transaction.css" rel="stylesheet">
<script src="js/jquery-2.2.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="header" id="header" style="margin-top: 20px">
		<div class="navbar-default" id="header-top">
			<div id="top-left">
				<ul class="nav nav-pills" role="tablist">
					<li role="presentation" class="active"><a href="#">后台中心</a></li>
					<li role="presentation"><a href="#">退出</a></li>
					<c:choose>
						<c:when test="${sessionScope.user!=null}">
							<li role="presentation"><a href="login.jsp">欢迎你<font
									style="color: red">${sessionScope.user.name}</font></a></li>
						</c:when>
						<c:otherwise>
							<li role="presentation"><a href="login.jsp">登录</a></li>
							<li role="presentation"><a href="register.jsp">注册</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div id="top-right">
				<ul class="nav nav-pills" role="tablist">
					 <li role="presentation"><a href="IssueHeart.jsp">发布物品</a></li>
                  <li role="presentation"><a href="#">需求发布</a></li>
                  <li role="presentation"><a href="PersonalGoodsMagager.do">物品管理</a></li>
                  <li role="presentation"><a href="#">个人交易</a></li>
					<li role="presentation">
						<div class="dropdown">
							<button class="btn btn-default dropdown-toggle" type="button"
								id="dropdownMenu1" data-toggle="dropdown">
								联系我们 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="dropdownMenu1">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#">电话：*************</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#">邮箱：*************</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#">传真：*************</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div style="clear: both">
		<hr style="height: 5px; border: none; border-top: 5px ridge green;" />
	</div>
	<div class="row">
		<div class="col-md-offset-1 col-md-2" style="margin-left: 110px">
			<img src="images/logo.jpg">
		</div>
		<div class="col-md-2 col-md-offset-6" style="margin-top: 10px">
			<h1 style="color: #AAD42C">物品检索</h1>
		</div>
	</div>
	<hr style="height: 5px; border: none; border-top: 5px ridge green;" />
	<c:choose>
		<c:when test="${requestScope.sgoods.size()==0}">
			<div>
				<font style="font-size: large; color: red"></font>没有相关的物品的物品！
			</div>
		</c:when>
		<c:otherwise>
			<div id="container">
				<div class="row" style="border: medium; border-color: #2aabd2">
				   <c:forEach items="${requestScope.sgoods}" var="goods">
				   <a href="PDetails.jsp?goodsname=${goods.name}&discription=${goods.discription}&tel=${goods.user.tel}&paddress=${goods.pictureAddress}&PID=${goods.id}">
				    <div class="col-md-2 col-md-offset-1 thumbnail">
						<img src="${goods.pictureAddress}" > <span
							style="margin-left: 20px"><font style="color: red">${goods.name}</font></span><br>
						<span style="margin-left: 20px"><font style="color: red">${goods.discription}</font></span><br>
					</div>
				   </a>
				   </c:forEach>
					
				</div>
				<div class="row">
					<div class="col-md-offset-1">
						<nav>
						<ul class="pagination">
							<li><a href="#">&laquo;</a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>
						</nav>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

	<div class=panel-footer>
		<p style="text-align: center">版权所有</p>
	</div>
</body>
</html>