<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Admin</title>

    <!-- Bootstrap core CSS -->
    <link href="/ShoolTiaoZao/css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="/ShoolTiaoZao/css/signin.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar" style="background:#317EE6 ">
    <h1 style="text-align: center">跳蚤市场管理系统</h1>
</nav>
<div class="container">
     <div class="row">
         <div class="col-md-2" style="background: blanchedalmond">
          <h3 class="btn btn-block btn-success btn-lg "><a href=""><a href="ManagerGoods.do">商品管理</a></a></h3>
          <h3 class="btn btn-block btn-success btn-lg "><a href=""><a href="index.jsp">退出管理</a></a></h3>
          <h3 class="btn btn-block btn-success btn-lg"><a href="alluser.do">用户管理</a></h3>
         </div>
         <div class="col-md-9">
              <div class="row"  style="background: #F8F8F8;height: 40px;font-size: 20px;margin-top: 15px;margin-bottom: 10px">
                  <div class="col-md-3">商品ID</div>
                  <div class="col-md-3">商品名称</div>
                  <div class="col-md-3">价格&nbsp;&nbsp;&nbsp;发布人编号</div>
                  <div class="col-md-3">操作</div>
              </div>
           <c:choose>
           <c:when test="${empty goods}">
           当前没有发布的商品！
           </c:when>
           <c:otherwise>
             <c:forEach var="u" items="${goods}">
              <div class="row"  style="background: #F8F8F8;height: 30px;font-size: 20px;margin-top: 15px;margin-bottom: 10px">
                 <div class="col-md-3" >${u.value}</div>
                 <c:set var="v" value="${u.value}" scope="request"></c:set>
                 <c:set var="m" value="${u.key.userID}" scope="request"></c:set>                 
                 <% String url="ManagerDeleteGoods.do?goodsID="+request.getAttribute("v")+"&userID="+request.getAttribute("m"); %>
                 <div class="col-md-3" >${u.key.goodsname }</div>
                 <div class="col-md-3" >${u.key.goodsprice}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${u.key.userID}</div>
                 <div class="col-md-3" ><a href=<%=url%> class="btn btn-warning">删除</a></div>
             </div>
            </c:forEach>
           </c:otherwise>
           </c:choose>
         </div>
     </div>
</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
