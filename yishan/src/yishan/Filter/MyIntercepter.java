package yishan.Filter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyIntercepter implements HandlerInterceptor {
   public MyIntercepter() {
		// TODO Auto-generated constructor stub
	   ArrayList<String> notAllowedList=new ArrayList<String>();
	   notAllowedList.add("IssueHeart.jsp");
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		
		//System.out.println("处理前");
		return true;
	}

}
