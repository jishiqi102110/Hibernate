package GoodsDisPlay;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.PersonalGoods;
import Dao.searchGoodsForIndex;

public class indexDisplay extends HttpServlet {
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		searchGoodsForIndex index=new searchGoodsForIndex();
		ConcurrentHashMap<PersonalGoods, String>  map=index.getindexGoodsMap();
		 request.setAttribute("goods",map);
   	     request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
