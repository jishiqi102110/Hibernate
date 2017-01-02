package Manager;

import java.util.concurrent.ConcurrentHashMap;

import Bean.User;

public interface IUserManager {
	public boolean addUser(String username,String password,String phoneNumber ,String email,String qq);
    public int delUser(String userID);
    public ConcurrentHashMap<User,String> getAlluser();
}
