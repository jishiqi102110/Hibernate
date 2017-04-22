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
		String reqURL = req.getRequestURL().toString();
		User admin = (User)session.getAttribute("user");//获取用户信息
		if(reqURL.indexOf("index.jsp")!=-1){	
			if(req.getAttribute("goodList")==null){
				req.getRequestDispatcher("getAllGoods.do").forward(req, resp);
				return;
			}
			else{
				chain.doFilter(req, resp);
				return;//return 必须加，不然后面的代码会执行
			}
		}
		 if(admin!=null){
			//说明已经登录
			chain.doFilter(req,resp);
			return;
		}
		 else if(admin==null){
			//如果是没授权的页面的话就去登录页面
			for(int i=0;i<notAllowedList.size();i++){
				if(reqURL.indexOf(notAllowedList.get(i))!=-1){
					if(reqURL.indexOf("addFavorite")!=-1){
						
						resp.sendRedirect("../login.jsp?loginmsg=1");
					}
					else{
						resp.sendRedirect("login.jsp?loginmsg=1");
					}
					return;
				}
			}
		}
		 chain.doFilter(req, resp);
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		notAllowedList.add("IssueHeart.jsp");
		notAllowedList.add("PersonalGoodsMagager.do");
		//notAllowedList.add("PersonGoodsManager.jsp");		
		notAllowedList.add("addFavorite");	
		notAllowedList.add("favorites.jsp");
		notAllowedList.add("getGoods");
		notAllowedList.add("ptrasaction");
		
	}
}
