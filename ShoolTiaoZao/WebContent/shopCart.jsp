<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="Bean.PersonalGoods"%>
 <%@ page import="java.util.Map" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>GoodsCellection</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="header">
<div  class="navbar top" style="background: #F5F5F5;">
  <div class="btn " style="margin-left: 8%">用户中心</div>
    <div class="btn  " style="font-size: 15px;"><a href="logout.do">退出</a></div>
    <div class="btn " style="margin-left: 40%">收藏夹</div>
    <div class="btn "><a href="shopCart.jsp">购物车</a></div>
    <div class="btn "><a href="goodsIssue.jsp">我要出售</a></div>
    <div class="btn "><a href="index.jsp">我要求购</a></div>
    <div class="btn ">联系客服</div>
    <div class="btn ">网站导航<span class=""></span></div>
</div>
    <div style="margin-left: 47%;width: 40%;margin-top:40px;">
        <form class="form-horizontal" action="mohuSearch.do" method="post">
            <div class="input-group">
            <input type="text" name="cond" class="form-control" style="border: #F52B00 2px solid" placeholder="Search for...">
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
    <div class="row" style="background: #F8F8F8;height: 30px;font-size: 20px;margin-top: 15px;margin-bottom: 10px">
        <div class="col-md-3"></div>
        <div class="col-md-2">商品名称</div>
        <div class="col-md-3">商品价格</div>
        <div class="col-md-2">添加时间</div>
        <div class="col-md-2">商品数量</div>
    </div>
    <%
//得到存在session中的shopCart
    	ConcurrentHashMap< PersonalGoods, String>  shopCartMap=(ConcurrentHashMap<PersonalGoods, String>) session.getAttribute("shopCartMap");
        double total=0;
        if(shopCartMap!=null&&shopCartMap.size()!=0){ 
        	for (Map.Entry<PersonalGoods,String> entry : shopCartMap.entrySet()) {
            	PersonalGoods personGoods=(PersonalGoods)entry.getKey();	//如果存在该商品     
            	String deleteUrl="deleteGoodsFromShopCart.do?&&goodsName="+personGoods.getGoodsname();
            	total=Double.parseDouble(personGoods.getGoodsprice())*personGoods.getNumOfGoodsInshopCart()+total;
   %>
    <div class="row" style="background: #F8F8F8;height: 200px;font-size: 20px;margin-top: 20px;margin-bottom: 10px">
        <div class="col-md-3"><img src=<%=personGoods.getGoodsPictureAddress()%> style="height: 190px" class="img-thumbnail"></div>
        <div class="col-md-2"><div style="margin-top: 80px"><%=personGoods.getGoodsname()%></div></div>
        <div class="col-md-3"><div style="margin-top: 80px"><%=personGoods.getGoodsprice() %></div></div>
        <div class="col-md-2"><div style="margin-top: 80px"><%=personGoods.getTime() %></div></div>
        <div class="col-md-2"><div style="margin-top: 80px"><%=personGoods.getNumOfGoodsInshopCart()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=<%=deleteUrl%>>删除</a>&nbsp;&nbsp;&nbsp;<a>购买</a> </div></div>
    </div>
    <%
        	}
        	%>
             <br>
        	 <p align="center"> <font size="5">总计：<%=total %></font><p>        	
        	<% 
        }
        else{
        	%>
           <P>您还没有添加任何商品到购物，<a href="index.jsp">快去购物吧!</a></P>
        	<% 
        }
    %>
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