package Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class judgeUserExists {
      Connection conn=null;
      ConnectionFactory factory=new ConnectionFactory();
      ResultSet result=null;
      PreparedStatement stmt=null;
      boolean flag=true;
      public String getUserID(String username){
    	  String userID=null;
    	  conn=factory.getConnection();
			try {
				String sql = "select * from user";
				stmt = conn.prepareStatement(sql);
				result = stmt.executeQuery();
				while(result.next()){	
				    String name=result.getString("username");
				   if(username.equals(name)){
					   //说明用户已经存在
					    userID=result.getString("userID");
				   }
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			factory.free(conn, result,stmt);//释放资源
  	   return userID;
      }
       public boolean judge(String username){
    	   conn=factory.getConnection();
			try {
				String sql = "select * from user";
				stmt = conn.prepareStatement(sql);
				result = stmt.executeQuery();
				while(result.next()){	
				    String name=result.getString("username");
				   if(username.equals(name)){
					   //说明用户已经存在
					   flag=false;
				   }
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			factory.free(conn, result,stmt);//释放资源
    	   return flag;
       }
	public int judge1(String username, String password) {
		int flag=-1;
		/*
		 * flag=0 表示用户名和密码正确
		 * flag=1 表示密码不正确
		 * flag=-1表示用户不存在
		 * 
		 * */
		 conn=factory.getConnection();
			try {
				String sql = "select * from user";
				stmt = conn.prepareStatement(sql);
				result = stmt.executeQuery();
				while(result.next()){	
				    String name=result.getString("username");
				    String pd=result.getString("password");
				   if(username.equals(name)){
					   //说明用户已经存在
					   if(password.equals(pd)){
						   flag=0;//用户名和密码正确
					   }
					   else{//密码不正确
						   flag=1;
					   }
				   }
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			factory.free(conn, result,stmt);//释放资源
 	   return flag;
	}
		

}
