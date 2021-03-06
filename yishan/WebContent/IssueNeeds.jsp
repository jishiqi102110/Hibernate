<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IssueHeart</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/IssueHeart.css" rel="stylesheet" />
 <link href="css/index.css" rel="stylesheet">
<script src="js/jquery-2.2.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
<script type="text/javascript">
$(document).on("change",'select#type',function(){
	    $.post("type.do",{type:$(this).val()},function(data){
	    	 var dataObj=eval("("+data+")");  
	    	 var htmlnode='';
	    	 $("#miaoshu").empty();
	         for(var i=0;i<dataObj.length;i++){      
	               htmlnode+="<span class='input-group-addon' id='basic-addon1'>"+dataObj[i]+"</span><input type='text' class='form-control' aria-describedby='basic-addon1'>"
	         }  
	         $("#miaoshu").append(htmlnode);
	         
	    });
	 });
</script>    
</head>
<body>
    <c:choose>
		<c:when test="${param.error==1}">
			<div>
				<font style="color: red; font-size: large">必须上传图片</font>
			</div>
		</c:when>
		<c:when test="${param.error==2}">
			<div>
				<font style="color: red; font-size: large">上传的文件必须是图片</font>
			</div>
		</c:when>
		<c:when test="${param.error==3}">
			<div>
				<font style="color: red; font-size: large">上传文件出错</font>
			</div>
		</c:when>
		<c:when test="${param.error==4}">
			<div>
				<font style="color: red; font-size: large">服务器存储错误</font>
			</div>
		</c:when>
	</c:choose>

	<div class="header" id="header">
		<div class="navbar-default" id="header-top">
			<div id="top-left">
				<ul class="nav nav-pills" role="tablist">
					<li role="presentation" class="active"><a href="#">后台中心</a></li>
					<li role="presentation"><a href="exit.do">退出</a></li>
					<c:choose>
						<c:when test="${sessionScope.user!=null}">
							<li role="presentation"><a href="login.jsp">欢迎你   <font
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
					<li role="presentation"><a href="allSwap.do">所有物易物</a></li>
					<li role="presentation"><a href="getDeal.do">所有认领</a></li>
					<li role="presentation"><a href="favorites.jsp">收藏夹</a></li>
					<li role="presentation"><a href="IssueHeart.jsp">发布物品</a></li>
					<li role="presentation"><a href="IssueNeeds.jsp">需求发布</a></li>
					<li role="presentation"><a href="PersonalGoodsMagager.do">物品管理</a></li>
					<li role="presentation"><a href="ptrasaction.do">个人认领</a></li>
					<li role="presentation"><a href="pSwap.do">个人物易物</a></li>
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
	<div class="container">
		<div class="h1" align="center">
			<img src="images/logo.jpg">
		</div>
		<div class="row" style="height: auto">
			<div class="col-md-4 col-md-offset-4">
				<h1>发布需求</h1>
				<form class="form-horizontal" method="post" action="IssueNeed.do"
					style="margin-top: 20px" >
					<div class="form-group">
						<label for="type">类别</label> <select class="form-control"
							id="type" name="type">
							<option>服装</option>
							<option>家居</option>
							<option>交通</option>
							<option>书籍</option>
							<option>数码</option>
							<option>其他</option>
						</select>
					</div>
					<div class="form-group" >
						<label for="discription">描述</label>
						<textarea class="form-control" rows="3" id="discription"
							name="discription"></textarea>
					</div>	
					
					<div class="form-group" id="miaoshu" name="miaoshu">
				         
                        <span class="input-group-addon" id="basic-addon1">尺码</span>
                        <input type="text" class="form-control" placeholder="large、small、middle...." aria-describedby="basic-addon1" name="尺码">
                      
             
                        <span class="input-group-addon" id="basic-addon1">颜色</span>
                        <input type="text" class="form-control"  aria-describedby="basic-addon1">
                  
                 
                        <span class="input-group-addon" id="basic-addon1">面料</span>
                        <input type="text" class="form-control"  aria-describedby="basic-addon1">
                 
                 
                        <span class="input-group-addon" id="basic-addon1">适用对象</span>
                        <input type="text" class="form-control"  aria-describedby="basic-addon1">
                  
                    
                        <span class="input-group-addon" id="basic-addon1">品牌</span>
                        <input type="text" class="form-control"  aria-describedby="basic-addon1">
     
                    
					</div>	
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="上传">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class=panel-footer>
		<p style="text-align: center">版权所有</p>
	</div>
</body>
</html>