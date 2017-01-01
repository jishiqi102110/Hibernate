package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import Bean.PersonalGoods;

public class searchGoodsForIndex {
	 public ConcurrentHashMap<PersonalGoods,String> getindexGoodsMap(){
   	  ConcurrentHashMap<PersonalGoods, String> map=new ConcurrentHashMap<PersonalGoods,String>();
   	  //链接数据库
   	  ConnectionFactory factory=new ConnectionFactory();
   	  Connection conn=factory.getConnection();
   	      ResultSet result=null;
   	      PreparedStatement stmt=null;
   				try {
   					String sql = "select * from goods ";
   					stmt = conn.prepareStatement(sql);
   					result = stmt.executeQuery();
   					while(result.next()){	
   					    String goodsname=result.getString("goodsName");
   					    String goodsprice=result.getString("goodsPrice");
   					    String imageAddress=result.getString("imageAddress");
   					    String time=result.getString("time");
   					    String goodsID=result.getString("goodID");
   					    String userID=result.getString("userID");
   					    //复值，然后存入Map
   					    PersonalGoods personnalGoods=new PersonalGoods();
   					    personnalGoods.setGoodsname(goodsname);
   					    personnalGoods.setGoodsPictureAddress(imageAddress);
   					    personnalGoods.setGoodsprice(goodsprice);
   					    personnalGoods.setTime(time);
   					    personnalGoods.setGoodsID(goodsID);
   					    personnalGoods.setUserID(userID);;
   					    map.put(personnalGoods, goodsID);
   					}
   				} catch (SQLException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
   				factory.free(conn, result,stmt);//释放资源
   		
   	  return map;
     }
}
