package Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.dodeleteUser;

public class deleteUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
        String userID=req.getParameter("userID");
        System.out.println("userID"+userID);
        dodeleteUser delete=new  dodeleteUser();
         System.out.println("删除条数："+delete.deleteuser(userID));
         resp.sendRedirect("alluser.do");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
          this.doPost(req, resp);
    }
}
