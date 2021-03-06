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
import Manager.IUserManager;
import Manager.UserManagerImp;

public class searchAllUser extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
     // getAllUser search=new getAllUser();
    	IUserManager manager=new UserManagerImp();
  	 ConcurrentHashMap<User,String> map= manager.getAlluser();
  	 req.setAttribute("user",map);
	 req.getRequestDispatcher("userManager.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
}
