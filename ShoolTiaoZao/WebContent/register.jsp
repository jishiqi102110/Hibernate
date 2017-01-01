<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-CN">
<%
String error=(String)request.getAttribute("error");
if(error!=null){
    out.print(error+"<br><br>"); 
}
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>register</title>
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
         <h1>用户注册</h1>
         <div class="col-md-4 col-lg-offset-2" style="width: 30%">

             <form class="form-horizontal" action="register.do" method="post">

                 <div class="form-group">
                     <label for="exampleInputEmail1">用户名</label>
                     <input type="text" class="form-control" id="Username" placeholder="UserName" name="username" onblur="javascript:validateUsername(this.value);">
                     <span id="username_msg"></span>
                 </div>
                 <div class="form-group">
                     <label for="InputPassword1">密码</label>
                     <input type="password" class="form-control" id="p1"  name="password1">
                     <span id="passError"></span>
                 </div>
                 <div class="form-group">
                     <label for="InputPassword2">确认密码</label>
                     <input type="password" class="form-control" id="p2" name="password2" onblur="validatePassword(this,p1);">
                 </div>
                 <div class="form-group">
                     <label for="InputPassword3">电话号码</label>
                     <input type="text" class="form-control" id="telnum" name="tel">
                 </div>
             
                 <div class="form-group">
                     <label for="InputPassword4">QQ号码</label>
                     <input type="text" class="form-control" id="qqnum" name="qq">
                 </div>
                 <div class="form-group">
                     <label for="InputPassword5">email</label>
                     <input type="text" class="form-control" id="Email" name="email">
                 </div>
                 
                 <div class="form-group">
                     <label for="verify">验证码</label>
                     <input type="text" class="form-control" id="verify" name="code" >
                 </div>
                 <div class="form-group">
                 <img style="cursor: pointer" onclick="this.src='validateCode.png?' + new Date().getTime()" src="validateCode.png" />
                 </div>
                 <div class="form-group">
                     <input type="submit" class="btn" value="提交">
                     <input type="reset" class="btn" value="重置">
                 </div>
             </form>

         </div>
         <div class="col-md-1 col-lg-offset-2" style="margin-left: 20px;margin-top: 4%"></div>

     </div>

</div>
<div class="footer">
    <p style="text-align: center">版权所有</p>
</div>
<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/2.0.4/bootstrap.min.js"></script>
<script type="text/javascript">
	var xmlHttpRequest;
	function validatePassword(p1,p2){
		var py= document.getElementById("p2");
		var password_span = document.getElementById("passError");
		if(p1.value!=p2.value){
			password_span.innerHTML = "*两次密码输入不相同！";
		}
		else{
			password_span.innerHTML = "密码输入正确，请继续填写";			
		}
		if(p1.value==""||p2.value==""){			
			password_span.innerHTML = "*密码不能为空！";
			
		}
	}
	va
	function validateUsername(username) {
		if(username == "") {
			var username_msg_span = document.getElementById("username_msg");
			username_msg_span.innerHTML = "用户名不能为空！";
			return;
			}
		xmlHttpRequest = initXMLHttpRequest();
		//alert(xmlHttpRequest);
		if(xmlHttpRequest) {			
			/*
			var url = "Validate.do?username=" + username;
			xmlHttpRequest.open("GET", url, true);//true:异步提交请求，false：同步提交请求
			//注册回调函数
			xmlHttpRequest.onreadystatechange = callback;
			xmlHttpRequest.send(null);//发送这个请求，参数：实体内容
			*/
			xmlHttpRequest.open("POST", "confirmUsername.do", true);
			xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
			username= encodeURI(encodeURI(username));
			var data = "username=" +username;
			xmlHttpRequest.onreadystatechange = callback;
			xmlHttpRequest.send(data);
		}
	}
	
	function callback() {
		//readyState
		//0:初始化, 1:正在加载, 2:已加载,  3:交互中, 4:完成
		if(xmlHttpRequest.readyState == 4) { //4:服务器响应消息已经回来
			if(xmlHttpRequest.status == 200) {//200响应状态码
				var msg = xmlHttpRequest.responseText;
				//alert(msg);
				showMsg(msg);
				
			}
		}
	}
		
	function showMsg(msg) {
		var username_msg_span = document.getElementById("username_msg");
		if(msg =="notexsits") {
			username_msg_span.innerHTML = "恭喜您，该用户名可以注册！";
		} else {
			username_msg_span.innerHTML = "对不起，该用户名已经被注册！";
		}
	}
	function initXMLHttpRequest() {
		var xhr = null;
		if(window.XMLHttpRequest) {
			//FireFox, NetScape,Chrome,Safari等
			xhr = new XMLHttpRequest();
		} else if(window.ActiveXObject) { //IE
			try {
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xhr;
	}
</script>
</body>
</html>