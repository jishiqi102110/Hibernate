package yishan.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import yishan.Dao.UserDao;
import yishan.Po.User;

@Controller
public class UserController implements IUseController {
	@RequestMapping("login")
	@Override
	public   String login(User user,HttpSession session,String code) {
		// TODO Auto-generated method stub
		String scode=(String)session.getAttribute("validateCode");
		System.out.println(scode);
		String msg="";
		if(scode.toLowerCase().equals(code.toLowerCase())){
			UserDao userdao=new UserDao();
			if(userdao.isExistsUser(user)==3){
				//用户名和密码都正确
				User u=new User();
				u.setName(user.getName());
				u.setPassword(user.getPassword());
				session.setAttribute("user",u);
				return "redirect:index.jsp";
			}
			if(userdao.isExistsUser(user)==0){
				//用户不存在
				return "redirect:login.jsp?error="+"0";
			}
			if(userdao.isExistsUser(user)==2){
				//密码不正确
				return "redirect:login.jsp?error="+"2";
			}
			return null;
		}
		else{
			//验证码错误
			return "redirect:login.jsp?error="+"4";
		}
		
	}
	@RequestMapping("register")
	@Override
	public String register(User user,String code,HttpSession session) {
		// TODO Auto-generated method stub
		String scode=(String)session.getAttribute("validateCode");
		
		if(scode.toLowerCase().equals(code.toLowerCase())){
			UserDao userdao=new UserDao();
			if(userdao.isExistsUser(user)>0){
				return "redirect:register.jsp?error="+"1";//存在该用户
			}
			else{
			     userdao.register(user);
			     return "redirect:login.jsp?msg=1&&name="+user.getName();
			}
		}
		else{
			//验证码错误
			return "redirect:register.jsp?error="+"4";
		}
		
	}
	@RequestMapping("exist")
	@Override
	public void ajaxUserExists(String name, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setName(name);
		//System.out.println(u.getName());
		UserDao userdao=new UserDao();
		
		int i=userdao.isExistsUser2(u);
		if(i>0){
			try {
				response.getWriter().print(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				response.getWriter().print(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
