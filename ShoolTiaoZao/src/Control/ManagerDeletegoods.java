package Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.doDeletePersonGoods;

public class ManagerDeletegoods extends HttpServlet{
       @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
         String goodsID=req.getParameter("goodsID");
         String userID=req.getParameter("userID");         
         //System.out.println(userID);
         //System.out.println(goodsID);
         doDeletePersonGoods delete= new doDeletePersonGoods();
    	 delete.deletePersonGoods(goodsID, userID);
    	 resp.sendRedirect("ManagerGoods.do");
       }
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
      this.doPost(req, resp);
   }
}
