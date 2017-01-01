package Dao;
	import java.sql.SQLException;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

	public class ConnectionFactory {
	    private Connection conn=null; 
		public Connection getConnection(){	
				try {
					Context context = new InitialContext();
					Object obj = context.lookup("java:comp/env/jdbc/schoolmarket");
					DataSource dataSource = (DataSource)obj;
					conn = dataSource.getConnection();
				} catch (NamingException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        return conn;
	}
		public void  free(Connection conn,ResultSet result,PreparedStatement stmt){
			try {
				result.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void  free(Connection conn,PreparedStatement stmt){
			try {
				
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
