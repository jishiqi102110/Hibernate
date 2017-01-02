package Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.User;

public class loginFilter implements Filter {
    
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String reqURL=null;
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
//		System.out.println("reqURI: " + reqURI);
//		System.out.println("reqURL: " + reqURL);
		User admin = (User)session.getAttribute("admin");//获取用户信息
		if(admin!=null){
			//说明已经登录
			chain.doFilter(request,response);
		}
		System.out.println(reqURL.endsWith(url5));
		if(admin==null){
			//如果是url，Url2,url3的话就去登录页面
			if(reqURL.endsWith(url1)||reqURL.endsWith(url2)||reqURL.endsWith(url4)||reqURL.endsWith(url3)||reqURL.endsWith(url5)){
				request.setAttribute("error","不能执行该操作！请先登录！");
				if(reqURL.endsWith(url5)){
					//如果是管理员登录则跳转到管理员登录页面
					request.getRequestDispatcher("managerLogin.jsp").forward(request, response);
				}
				else{					
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			//如果没有登录但是是游客则不进行过滤
			else{	
				System.out.println(reqURL);
				chain.doFilter(request,response);
			}
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	    //this.url1="http://localhost:8080/ShoolTiaoZao/goodsIssue.jsp";
	    this.url1="personalGoodsDisplay.do";
		this.url2="goodsIssue.jsp";
	    this.url4="shopCart.jsp";
	    this.url3="addCart.do";
	    this.url5="alluser.do";
	    
	}

}
