package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addUser {
    Connection conn=null;
    ConnectionFactory factory=new ConnectionFactory();
    ResultSet result=null;
    PreparedStatement stmt=null;
    boolean flag=true;
      public boolean adduser(String username,String password,String tel ,String email,String qq){   		    
    		   String sql = "insert into  user(username,password,phoneNumber,email,qq) value(?,?,?,?,?)";
    		  conn=factory.getConnection();
    		   try {
    			stmt=conn.prepareStatement(sql);
    			 stmt.setString(1, username);
    			 stmt.setString(2, password);
    			 stmt.setString(3,tel);
    			 stmt.setString(4,email);
    			 stmt.setString(5,qq);
    			 int i=stmt.executeUpdate();
    			 if(i==0){
    				 flag=false;
    			 }
    			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 factory.free(conn, stmt);
    		  return flag;
      }
}
