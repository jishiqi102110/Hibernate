package Control;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.spi.activation.Server;

import Dao.addUser;
import Dao.judgeUserExists;
public class doRegister extends HttpServlet{
      @Override     
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
		//对比验证码
		HttpSession session=req.getSession();
		String code=(String)session.getAttribute("validateCode");
		
		String code2=req.getParameter("code");
		if(code.toLowerCase().equals(code2.toLowerCase())){
			//获取参数
			String username=req.getParameter("username");//获取用户名
			String password1=req.getParameter("password1");
			String password2=req.getParameter("password2");
			String tel=req.getParameter("tel");
			String qq=req.getParameter("qq");
			String email=req.getParameter("email");
			if(password1.equals(password2)){	
				addUser addu=new addUser();
				addu.adduser(username, password1, tel,email, qq);
				req.setAttribute("username",username);
				req.setAttribute("password",password1);
				req.getRequestDispatcher("login.jsp").forward(req, resp);//传递参数跳转到登录页面
			}
			else{
				req.setAttribute("error","密码输入不一致！");
				req.getRequestDispatcher("register.jsp").forward(req, resp);
			}
			//添加新用户
		}
		else{
			req.setAttribute("error","验证码输入不一致！");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		
    }
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
}
