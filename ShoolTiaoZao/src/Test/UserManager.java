package Test;

import java.util.Map;

import org.junit.Test;

import Manager.IUserManager;
import Manager.UserManagerImp;

public class UserManager {
      @Test
      public void testAddUser(){
    	  IUserManager manager=new UserManagerImp();
    	  boolean flag= manager.addUser("1","1","1","1","1");
    	  System.out.println(flag);
      }
      @Test
      public void testGetAllUser(){
    	  IUserManager manager=new UserManagerImp();
    	  Map m= manager.getAlluser();
    	  System.out.println(m.size());
      }
}
