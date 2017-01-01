<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>GoodsIssue</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<%
String error=(String)request.getAttribute("error");
if(error!=null){
    out.print(error+"<br><br>"); 
}
%>
<body>
<div class="header">
<div  class="navbar top" style="background: #F5F5F5;">
  <div class="btn " style="margin-left: 8%"><a href="index.jsp">首页</a></div>  
    <div class="btn  " style="font-size: 15px;"><a href="logout.do">退出</a></div>
    <div class="btn " style="margin-left: 40%">收藏夹</div>
    <div class="btn "><a href="shopCart.jsp">购物车</a></div>
    <div class="btn "><a href="goodsIssue.jsp">我要出售</a></div>
    <div class="btn "><a href="personalGoodsDisplay.do">我的商品</a></div>
    <div class="btn "><a href="index.jsp">我要求购</a></div>
    <div class="btn ">联系客服</div>
    <div class="btn ">网站导航<span class=""></span></div>
</div>
    <div style="margin-left: 47%;width: 40%;margin-top:40px;">
        <form class="form-horizontal" >
            <div class="input-group">
            <input type="text" class="form-control" style="border: #F52B00 2px solid" placeholder="Search for...">
             <span class="input-group-btn">
        <input class="btn btn-default btn-warning"  type="submit" value="搜索">
      </span>
            </div>
        </form>
       <p class="navbar"><a href="">潮流T恤</a>|<a href="">时尚女鞋</a>|<a href="">短裤</a>|<a href="">半身裙</a>|<a href="">行车记录仪</a>|<a href="">新款</a>|<a href="">男鞋</a>|<a href="">耳机</a>|<a href="">时尚</a>|<a href="">女包</a>|<a href="">更多➤</a></p>

    </div>
</div>
<nav class="navbar navbar-default" style="list-style: none">
    <div class="container-fluid">
        <li class="navbar-brand dropDown1 " style="margin-left: 8%"><a href="index.jsp">首页</a>
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
        <li class="navbar-brand ">最新求购
            <div >
            </div></li>
        <li class="navbar-brand ">以旧换新
            <div>
            </div></li>
        <li class="navbar-brand ">我要留言
            <div>
            </div></li>
    </div>
</nav>
<div class="container">
         <div class="row">
           <div class="col-md-6 col-lg-offset-2">
               <form class="form-horizontal" action="goodsissue.do" method="post" enctype="multipart/form-data">
                   <div class="form-group">
                       <label for="inputEmail3" class="col-sm-4 control-label">商品名称：</label>
                       <div class="col-sm-8">
                           <input type="text" class="form-control" id="inputgoodsnane" name="goodsName" >
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="inputEmail4" class="col-sm-4 control-label">数量：</label>
                       <div class="col-sm-8">
                           <input type="text" class="form-control" id="inputgoodsNum" name="goodsNUm" >
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="inputEmail5" class="col-sm-4 control-label">价格：</label>
                       <div class="col-sm-8">
                           <input type="text" class="form-control" id="inputgoodsPrice" name="goodsPrice" >
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="inputEmail8" class="col-sm-4 control-label">分类：</label>
                       <div class="col-sm-8">
                           <select type="text" class="form-control" id="inputEmail8" name="goodsClass">
                               <option>服饰</option>
                               <option>电子数码</option>
                           </select>
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="inputEmai20" class="col-sm-4 control-label">上传图片：</label>
                       <div class="col-sm-8">
                           <input name="goodsPicture" type="file" class="form-control" id="inputGoodsPicture" style="border: 0 ;height: 50px;" >
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="inputEmai20" class="col-sm-4 control-label"></label>
                       <div class="col-sm-8">
                           <input type="submit" class="form-control btn btn-success" id="inputEmai21" value="立即发布">
                       </div>
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
<script>
    $(document).ready(function (){
        $("li").hover(function(){
            $(this).addClass("liHover");
            $(this).find("div:hidden").show();
        },function (){
            $(this).find("div:visible").hide();
            $(this).removeClass("liHover");
        });
    });
</script>
</body>
</html>