package Control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.User;
import Dao.JudgeManagerExist;
import Dao.judgeUserExists;

public class ManagerLogin extends HttpServlet{
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	 HttpSession session=req.getSession();
 		String code=(String)session.getAttribute("validateCode");
 		String code2=req.getParameter("code");
 		if(code.toLowerCase().equals(code2.toLowerCase())){
 			//获取参数
 			String username=req.getParameter("username");//获取用户名
 			String password=req.getParameter("password");//获取密码
 			System.out.println(username);
 			System.out.println(password);
 			if(username==null||password==null){				
 				req.setAttribute("error","用户名或密码不能为空！");
 				req.getRequestDispatcher("managerLogin.jsp").forward(req, resp);
 			}
 			//验证用户是否存在
 			JudgeManagerExist judge=new JudgeManagerExist();
 			if(judge.judge(username, password)==0){//0表示用户名和密码正确
 				resp.sendRedirect("alluser.do");//直接跳转到用户管理
 			}
 			if(judge.judge(username, password)==-1){
 				req.setAttribute("error","用户名不存在！");
 				req.getRequestDispatcher("managerLogin.jsp").forward(req, resp);
 			}
 			if(judge.judge(username, password)==1){
 				req.setAttribute("error","密码不正确！");
 				req.getRequestDispatcher("managerLogin.jsp").forward(req, resp);
 			}
 		}
 		else{
 			req.setAttribute("error","验证码输入不一致！");
 			req.getRequestDispatcher("managerLogin.jsp").forward(req, resp);
 		}
     }
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
}
