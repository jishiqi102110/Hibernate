package yishan.Dao;

import java.util.List;

import yishan.Po.User;

public interface IUserDao {
	public User getUserByName(String name);
	public boolean register(User user);
	public int  isExistsUser2(User u);
	public int  isExistsUser(User u);
	public List getUserGoods(User u);
	public List getSearchGoods(String keyword);
	public List getAllGoods();
	public boolean deleteGoods(String ID);
}
