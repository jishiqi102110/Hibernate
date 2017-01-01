package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Bean.PersonalGoods;

public class SearchGoodsOfUser {
      public ConcurrentHashMap<PersonalGoods,String> getPersonGoodsMap(String userID){
    	  ConcurrentHashMap<PersonalGoods, String> map=new ConcurrentHashMap<PersonalGoods,String>();
    	  //链接数据库
    	  ConnectionFactory factory=new ConnectionFactory();
    	  Connection conn=factory.getConnection();
    	      ResultSet result=null;
    	      PreparedStatement stmt=null;
    				try {
    					String sql = "select * from goods where userID="+userID;
    					stmt = conn.prepareStatement(sql);
    					result = stmt.executeQuery();
    					while(result.next()){	
    					    String goodsname=result.getString("goodsName");
    					    String goodsprice=result.getString("goodsPrice");
    					    String imageAddress=result.getString("imageAddress");
    					    String time=result.getString("time");
    					    String goodsID=result.getString("goodID");
    					    //复值，然后存入Map
    					    PersonalGoods personnalGoods=new PersonalGoods();
    					    personnalGoods.setGoodsname(goodsname);
    					    personnalGoods.setGoodsPictureAddress(imageAddress);
    					    personnalGoods.setGoodsprice(goodsprice);
    					    personnalGoods.setTime(time);
    					    personnalGoods.setGoodsID(goodsID);
    					    map.put(personnalGoods,userID);
    					}
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				factory.free(conn, result,stmt);//释放资源
    	  return map;
      }
}
