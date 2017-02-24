<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Details</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/Details.css" rel="stylesheet">
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
            <li class="dropdown navbar-brand" style="margin-left:8%" id="shouye"><a href=""><i class="glyphicon glyphicon-home"></i>首页</a>
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
        <div class="col-md-5 col-md-offset-2" style="height: 400px">
            <img src="images/zhongxin.jpg" class="thumbnail">
        </div>
        <div class="col-md-2">
            <div style="margin-top: 50px">
                <span style="font-size: medium;color: #2b669a">物品名称：</span><span></span><br><br>
                <span style="font-size: medium;color: #2b669a">物品描述：</span><span></span><br><br>
                <span style="font-size: medium;color: #2b669a">联系方式：</span><span></span><br><br>
                <span style="font-size: medium;color: #2b669a">交易状态：</span><span></span><br><br>
                <div class="row">
                    <div class="col-md-offset-1 col-md-2" style="margin-top: 40px">
                        <div class="btn btn-lg btn-info">下架</div>
                    </div>
                    <div class="col-md-offset-6 col-md-3" style="margin-top: 40px">
                        <div class="btn btn-lg btn-info">修改</div>
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