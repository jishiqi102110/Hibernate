package yishan.test.Dao;

import junit.framework.TestCase;
import yishan.Dao.UserDao;
import yishan.Po.User;

public class testDao extends TestCase {
	
   public void testgetUserGoods(){
	   UserDao dao=new UserDao();
	   User u=new User();
       u.setId("402859815a3f9740015a3f976a7c0000");
       
       dao.getUserGoods(u);
       
	   
   }
}
