<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="Bean.PersonalGoods"%>
 <%@ page import="java.util.Map" %>  
 <%@ page import="Dao.searchGoodsForIndex" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="Bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Index</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="js/myfocus-2.0.1.min.js"></script><!--引入myFocus库-->

    <script type="text/javascript">
        myFocus.set({
            id:'myFocus',//ID
            pattern:'mF_fancy', //风格
            time:3
        });
    </script>
</head>
<body>
<div class="header">
<div  class="navbar top" style="background: #F5F5F5;">
  <div class="btn " style="margin-left: 8%"><a href="managerLogin.jsp">后台中心<a/></div>
    <div class="btn  " style="font-size: 15px;"><a href="logout.do">退出 </a></div>
    <%
    User user=(User)session.getAttribute("admin");
    if(user!=null){
    	String username=user.getUsername();
    	String message="欢迎您："+username;
	%>
	 <div class="btn  " style="font-size: 15px;"><%=message%></div>
	<% 
    }
    else{
    	%>
    	 <div class="btn  " style="font-size: 15px;"><a href="login.jsp">登录 </a></div>
    	<% 
    }
    %>
    <div class="btn " style="margin-left: 40%">收藏夹</div>
    <div class="btn "><a href="shopCart.jsp">购物车</a></div>
    <div class="btn "><a href="goodsIssue.jsp">我要出售</a></div>
    <div class="btn "><a href="personalGoodsDisplay.do">我的商品</a></div>
    <div class="btn ">联系客服</div>
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
         <div class="row">
             <div  class="col-md-8 slideImage " >
                 <div id="myFocus"><!--焦点图盒子-->
                     <div class="loading"><img src="images/slide.jpg" alt="请稍候..." /></div><!--载入画面(可删除)-->
                     <div class="pic"><!--图片列表-->
                         <ul>
                             <li><a href="#1"><img src="images/slide.jpg" thumb="" style="height:392px;width: 720px;" alt="图片1来源于网络，版权属于作者" text="图片1更详细的描述文字" /></a></li>
                             <li><a href="#2"><img src="images/slide2.jpg" thumb="" style="height:392px;width: 720px" alt="版权属于作者，图片2来源于网络" text="图片2更详细的描述文字" /></a></li>
                             <li><a href="#3"><img src="images/slide3.jpg" thumb="" style="height:392px;width: 720px "alt="图片3来源于网络，版权属于作者" text="图片3更详细的描述文字" /></a></li>
                             <li><a href="#4"><img src="images/slide.jpg" thumb="" style="height:392px;width: 720px" alt="版权属于作者，图片4来源于网络" text="图片4更详细的描述文字" /></a></li>
                             <li><a href="#5"><img src="images/slide1.jpg" thumb=""style="height:392px;width: 720px" alt="图片5来源于网络，版权属于作者" text="图片5更详细的描述文字" /></a></li>
                         </ul>
                     </div>
                 </div>
             </div>
             <div class="col-md-4 slide_right">
                 <div class="container">
                     <div  class="btn" style="width: 110%;background: #F07D13; height: 80px"><span style="font-size: 24px" class=" btn-lg">发布闲置</span></div>
                     <div class="row">
                        <div class="col-md-5 col-lg-offset-1 btn" style="background: #F07D13; height: 60px;margin-top: 30px"><span style="font-size: 24px" class=" btn-lg">开店</span></div>
                         <div class="col-md-4 col-lg-offset-2 btn"style="background: #F07D13; height: 60px;margin-top: 30px"><span style="font-size: 24px" class=" btn-lg">求购</span></div>
                     </div>
                     <div style="width: 110%;background: #F07D13; height: 195px;clear: both;margin-top: 40px;">
                        <img src="images/computer.jpg" style="width: 100%;height:100%">
                     </div>
                 </div>
             </div>
         </div>
    <h2 style="color: #AAD42C">热门商品<small>HOT COMMODITEMS</small></h2>
        <%
        //在数据库中获得商品列表
        searchGoodsForIndex index=new searchGoodsForIndex();
		ConcurrentHashMap<PersonalGoods, String>  map=index.getindexGoodsMap();
        int goodsNum=map.size();//商品总数
        int row=goodsNum/4+1;
     PersonalGoods[] goodsList=new PersonalGoods[goodsNum];
     if(map!=null){
    	    int i=0;
    	    for (Map.Entry<PersonalGoods,String> entry : map.entrySet()) {
    	    	PersonalGoods personGoods=(PersonalGoods)entry.getKey();	//如果存在该商品
                goodsList[i]=personGoods;
    	    	i++;
    	    }
     }
     %>
     <div class="goodsList ">
     <%
        int jishuqi=0;
        for(int i=0;i<row;i++){               	
     %>
         <div class="row ">
         <%
         for(int j=0;j<4;j++){
        	 if(jishuqi==goodsNum){ break ;}
        	 jishuqi++;
        	 String HyperLink=null;
        	 String goodsName=goodsList[jishuqi-1].getGoodsname();
        	 String goodsPrice=goodsList[jishuqi-1].getGoodsprice();
        	 String imageAddress=goodsList[jishuqi-1].getGoodsPictureAddress();
        	 String time=goodsList[jishuqi-1].getTime();
             String userID=goodsList[jishuqi-1].getUserID();
             HyperLink="goodsDetails.jsp?imageAddress="+imageAddress+"&&userID="+userID+"&&goodsPrice="+goodsPrice+"&&goodsName="+goodsName+"&&time="+time;
         %>
             <div class="col-md-3" >
                <a href=<%=HyperLink%>><img src=<%=imageAddress%> class="img-thumbnail"></a> 
             <div style="height: 60px; border: 0">
                 <span class="btn ">日系小清新</span>
                <span class=" btn">真的是皮啊</span>
                 <span class=" btn">穿对鞋子再出门</span>
             </div>
             </div>
          <%
        }
   %>              
        </div>
   <%
        }
   %> 
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