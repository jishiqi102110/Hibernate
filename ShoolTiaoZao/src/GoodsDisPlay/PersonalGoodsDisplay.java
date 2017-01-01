package GoodsDisPlay;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Current;

import Bean.PersonalGoods;
import Bean.User;
import Dao.ConnectionFactory;
import Dao.SearchGoodsOfUser;

public class PersonalGoodsDisplay extends HttpServlet{
	
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 //获取当前用户名称从而在数据库中调出它上传的商品 
    	 HttpSession session =req.getSession();
    	  User user=new User();
    	  user=(User)session.getAttribute("admin");
    	  //从数据库中查询该用户上传的商品
    	  String userID=user.getUserID();
    	  SearchGoodsOfUser search=new SearchGoodsOfUser();
    	  ConcurrentHashMap<PersonalGoods,String> map= (ConcurrentHashMap) search.getPersonGoodsMap(userID);
    	  //得到用户的商品列表map之后传到他的自己页面
    	  req.setAttribute("goods",map);
    	  req.getRequestDispatcher("goodsOnsale.jsp").forward(req, resp);
//    	  for (Map.Entry<PersonalGoods,String> entry : map.entrySet()) {
//	    		PersonalGoods personGoods=(PersonalGoods)entry.getKey();
//	    		//如果存在该商品
//	    		System.out.println(personGoods.getGoodsname());
//	    		System.out.println(personGoods.getGoodsPictureAddress());
//	    		System.out.println(personGoods.getTime());
//	    		System.out.println(personGoods.getGoodsprice());
//	    }
    }
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
}
