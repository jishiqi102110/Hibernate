package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import Dao.ConnectionFactory;

public class AddGoodsToDB {
     public boolean addGoodsToDb(String goodsName,String goodsNum,String goodsPrice,String goodsClass,String imageAddress,String userID){
    	    Connection conn=null;
    	    ConnectionFactory factory=new ConnectionFactory();
    	    ResultSet result=null;
    	    PreparedStatement stmt=null;
    	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	    System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    	    String time=df.format(new Date());
    	    boolean flag=true;		    
    	    		   String sql = "insert into  goods(goodsName,goodsNum,goodsPrice,goodsClass,imageAddress,time,userID) value(?,?,?,?,?,?,?)";
    	    		  conn=factory.getConnection();
    	    		   try {
    	    			stmt=conn.prepareStatement(sql);
    	    			 stmt.setString(1, goodsName);
    	    			 stmt.setString(2, goodsNum);
    	    			 stmt.setString(3,goodsPrice);
    	    			 stmt.setString(4,goodsClass);
    	    			 stmt.setString(5,imageAddress);
    	    			 stmt.setString(6,time);
    	    			 stmt.setString(7,userID);    	    			 
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

