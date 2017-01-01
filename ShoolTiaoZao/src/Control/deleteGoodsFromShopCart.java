package Control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.PersonalGoods;


public class deleteGoodsFromShopCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsName=req.getParameter("goodsName");
    	HttpSession session=req.getSession();
    	//获取购物车map    	
        Map<PersonalGoods,String> item=(Map<PersonalGoods, String>)session.getAttribute("shopCartMap");
        for (Map.Entry<PersonalGoods,String> entry : item.entrySet()) {
    		PersonalGoods p=(PersonalGoods)entry.getKey();
    		if(p.getGoodsname().equals(goodsName)){
    			item.remove(p);
    			if(p.getNumOfGoodsInshopCart()>1){
    				p.setNumOfGoodsInshopCart(p.getNumOfGoodsInshopCart()-1);
    				item.put(p,goodsName);
    			}
    		}
    	}  
        session.setAttribute("shopCartMap",item);
        resp.sendRedirect("shopCart.jsp");
    }
}
