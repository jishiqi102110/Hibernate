package Manager;

public interface IUserManager {
	public boolean addUser(String username,String password,String phoneNumber ,String email,String qq);
    public int delUser(String userID);
}
