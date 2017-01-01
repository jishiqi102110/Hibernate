package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.PersonalGoods;

public class doDeletePersonGoods {
      public int deletePersonGoods(String goodID,String userID){
    	  int flag=0;
    	  ConnectionFactory factory=new ConnectionFactory();
    	  Connection conn=factory.getConnection();
    	      ResultSet result=null;
    	      PreparedStatement stmt=null;
    				try {
    					String sql = "delete from goods where userID="+userID+" and goodID="+goodID;
    					stmt = conn.prepareStatement(sql);
    					flag=stmt.executeUpdate();
    					System.out.println(flag);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				factory.free(conn,stmt);//释放资源
    	  return flag;
      }
}
