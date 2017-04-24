<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/Transaction.css" rel="stylesheet">
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
              <c:choose>
						<c:when test="${sessionScope.user!=null}">
							<li role="presentation"><a href="login.jsp">欢迎你<font
									style="color: red">${sessionScope.user.name}</font></a></li>
						</c:when>
						<c:otherwise>
							<li role="presentation"><a href="login.jsp">登录</a></li>
							<li role="presentation"><a href="register.jsp">注册</a></li>
						</c:otherwise>
					</c:choose>
            </ul>
        </div>
        <div id="top-right">
            <ul class="nav nav-pills" role="tablist">
              <li role="presentation"><a href="IssueHeart.jsp">发布物品</a></li>
                  <li role="presentation"><a href="#">需求发布</a></li>
                  <li role="presentation"><a href="PersonalGoodsMagager.do">物品管理</a></li>
                  <li role="presentation"><a href="#">个人交易</a></li>
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
<div style="clear: both">
    <hr style="height:5px;border:none;border-top:5px ridge green;"/>
</div>
<div class="row">
     <div class="col-md-offset-1 col-md-2" style="margin-left:110px">
         <img src="images/logo.jpg">
     </div>
    <div class="col-md-3 col-md-offset-5" style="margin-top: 10px">
        <h1 style="color: #AAD42C">个人物易物状态</h1>
    </div>
</div>
<hr style="height:5px;border:none;border-top:5px ridge green;"/>
<div id="container">
    <div class="row" id="undone">
        <h2 style="margin-left: 50px">我的物易物请求</h2>
          <c:if test="${requestScope.get!=null && fn:length(requestScope.get) != 0}">
           <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">交换用物品</font></div>
           <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">物主物品</font></div>
        <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">物主</font></div>
      
          </c:if>
       
        <br> 
         <c:forEach items="${requestScope.get}" var="u">
        <div style="height: 220px;margin-top: 20px;border:#E7E7E7 1px solid;">
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.pic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.getgname }</span>
        </div>
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.dpic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.disgname }</span>
        </div>
        <div class="col-md-2 col-md-offset-1" style="height: 200px;margin-left: 40px"><div align="center" style="margin-top: 70px"> <font style="font-size: x-large;">${u.disname }</font></div></div>
        <div class="col-md-2 col-md-offset-1" style="height: 200px;margin-left: 50px"><div align="center" style="margin-top: 70px"> <font style="font-size: x-large"><a href="deletSwap.do?swapID=${u.swapID}">撤销</a></font></div></div>         
        </div> 
         </c:forEach>          
        <br>
        <div class="col-md-offset-1" style="margin-top: 50px">
            <nav>
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </nav>
        </div>
      </div>
      <div class="row" id="undone">
        <h2 style="margin-left: 50px">别人的物易物请求</h2>
          <c:if test="${requestScope.dis!=null && fn:length(requestScope.dis)!= 0}">
            <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">交换用物品</font></div>
           <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">我的物品</font></div>
        <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">物品索求者</font></div>
           
          </c:if>
       
        <br> 
         <c:forEach items="${requestScope.dis}" var="u">
        <div style="height: 220px;margin-top: 20px;border:#E7E7E7 1px solid;">
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.pic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.getgname }</span>
        </div>
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.dpic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.disgname }</span>
        </div>
        <div class="col-md-1 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <font style="font-size: x-large">${u.getname }</font></div></div>       
        <div class="col-md-1 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <font style="font-size: x-large"><a href="agreeSwap.do?swapID=${u.swapID}">同意</a></font></div></div>
        <div class="col-md-1 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <font style="font-size: x-large"><a href="disagreeSwap.do?swapID=${u.swapID}">不同意</a></font></div></div>
        </div> 
         </c:forEach>          
        <br>
        <div class="col-md-offset-1" style="margin-top: 50px">
            <nav>
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </nav>
        </div>
      </div>
       <div class="row" id="undone">
        <h2 style="margin-left: 50px">物主接受的物易物</h2>
          <c:if test="${requestScope.done!=null && fn:length(requestScope.done)!= 0}">
           <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">物品</font></div>
        <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">交换用物品</font></div>
        <div class="col-md-1 col-md-offset-1"><font style="font-size: large;color: red">物主名称</font></div>               
        <div class="col-md-1 col-md-offset-1"><font style="font-size: large;color: red">评价</font></div>               
          </c:if>
       
        <br> 
         <c:forEach items="${requestScope.done}" var="u">
        <div style="height: 220px;margin-top: 20px;border:#E7E7E7 1px solid;">
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.dpic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.disgname }</span>
        </div>
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.pic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.getgname }</span>
        </div>        
        <div class="col-md-1 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <font style="font-size: x-large ;color:red">点赞</font></div></div> 
        <c:choose>
          <c:when test="${u.evaluate==null}">
        <div class="col-md-1 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <form action="evaluateSwap.do"><input type="text" style="width: 300px;height: 100px;" name="evaluate" required="required"/><input type="hidden" value="${u.swapID}" name="swapID" /><input type="submit" value="提交" style="margin-top: 10px"/></form></div></div>                                
          </c:when>
          <c:otherwise>
                <div class="col-md-2 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <font style="font-size:1">评价:${u.evaluate }</font></div></div>            
          </c:otherwise>
        </c:choose>                  
        </div> 
         </c:forEach>          
        <br>
            <nav>
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </nav>
        </div>
      </div>
       <div class="row" id="undone">
        <h2 style="margin-left: 50px">物主不接受的物易物</h2>
          <c:if test="${requestScope.disagree!=null && fn:length(requestScope.disagree)!= 0}">
           <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">物品</font></div>
        <div class="col-md-2 col-md-offset-1"><font style="font-size: large;color: red">交换用物品</font></div>
        <div class="col-md-1 col-md-offset-1"><font style="font-size: large;color: red">物主名称</font></div>               
        <div class="col-md-1 col-md-offset-1"><font style="font-size: large;color: red">评价</font></div>               
          </c:if>
       
        <br> 
         <c:forEach items="${requestScope.disagree}" var="u">
        <div style="height: 220px;margin-top: 20px;border:#E7E7E7 1px solid;">
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.dpic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.disgname }</span>
        </div>
         <div class="col-md-2 col-md-offset-1"  style="height: 200px" >
            <img src="${u.pic }" class="thumbnail" alt="fdef" style="height: 160px;width: 160px">
             <span style="margin-bottom: 5px;">物品名称：${u.getgname }</span>
        </div>        
        <div class="col-md-1 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <font style="font-size: x-large ;color:red">点赞</font></div></div> 
         <c:choose>
          <c:when test="${u.evaluate==null}">
        <div class="col-md-1 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <form action="evaluateSwap.do"><input type="text" style="width: 300px;height: 100px;" name="evaluate" required="required"/><input type="hidden" value="${u.swapID}" name="swapID" /><input type="submit" value="提交" style="margin-top: 10px"/></form></div></div>                                
          </c:when>
          <c:otherwise>
                <div class="col-md-2 col-md-offset-1" style="height: 200px"><div align="center" style="margin-top: 70px"> <font style="font-size:1">评价:${u.evaluate }</font></div></div>            
          </c:otherwise>
        </c:choose>                 
        </div> 
         </c:forEach>          
        <br>
            <nav>
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </nav>
        </div>
      </div>
</div>
<div class=panel-footer>
    <p style="text-align: center">版权所有</p>
</div>
</body>
</html>