package yishan.Filter;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yishan.Po.User;



public class loginFilter implements Filter {
    private ArrayList<String> notAllowedList=new ArrayList<String>();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
//		System.out.println("------------------");
		String reqURI = req.getRequestURI();
		String reqURL = req.getRequestURL().toString();
		//System.out.println("reqURI: " + reqURI);
		//System.out.println("reqURL: " + reqURL);
		User admin = (User)session.getAttribute("user");//获取用户信息
		if(admin!=null){
			//说明已经登录
			chain.doFilter(request,response);
		}
		if(admin==null){
			//如果是没授权的页面的话就去登录页面
			for(int i=0;i<notAllowedList.size();i++){
				if(reqURL.indexOf(notAllowedList.get(i))!=-1){
					request.setAttribute("loginmsg","1");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			chain.doFilter(request,response);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	    //this.url1="http://localhost:8080/ShoolTiaoZao/goodsIssue.jsp";
		notAllowedList.add("IssueHeart.jsp");
	}

}
