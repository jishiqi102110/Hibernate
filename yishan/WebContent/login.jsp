<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
</head>
<body>
<c:choose>
      <c:when test="${param.loginmsg==1}">
      <%
      out.print("<script type='text/javascript'>alert('不能进行此操作，清先登录！');</script>");
      %>
      </c:when>
       <c:when test="${param.msg==1}">
      <%
      out.print("<script type='text/javascript'>alert('恭喜你注册成功');</script>");
      %>
      </c:when>
      <c:when test="${4==param.error}">
      <div style=""><font style="font-size:large;color: red">验证码错误!</font></div>
      </c:when>
       <c:when test="${2==param.error}">
      <div style=""><font style="font-size:large;color: red">密码错误!</font></div>
      </c:when>
       <c:when test="${0==param.error}">
      <div style=""><font style="font-size:large;color: red">用户不存在!</font></div>
      </c:when>
</c:choose>

<nav class="navbar-default">
 
    <div class="container-fluid" style="list-style: none">
        <ul>
            <a href="index.jsp"><li class="navbar-brand navbar-btn" style="color: blue" >首页</li></a>
            <li class="navbar-brand navbar-btn" >1页</li>
            <li class="navbar-brand navbar-btn" >2页</li>
        </ul>
    </div>
    </nav>
    <div class="container">
        <div class="h1" align="center">
            <img src="images/logo.jpg">
        </div>
        <div class="row" style="height:auto">
            <div class="col-md-3 col-md-offset-2">
               <h1>用户登录</h1>
                <form class="form-horizontal" method="post" action="login.do">
                    <div class="form-group">
                        <label for="exampleInputEmail1">用户名</label>
                        <input name="name" type="text" class="form-control" id="exampleInputEmail1" placeholder="UserName" required="required"  >
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">密码</label>
                        <input  name="password" type="password" class="form-control" id="p1" placeholder="password" required="required">
                    </div>
                    <div class="form-group">
                        <label for="verify">验证码</label>
                        <input type="text" class="form-control" id="verify" name="code"  required="required">
                    </div>
                    <div class="form-group">
                        <img style="cursor: pointer" onclick="this.src='validateCode.png?' + new Date().getTime()" src="validateCode.png" />
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn" value="登录">
                    </div>
                </form>
            </div>
            <div class="col-md-1" style="margin-top: 100px;margin-left: 30px">
                <img src="images/bianjie.jpg">
            </div>
            <div class="col-md-4">
                <form class="form-horizontal">
                <div class="form-group" align="center" style="margin-top: 100px">
                    <h4>不是易善网用户？</h4>
                    <p>还等什么，快来加入吧，和别人分享你的慈善物品吧</p>
                    <a href="register.jsp">注册新用户</a>
                </div>
            </form></div>
        </div>
        <div></div>
    </div>
<div class=panel-footer>
    <p style="text-align: center">版权所有</p>
</div>
</body>

</html>