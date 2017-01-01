package Test;

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
}
