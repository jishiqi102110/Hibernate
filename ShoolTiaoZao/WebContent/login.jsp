<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%
String username=(String)request.getAttribute("username");
String passwd=(String)request.getAttribute("password");
String error=(String)request.getAttribute("error");
if(error!=null){
	if(error.equalsIgnoreCase("不能执行该操作！请先登录！")){		
	out.print("<script type='text/javascript'>alert('没有进行登录前不能执行该操作！');</script>");
	}
    out.print(error+"<br><br>"); 
}
if(username!=null&&passwd!=null){
    out.print("恭喜你注册成功！"+"<br><br>"); 
}
else{
	username="";
	passwd="";
}
%>
<script type="text/javascript">
function validatePassword(p){
	var password_span = document.getElementById("pdSpan");
	if(p.value==""){			
		password_span.innerHTML = "*密码不能为空！";
	}
	else{		
		password_span.innerHTML = "";
	}
}
function validateUsername(user) {
	var username_msg_span= document.getElementById("nameSpan");
	if(user.value== "") {
		username_msg_span.innerHTML = "用户名不能为空！";
		}
	else{		
		username_msg_span.innerHTML = "";		
	}
}
</script>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default" style="list-style: none">
   
    <div class="container-fluid">
        <li class="navbar-brand navbar-btn"><a href="index.jsp">首页</a></li>
        <li class="navbar-brand navbar-btn">2页</li>
        <li class="navbar-brand navbar-btn">3页</li>
        <li class="navbar-brand navbar-btn">4页</li>
    </div>
</nav>
<div class="container">
     <div class="header">
     </div>
     <div class="content row">
         <h1>用户登录</h1>
         <div class="col-md-4 col-lg-offset-2" style="width: 30%">

             <form class="form-horizontal" method="post" action="login.do">
                 <div class="form-group">
                     <label for="exampleInputEmail1">用户名</label>
                     <input  onblur="validateUsername(this);" name="un" type="text" class="form-control" id="exampleInputEmail1" placeholder="UserName" value=<%=username%>>
                     <span id="nameSpan"></span>
                 </div>
                 <div class="form-group">
                     <label for="InputPassword1">密码</label>
                     <input  onblur="validatePassword(this);" name="pd" type="password" class="form-control" id="p1" value=<%=passwd %> >
                     <span id="pdSpan"></span>
                 </div>
                 <div class="form-group">
                     <label for="verify">验证码</label>
                     <input type="text" class="form-control" id="verify" name="code" >
                 </div>
                 <div class="form-group">
                  <img style="cursor: pointer" onclick="this.src='validateCode.png?' + new Date().getTime()" src="validateCode.png" />
                 </div>
                 <div class="form-group">
                     <input type="submit" class="btn" value="登录">
                 </div>
             </form>

         </div>
         <div class="col-md-1 col-lg-offset-2" style="margin-left: 20px;margin-top: 4%"><img src="images/bianjie.jpg" ></div>
         <div class="col-md-3">
             <form class="form-horizontal">

                 <div class="form-group">
                 <h4>不是跳蚤市场用户？</h4>
                 <p>还等什么，快来加入吧，和同学分享商品的乐趣吧</p>
                 <a href="register.jsp">注册新用户</a>
                 </div>
             </form>

         </div>
     </div>
</div>
<div class="footer">
    <p style="text-align: center">版权所有</p>
</div>
<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/bootstrap.min.js"></script>
</body>
</html>