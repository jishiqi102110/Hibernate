package Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.User;
import Dao.doDeletePersonGoods;

public class offTheShelf extends HttpServlet {
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	 HttpSession session=req.getSession();
    	 User user=(User) session.getAttribute("admin");
    	 String userID=user.getUserID();
    	 String goodID=req.getParameter("goodID");
    	 String goodsAddress=req.getParameter("goodsAddress");
    	 doDeletePersonGoods delete= new doDeletePersonGoods();
    	 delete.deletePersonGoods(goodID, userID);
    	 //删除项目下的为商品图片
    	 deleteFile deletefile=new deleteFile();
    	 System.out.println(deletefile.DeleteFolder(goodsAddress));
    	 resp.sendRedirect("personalGoodsDisplay.do");
    }
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
         this.doPost(req, resp);
     }
}
