package Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.PersonalGoods;

public class mohuSearchGoods extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConcurrentHashMap<PersonalGoods, String> map = new ConcurrentHashMap<PersonalGoods, String>();
		String cond = req.getParameter("cond");
		System.out.println(cond);
		if (req.getParameter("cond") != null) {
			ConnectionFactory factory = new ConnectionFactory();
			Connection conn = factory.getConnection();
			ResultSet rs = null;
			String sql = "SELECT imageAddress,goodsName,goodsPrice,time,userID FROM goods WHERE goodsName LIKE ? or goodsPrice LIKE ? or goodsClass LIKE ?";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + cond + "%");
				pstmt.setString(2, "%" + cond + "%");
				pstmt.setString(3, "%" + cond + "%");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					PersonalGoods p = new PersonalGoods();
					p.setGoodsPictureAddress(rs.getString(1));
					p.setGoodsname(rs.getString(2));
					p.setGoodsprice(rs.getString(3));
					p.setTime(rs.getString(4));
					p.setUserID(rs.getString(5));
					map.put(p, rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			factory.free(conn, rs, pstmt);
			req.setAttribute("cond", cond);
			if(req.getParameter("min")!=null||req.getAttribute("max")!=null){
				if ((req.getParameter("min") != null && req.getParameter("max") != null)
						&& (!req.getParameter("min").equals("") && !req.getParameter("max").equals(""))) {
					double min = Double.parseDouble(req.getParameter("min"));
					double max = Double.parseDouble(req.getParameter("max"));
					System.out.println(min);
					System.out.println(max);
					for (Map.Entry<PersonalGoods, String> entry : map.entrySet()) {
						PersonalGoods personGoods = (PersonalGoods) entry.getKey();// 如果存在该商品
						double price = Double.parseDouble(personGoods.getGoodsprice());
						if (price > max || price < min) {// 如果没在这个区间
							map.remove(personGoods);
						}
					}
				}
				// 如果只有最大值
				if (!req.getParameter("max").equals("")&&req.getParameter("min").equals("")) {
					double max = Double.parseDouble(req.getParameter("max"));
					for (Map.Entry<PersonalGoods, String> entry : map.entrySet()) {
						PersonalGoods personGoods = (PersonalGoods) entry.getKey();// 如果存在该商品
						double price = Double.parseDouble(personGoods.getGoodsprice());
						if (price > max) {// 如果没在这个区间
							map.remove(personGoods);
						}
					}
				}
				if (!req.getParameter("min").equals("")&&req.getParameter("max").equals("")) {
					double min = Double.parseDouble(req.getParameter("min"));
					for (Map.Entry<PersonalGoods, String> entry : map.entrySet()) {
						PersonalGoods personGoods = (PersonalGoods) entry.getKey();// 如果存在该商品
						double price = Double.parseDouble(personGoods.getGoodsprice());
						if (price < min) {// 如果没在这个区间
							map.remove(personGoods);
						}
					}
				}
			}
			req.setAttribute("goodsMap", map);
			req.getRequestDispatcher("GoodsList.jsp").forward(req, resp);	
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);
	}
}
