<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IssueHeart</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/IssueHeart.css" rel="stylesheet"/>
    <script src="js/jquery-2.2.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<c:choose>
<c:when test="${param.error==1}">
    <div><font style="color: red; font-size: large">必须上传图片</font></div>
</c:when>
<c:when test="${param.error==2}">
    <div><font style="color: red; font-size: large">上传的文件必须是图片</font></div>
</c:when>
<c:when test="${param.error==3}">
    <div><font style="color: red; font-size: large">上传文件出错</font></div>
</c:when>
<c:when test="${param.error==4}">
    <div><font style="color: red; font-size: large">服务器存储错误</font></div>
</c:when>
</c:choose>

<div class="header" id="header">
    <div class="navbar-default" id="header-top">
        <div id="top-left">
            <ul class="nav nav-pills" role="tablist">
                <li role="presentation" class="active"><a href="#">后台中心</a></li>
                <li role="presentation"><a href="#">退出</a></li>
                <li role="presentation"><a href="#">登录</a></li>
            </ul>
        </div>
        <div id="top-right">
            <ul class="nav nav-pills" role="tablist">
                <li role="presentation"><a href="#">浏览记录</a></li>
                <li role="presentation"><a href="#">需求发布</a></li>
                <li role="presentation"><a href="#">物品管理</a></li>
                <li role="presentation"><a href="#">交易过程</a></li>
                <li role="presentation">
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                            联系我们
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">电话：*************</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">邮箱：*************</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">传真：*************</a></li>
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
    <div class="row" style="height:auto">
        <div class="col-md-4 col-md-offset-4">
            <h1>发布爱心物品</h1>
            <form class="form-horizontal" method="post" action="IssueGoods.do" style="margin-top: 20px" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="exampleInputEmail1">物品名称</label>
                    <input   name="name" type="text" class="form-control" id="exampleInputEmail1" placeholder="GoodsName" required="required" >
                </div>
                <div class="form-group">
                    <label for="type">类别</label>
                    <select class="form-control" id="type" name="type">
                        <option>服装</option>
                        <option>家居</option>
                        <option>交通工具</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="discription">描述</label>
                    <textarea class="form-control" rows="3" id="discription" name="discription"></textarea>
                </div>
                <div class="form-group">
                    <label for="exampleInputFile">上传图片</label>
                    <input type="file" id="exampleInputFile" name="file">
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="上传">
                </div>
            </form>
        </div>
    </div>
    <div></div>
</div>
<div class=panel-footer>
    <p style="text-align: center">版权所有</p>
</div>
</body>
</html>