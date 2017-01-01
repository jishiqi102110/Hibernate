package Control;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.PersonalGoods;

public class addGoodsToShopCart extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	//得到传递过来的商品的基本信息
    	 String goodsName=req.getParameter("goodsName");//给购物车传递过去发布人姓名、商品价格、照片地址、商品名称
    	 String goodsPrice=req.getParameter("goodsPrice");
    	 String imageAddress=req.getParameter("imageAddress");
    	 String userID=req.getParameter("userID");
    	 String time=req.getParameter("time");
    	 PersonalGoods p1=new PersonalGoods();
    	 //设置goods属性
    	 p1.setGoodsPictureAddress(imageAddress);
    	 p1.setGoodsname(goodsName);
    	 p1.setGoodsprice(goodsPrice);
    	 p1.setUserID(userID);;
    	 p1.setTime(time);
    	//获取购物车map
    	 HttpSession session=req.getSession();
    	ConcurrentHashMap<PersonalGoods,String> item=(ConcurrentHashMap<PersonalGoods, String>)session.getAttribute("shopCartMap");
		if(item==null){
			 item=new ConcurrentHashMap<PersonalGoods,String>();
		}
		boolean flag=false;
		for (Map.Entry<PersonalGoods,String> entry : item.entrySet()) {
	    		PersonalGoods p=(PersonalGoods)entry.getKey();
	    		//如果存在该商品
	    		if(p.getGoodsname().equals(goodsName)){
	    			item.remove(p);
	    			p.setNumOfGoodsInshopCart(p.getNumOfGoodsInshopCart()+1);
	    			item.put(p, goodsName);
	    			flag=true;
	    		}
	    }
		 //如果不存在该商品
		 if(flag==false){
			 p1.setNumOfGoodsInshopCart(1);
			 item.put(p1,goodsName);
		 }
		 session.setAttribute("shopCartMap", item);
		 req.getRequestDispatcher("shopCart.jsp").forward(req,resp);
    }
}
