package GoodsDisPlay;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;

import Bean.User;
import Dao.SearchGoodsOfUser;
import Dao.getAllUser;

public class searchAllUser extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
      getAllUser search=new getAllUser();
  	 ConcurrentHashMap<User,String> map= (ConcurrentHashMap)search.getuser();
  	 req.setAttribute("user",map);
	 req.getRequestDispatcher("userManager.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
}
