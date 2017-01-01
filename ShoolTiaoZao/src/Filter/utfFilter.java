package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class utfFilter
 */
@WebFilter("/utfFilter")
public class utfFilter implements Filter {

  
    public utfFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		//继续执行
        //过滤错误异常
        try{        	
        	chain.doFilter(request, response);
        }
        catch(Exception e){
        	request.setAttribute("exception", e);
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
