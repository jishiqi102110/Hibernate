package yishan.Dao;

import java.util.List;
import java.util.Map;

import yishan.Po.Deal;
import yishan.Po.Goods;
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
	public Goods getGoodsbyID(String ID);
	public User getUserByGoodsID(String GoodsID);
	public boolean saveDeal(Deal deal);
	public boolean isExistDeal(String UserID,String GoodsID);
	public List getALLDeal();//得到所有交易
	public List getUndoneDeal();//得到未完成交易
	public List getDoneDeal();//得到完成交易
	public List getGoodsAllProperty(String type);
	public List getAllVote();
	public List getAllUser();
	public User getUserByID(String id);
	public void updataVote(String id,int vote);//更新投票
	public List getpersongetDeal(String userID);
	public List getpersondisDeal(String userID);
	public void agree(String id);
	public void disagree(String id);
	public List getpersonagreeDeal(String userID);//获取别人同意的请求
	public void evaluate(String dealid,String evaluate);
	public void dianzan(String userid);
	public int getUserVote(String userid);
	
}
