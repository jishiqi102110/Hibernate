package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import Bean.PersonalGoods;
import Bean.User;

public class getAllUser {
    public ConcurrentHashMap<User,String> getuser(){
  	  ConcurrentHashMap<User, String> map=new ConcurrentHashMap<User,String>();
    	 ConnectionFactory factory=new ConnectionFactory();
      	  Connection conn=factory.getConnection();
      	      ResultSet result=null;
      	      PreparedStatement stmt=null;
      				try {
      					String sql = "select * from user ";
      					stmt = conn.prepareStatement(sql);
      					result = stmt.executeQuery();
      					while(result.next()){	
      					    String username=result.getString("username");
      					    String phoneNum=result.getString("phoneNumber");
      					    String qq=result.getString("qq");
      		                String userId=result.getString("userID");
      					    //复值，然后存入Map
      					     User user=new User();
      					     user.setUsername(username);
      					     user.setPhoneNumber(phoneNum);
      					     user.setQq(qq);
      					    map.put(user,userId);
      					}
      				} catch (SQLException e) {
      					// TODO Auto-generated catch block
      					e.printStackTrace();
      				}
      				factory.free(conn, result,stmt);//释放资源
      	System.out.println(map.size());
      	  return map;
    }
}
