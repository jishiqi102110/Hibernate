package Control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.judgeUserExists;
//单独获取
public class Confirmusername extends HttpServlet{
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		PrintWriter out = resp.getWriter();
  		resp.setHeader("Cache-Contral", "no-store");//HTTP1.1 ���������������ҳ������
  		resp.setHeader("Pragma", "no-cache");//HTTP1.0���������������ҳ������
  		resp.setDateHeader("Expires", 0);//��ֹ�Ӵ���������ϻ�ȡ
  		
  		String username = req.getParameter("username");
  		judgeUserExists judge=new judgeUserExists();
  		if(username != null && !username.trim().equals("")) {
  			if(!judge.judge(username)) {
  				out.print("exsits");
  			} else {
  				out.print("notexsits");				
  			}
  		} 
  		out.flush();
  		out.close();
    	
    }
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	this.doPost(req, resp);
    }
}
