<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="yishan.Po.Goods"%>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Details</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/Details.css" rel="stylesheet">
     <link href="css/index.css" rel="stylesheet">
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
<div id="header-bottom" class="row">
    <div id="logo" class="col-md-4 col-md-offset-1" ></div>
    <div id="sousou" class="col-md-4 col-md-offset-2">
        <form class="form-horizontal" action="" method="post">
            <div class="input-group">
                <input type="text" name="cond" class="form-control" style="border:#F52B00 2px solid" placeholder="Search for...">
                <span class="input-group-btn">
        <input class="btn btn-default btn-warning"  type="submit" value="搜索">
      </span>
            </div>
        </form>
        <p class="navbar"><a href="">潮流T恤</a>|<a href="">时尚女鞋</a>|<a href="">短裤</a>|<a href="">半身裙</a>|<a href="">行车记录仪</a>|<a href="">新款</a>|<a href="">男鞋</a>|<a href="">耳机</a>|<a href="">时尚</a>|<a href="">女包</a>|<a href="">更多➤</a></p>
    </div>
</div>
<nav class=" navbar-default" style="list-style: none">
    <div class="container-fluid">
        <ul>
            <li class="dropdown navbar-brand" style="margin-left:8%" id="shouye"><a href="index.jsp"><i class="glyphicon glyphicon-home"></i>首页</a>
                <div>
                    <h4>服饰</h4>
                    <table border="0">
                        <tr><td>女装</td><td>男装</td><td>儿童装</td><td>更多</td></tr>
                    </table>
                    <h4>手机数码</h4>
                    <table border="0">
                        <tr><td>Iphone手机</td><td>国产手机</td><td>手机配件</td><td>更多</td></tr>
                    </table>
                    <h4>电脑配件</h4>
                    <table border="0" >
                        <tr><td>笔记本配件</td><td>台式机配给</td><td>更多</td></tr>
                    </table>
                </div>
            </li>
            <li class="navbar-brand ">最新发布
                <div >
                </div></li>
            <li class="navbar-brand ">最新需求
                <div >
                </div></li>
            <li class="navbar-brand ">物易物
                <div>
                </div></li>
            <li class="navbar-brand ">我要留言
                <div>
                </div></li>
        </ul>

    </div>
</nav>
<div class="container">
    <div class="row" style="margin-top: 20px;height: 400px">
        <div class="col-md-5 col-md-offset-2 thumbnail" style="height: 400px">
            <img src="${param.paddress}" style="height: 100%;width: 100%">
        </div>
        <div class="col-md-4">
            <div style="margin-top: 50px">
                <span style="font-size: medium;color: #2b669a">物品名称：${param.goodsname}</span><span></span><br><br>
                <span style="font-size: medium;color: #2b669a">物品描述：${param.discription}</span><span></span><br><br>
                <span style="font-size: medium;color: #2b669a">联系方式：${param.tel}</span><span></span><br><br>
               <c:choose>
                   <c:when test="${param.state=='normal'}">
                   <span style="font-size: medium;color: #2b669a">交易状态：无人领取</span><span></span><br><br>
                   </c:when>
                 <c:when test="${param.state=='undone'}">
                   <span style="font-size: medium;color: #2b669a">交易状态：有人领取</span><span></span><br><br>
                   </c:when>
                    <c:when test="${param.state=='done'}">
                   <span style="font-size: medium;color: #2b669a">交易状态：物品已无效</span><span></span><br><br>
                   </c:when>
                
                </c:choose>
                <div class="row">
                    <div class="col-md-offset-1 col-md-2" style="margin-top: 40px">
                        <div class="btn btn-lg btn-info"><a href="goodsDelet/${param.PID }.do">下架</a></div>
                    </div>
                    <div class="col-md-offset-6 col-md-3" style="margin-top: 40px">
                        <div class="btn btn-lg btn-info"><a href="goodsUpdate.do?PID=${param.PID }">修改</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function (){
        $("#shouye").hover(function(){
            $(this).addClass("liHover");
            $(this).find("div:hidden").show();
        },function (){
            $(this).find("div:visible").hide();
            $(this).removeClass("liHover");
        });
    });
</script>
</html>