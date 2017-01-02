package Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.dodeleteUser;
import Manager.IUserManager;
import Manager.UserManagerImp;

public class deleteUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
        String userID=req.getParameter("userID");
        System.out.println("userID"+userID);
        IUserManager manager=new UserManagerImp();
        int flag= manager.delUser(userID);
        //dodeleteUser delete=new  dodeleteUser();
        System.out.println("删除条数："+flag);
         resp.sendRedirect("alluser.do");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
          this.doPost(req, resp);
    }
}
