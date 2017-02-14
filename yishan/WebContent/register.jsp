<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <style type="text/css">
        li{
            list-style: none;
        }
    </style>
    <script type="text/javascript" src="js/jquery-2.2.2.min.js"></script>
      <script type="text/javascript">
         $(function(){
        	 $("#name").blur(function(){
        		 $.post("exist.do",{'name':$("#name").val()},function(data){
        			 if(data=='false'){
       				      $("#span").text("抱歉用户已存在！");
       			     }
        			 if(data=='true'){
        				 $("#span").text("恭喜您该用户名可以注册")
        			 }
        		 });
        	 });
         });
    </script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  
</head>
<body>
<c:choose>
    <c:when test="${param.error==1}">
    <div><font style="font-size: large;color: red">该用户名已经存在</font></div>
    </c:when>
    <c:when test="${param.error==4}">
    <div><font style="font-size: large;color: red">验证码错误</font></div>
    </c:when>
</c:choose>

<nav class="navbar-default">
    <div class="container-fluid" style="list-style: none">
        <ul>
            <li class="navbar-brand navbar-btn" >首页</li>
            <li class="navbar-brand navbar-btn" >1页</li>
            <li class="navbar-brand navbar-btn" >2页</li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="h1" align="center"><img src="images/logo.jpg"></div>
    <div class="row">
        <div class="col-md-3 col-md-offset-2">
            <h1>用户注册</h1>
            <form class="form-horizontal" method="post" action="register.do">
                <div class="form-group">
                    <label for="exampleInputEmail1">用户名</label>
                    <input   name="name" type="text" class="form-control" id="name" placeholder="UserName" >
                    <span ><font color="color" id="span"></font></span>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">密码</label>
                    <input  name="password" type="password" class="form-control"  placeholder="password">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">邮箱</label>
                    <input  name="email" type="text" class="form-control" id="p" placeholder="email">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">电话</label>
                    <input  name="tel" type="text" class="form-control"  placeholder="tel">
                </div>
                <div class="form-group">
                    <label for="verify">验证码</label>
                    <input type="text" class="form-control" id="verify" name="code" >
                </div>
               <div class="form-group">
                        <img style="cursor: pointer" onclick="this.src='validateCode.png?' + new Date().getTime()" src="validateCode.png" />
                 </div>
                <div class="form-group">
                    <input type="submit" class="btn" value="注册">
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