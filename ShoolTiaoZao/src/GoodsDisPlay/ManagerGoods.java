package GoodsDisPlay;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.PersonalGoods;
import Dao.searchGoodsForIndex;

public class ManagerGoods  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		searchGoodsForIndex index=new searchGoodsForIndex();
		ConcurrentHashMap<PersonalGoods, String>  map=index.getindexGoodsMap();
		 req.setAttribute("goods",map); 
   	     req.getRequestDispatcher("ManagerGoods.jsp").forward(req, resp);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
           this.doPost(req, resp);		
	}
}
