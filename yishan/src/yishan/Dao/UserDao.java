package yishan.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;

import yishan.Po.Deal;
import yishan.Po.Goods;
import yishan.Po.Swap;
import yishan.Po.User;
import yishan.Po.Vote;
import yishan.Util.HibernateUtil;

public class UserDao implements IUserDao{
	 HibernateUtil util;
	 Session session;
	public UserDao(){
		this.util=HibernateUtil.getHibernateUtil();
		this.session=util.Openssion();
	}
     public int  isExistsUser(User u){
    	 /*
    	  * 1代表存在该用户
    	  * 0代表不存在
    	  * 2代表密码不正确
    	  * 3代表密码和用户名都正确
    	  */
    	 String hql="from User as user where name=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",u.getName());
    	 int i=query.list().size();
    	 List list=query.list();
    	 if(i>0){
    		//存在用户
    		 for(int j=0;j<list.size();j++){
    			 Object object=list.get(j);
    			 User uu=(User)object;
    			 if(u.getPassword().equals(uu.getPassword())){
    				 return 3;
    			 }
    			 else{
    				 return 2;//密码不正确
    			 }
    		 }
    	 }
    	 //用户不存在
    	 return i;
     }
     public int  isExistsUser2(User u){
    	 /*
    	  * 1代表存在该用户
    	  * 0代表不存在
    	  * 2代表密码不正确
    	  * 3代表密码和用户名都正确
    	  */
    	 String hql="from User as user where name=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",u.getName());
    	 int i=query.list().size();
    	 //用户不存在
    	 return i;
     }
     public boolean register(User user){
    	 Transaction tras=session.beginTransaction();
    	 try {
    		  user.setVote(0);
        	   session.save(user);
			   tras.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if(tras!=null){
				 tras.rollback();
				 return false;
			}
			e.printStackTrace();
		}finally{
			  session.close();
		}
    	return true;
     }
     public User getUserByName(String name){
    	 String hql="from User as user where name=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",name);
    	 User user=(User) query.list().get(0);
    	 return user;
     }
	@Override
	public List getUserGoods(User u) {
		// TODO Auto-generated method stub
		String hql="from Goods as goods where goods.user.name=:n ";
		Query query=this.session.createQuery(hql);
		query.setParameter("n",u.getName());
		List<Goods> list=query.list();
		return list;
	}
	@Override
	public List getSearchGoods(String keyword) {
		// TODO Auto-generated method stub
		
		String hql="from Goods as g where g.name like :name or g.type like :type or g.discription like :discription";
		Query query=this.session.createQuery(hql);
		query.setParameter("name", "%"+keyword+"%");
		query.setParameter("type", "%"+keyword+"%");
		query.setParameter("discription", "%"+keyword+"%");	
		List<Goods> glist=query.list();
		return glist;	
	}
	@Override
	public List getAllGoods() {
		// TODO Auto-generated method stub
		String hql="from Goods as g";
		Query query=this.session.createQuery(hql);
		List<Goods> goodsList=query.list();
		return goodsList;
	}
	@Override
	public boolean deleteGoods(String ID) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		 try {
				Goods goods=(Goods) session.get(Goods.class,ID);
				session.delete(goods);
				session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if(tras!=null){
				 tras.rollback();
				 return false;
			}
			e.printStackTrace();
		}finally{
			  session.close();
		}
		return true;
	}
	@Override
	public Goods getGoodsbyID(String ID) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		Goods g=null;
		 try {
				g=(Goods) session.get(Goods.class,ID);
				session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if(tras!=null){
				 tras.rollback();
			}
			e.printStackTrace();
		}finally{
			  session.close();
		}
		return g;
	}
	@Override
	public User getUserByGoodsID(String GoodsID) {
		// TODO Auto-generated method stub
		 String hql="from Goods as g where g.id=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",GoodsID);
         Goods goods=(Goods) query.list().get(0);
    	 User user=goods.getUser();
    	 return user;
	}
	@Override
	public boolean saveDeal(Deal deal) {
		// TODO Auto-generated method stub
		 Transaction tras=session.beginTransaction();
    	 try {
        	   session.save(deal);
			   tras.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if(tras!=null){
				 tras.rollback();
				 return false;
			}
			e.printStackTrace();
		}finally{
			  session.close();
		}
    	return true;
	}
	@Override
	public boolean isExistDeal(String UserID, String GoodsID) {
		// TODO Auto-generated method stub
		 
   	 String hql="from Deal as deal where getter=:n";
   	 Query query= this.session.createQuery(hql);
   	 query.setParameter("n",UserID);
   	 int i=query.list().size();
   	 List list=query.list();
   	 if(i>0){
   		//存在用户
   		 for(int j=0;j<list.size();j++){
   			 Object object=list.get(j);
   			 Deal dd=(Deal)object;
   			 if(dd.getGoodsID().equals(GoodsID)){
   				 return false;//之前就存在该交易
   			 }
   		 }
   	 }
		return true;//之前未存在该交易
	}
	@Override
	public List getALLDeal() {
		// TODO Auto-generated method stub
		 String hql="from Deal";
		 Query query= this.session.createQuery(hql);
	   	 int i=query.list().size();
	   	 ArrayList<Deal> result=new ArrayList<>();
	   	 List list=query.list();
	   	 if(i>0){
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Deal pp=(Deal) object;
	   			result.add(pp);
	   		 }
	   	 }
			
		return result;
	}
	@Override
	public List getGoodsAllProperty(String type) {
		// TODO Auto-generated method stub
		 String hql="select p.property from goodsProperty as p,goodtype as t where t.typename=:n and p.typeid=t.id";
		 Query query= this.session.createQuery(hql);
	   	 query.setParameter("n",type);
	   	 int i=query.list().size();
	   	 ArrayList<String> result=new ArrayList<>();
	   	 List list=query.list();
	   	 if(i>0){
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 String pp=(String) object;
	   			result.add(pp);
	   		 }
	   	 }
			
		return list;
	}
	@Override
	public List getAllVote() {
		// TODO Auto-generated method stub
		ArrayList<Vote> vot=new ArrayList<>();
		for(int i=0;i<this.getAllUser().size();i++){
			User u=(User) this.getAllUser().get(i);
			ArrayList<String> goodsnames=new ArrayList<>();
			List<Goods> pGoods=this.getUserGoods(u);
			for(int j=0;j<pGoods.size();j++){
				goodsnames.add(pGoods.get(j).getName());
			}
			Vote v=new Vote();
			v.setUser(u);
			v.setGoodsname(goodsnames);
			vot.add(v);
		}
		return vot;
	}
	@Override
	public List getAllUser() {
		// TODO Auto-generated method stub
		 String hql="from User";
		 ArrayList<User> list=new ArrayList<>();
    	 Query query= this.session.createQuery(hql);
    	for(int i=0;i<query.list().size();i++){
    		list.add((User) query.list().get(i));
    	}    	
    	 return list;
	}
	@Override
	public void updataVote(String id,int vote) {
		// TODO Auto-generated method stub
		 Transaction tras=session.beginTransaction();
		 try{
			 String hql="update User user set user.vote=:n where user.id=:m";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n",vote);
			 queryupdate.setParameter("m",id);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public List getUndoneDeal() {
		// TODO Auto-generated method stub
		 String hql="from Deal as deal where deal.state=:n";
		 Query query= this.session.createQuery(hql);
		 query.setParameter("n", "undone");
	   	 int i=query.list().size();
	   	 ArrayList<Deal> result=new ArrayList<>();
	   	 List list=query.list();
	   	 if(i>0){
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Deal pp=(Deal) object;
	   			result.add(pp);
	   		 }
	   	 }
			
		return result;
	}
	@Override
	public List getDoneDeal() {
		// TODO Auto-generated method stub
		 String hql="from Deal as deal where deal.state=:n";
		 Query query= this.session.createQuery(hql);
		 query.setParameter("n","done");
	   	 int i=query.list().size();
	   	 ArrayList<Deal> result=new ArrayList<>();
	   	 List list=query.list();
	   	 if(i>0){
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Deal pp=(Deal) object;
	   			result.add(pp);
	   		 }
	   	 }
			
		return result;
	}
	@Override
	public User getUserByID(String id) {
		// TODO Auto-generated method stub
		
		 String hql="from User as user where user.id=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",id);
    	 User user=(User) query.list().get(0);
    	 return user;
	}
	@Override
	public List getpersongetDeal(String userID) {
		// TODO Auto-generated method stub
		 String hql="from Deal as d where d.getter=:n and d.state!=:m";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userID);
    	 query.setParameter("m","done");
    	 ArrayList<Deal> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Deal pp=(Deal) object;
	   			result.add(pp);
	   		 }
	   	 return result;
	}
	@Override
	public List getpersondisDeal(String userID) {
		// TODO Auto-generated method stub
		 String hql="from Deal as d where d.distributor=:n and d.state=:m";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userID);
    	 query.setParameter("m","undone");
    	 ArrayList<Deal> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Deal pp=(Deal) object;
	   			result.add(pp);
	   		 }
	   	 return result;
	}
	@Override
	public void agree(String id) {
		// TODO Auto-generated method stub
		 Transaction tras=session.beginTransaction();
		 try{
			 String hql="update Deal d set d.state=:n where d.dealID=:m";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n","done");
			 queryupdate.setParameter("m",id);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public void disagree(String id) {
		// TODO Auto-generated method stub
		 Transaction tras=session.beginTransaction();
		 try{
			 String hql="update Deal d set d.state=:n where d.dealID=:m";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n","disagree");
			 queryupdate.setParameter("m",id);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public List getpersonagreeDeal(String userID) {
		// TODO Auto-generated method stub
		String hql="from Deal as d where d.getter=:n and d.state=:m";
   	 Query query= this.session.createQuery(hql);
   	 query.setParameter("n",userID);
   	 query.setParameter("m","done");
   	 ArrayList<Deal> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Deal pp=(Deal) object;
	   			result.add(pp);
	   		 }
	   	 return result;
	}
	@Override
	public void evaluate(String dealid, String evaluate) {
		// TODO Auto-generated method stub
		 Transaction tras=session.beginTransaction();
		 try{
			
			 String hql="update Deal d set d.evaluate=:n where d.dealID=:m";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n",evaluate);
			 queryupdate.setParameter("m",dealid);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public void dianzan(String userid) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		 try{
			 
			 int vote=this.getUserVote(userid)+1;
			 String hql="update User u set u.vote=:m where u.id=:n";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n",userid);
			 queryupdate.setParameter("m",vote);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public int getUserVote(String userid) {
		// TODO Auto-generated method stub
		 String hql="from User as user where user.id=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userid);
    	 User user=(User) query.list().get(0);
    	 return user.getVote();
	}
	@Override
	public User getUserByGoodsName(String GoodsName) {
		// TODO Auto-generated method stub
		 String hql="from Goods as g where g.name=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",GoodsName);
         Goods goods=(Goods) query.list().get(0);
    	 User user=goods.getUser();
    	 return user;
	}
	@Override
	public Goods getGoodsbyGoodsName(String Goodsname) {
		// TODO Auto-generated method stub
		 String hql="from Goods as g where g.name=:n";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",Goodsname);
    	 Goods g=(Goods) query.list().get(0);
    	 return g;
	}
	@Override
	public boolean saveSWap(Swap swap) {
		// TODO Auto-generated method stub
		 Transaction tras=session.beginTransaction();
    	 try {
        	   session.save(swap);
			   tras.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if(tras!=null){
				 tras.rollback();
				 return false;
			}
			e.printStackTrace();
		}finally{
			  session.close();
		}
    	return true;
	}
	@Override
	public boolean isexistSwap(String disgID, String getgID) {
		// TODO Auto-generated method stub
		String hql="from Swap as s where s.disgID=:n and s.getgID=:m";
	   	 Query query= this.session.createQuery(hql);
	   	 query.setParameter("n",disgID);
	   	 query.setParameter("m",getgID);
	   	 int i=query.list().size();
	   	 List list=query.list();
	   	 if(i>0){
	   		//存在交换
	   		 return true;
	   	 }
		return false;
	}

	@Override
	public List getDoneSwap() {
		// TODO Auto-generated method stub
		 String hql="from Swap as s where s.state=:n";
		 Query query= this.session.createQuery(hql);
		 query.setParameter("n", "done");
	   	 int i=query.list().size();
	   	 ArrayList<Swap> result=new ArrayList<>();
	   	 List list=query.list();
	   	 if(i>0){
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Swap pp=(Swap) object;
	   			result.add(pp);
	   		 }
	   	 }
			
		return result;
	}
	@Override
	public List getUndoneSwap() {
		// TODO Auto-generated method stub
		 String hql="from Swap as s where s.state=:n";
		 Query query= this.session.createQuery(hql);
		 query.setParameter("n", "undone");
	   	 int i=query.list().size();
	   	 ArrayList<Swap> result=new ArrayList<>();
	   	 List list=query.list();
	   	 if(i>0){
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Swap pp=(Swap) object;
	   			 result.add(pp);
	   		 }
	   	 }
		return result;
	}
	@Override
	public List getpgetundoneSwap(String userID) {
		// TODO Auto-generated method stub
		 String hql="from Swap as s where s.getter=:n and s.state=:m";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userID);
    	 query.setParameter("m","undone");
    	 ArrayList<Swap> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Swap pp=(Swap) object;
	   			result.add(pp);
	   		 }
	   	 return result;
		
	
	}
	@Override
	public List getpdisundoneSwap(String userID) {
		// TODO Auto-generated method stub
		 String hql="from Swap as s where s.distributor=:n and s.state=:m";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userID);
    	 query.setParameter("m","undone");
    	 ArrayList<Swap> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Swap pp=(Swap) object;
	   			result.add(pp);
	   		 }
	   	 return result;
		
	}
	@Override
	public List getpdoneSwap(String userID) {
		// TODO Auto-generated method stub
		 String hql="from Swap as s where s.getter=:n and s.state=:m";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userID);
    	 query.setParameter("m","done");
    	 ArrayList<Swap> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Swap pp=(Swap) object;
	   			result.add(pp);
	   		 }
	   	 return result;
	}
	@Override
	public void deleteSwap(String SwapID) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		 try{
			 String hql="delete Swap as s  where s.swapID=:n";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n",SwapID);
			 queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public void agreeSwap(String SwapID) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		 try{
			 String hql="update Swap s set s.state=:n where s.swapID=:m";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n","done");
			 queryupdate.setParameter("m",SwapID);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public void disAgreeSwap(String SwapID) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		 try{
			 String hql="update Swap s set s.state=:n where s.swapID=:m";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n","disagree");
			 queryupdate.setParameter("m",SwapID);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public List getDisagreeSwap(String userID) {
		// TODO Auto-generated method stub
		 String hql="from Swap as s where s.getter=:n and s.state=:m";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userID);
    	 query.setParameter("m","disagree");
    	 ArrayList<Swap> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Swap pp=(Swap) object;
	   			result.add(pp);
	   		 }
	   	 return result;
	}
	@Override
	public void EvaluateSwap(String SwapID, String evaluate) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		 try{
			 String hql="update Swap s set s.evaluate=:n where s.swapID=:m";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n",evaluate);
			 queryupdate.setParameter("m",SwapID);
			 int ret=queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public List getPdisagreeMyDeal(String userID) {
		// TODO Auto-generated method stub
		 String hql="from Deal as d where d.getter=:n and d.state=:m";
    	 Query query= this.session.createQuery(hql);
    	 query.setParameter("n",userID);
    	 query.setParameter("m","disagree");
    	 ArrayList<Deal> result=new ArrayList<>();
	   	 List list=query.list();
	   		//存在用户
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Deal pp=(Deal) object;
	   			result.add(pp);
	   		 }
	   	 return result;
	}
	@Override
	public void DeleteDeal(String dealID) {
		// TODO Auto-generated method stub
		Transaction tras=session.beginTransaction();
		 try{
			 String hql="delete Deal as d where d.dealID=:n";
			 Query queryupdate=session.createQuery(hql);
			 queryupdate.setParameter("n",dealID);
			 queryupdate.executeUpdate();
			 tras.commit();
		 }catch(HibernateException e) {
				// TODO Auto-generated catch block
				if(tras!=null){
					 tras.rollback();
				}
				e.printStackTrace();
			}finally{
				  session.close();
			}
	}
	@Override
	public List getGoodsByDateOrder() {
		// TODO Auto-generated method stub
		 String hql="from Goods as g order by g.time desc";
		 Query query= this.session.createQuery(hql);
	   	 int i=query.list().size();
	   	 ArrayList<Goods> result=new ArrayList<>();
	   	 List list=query.list();
	   	 if(i>0){
	   		
	   		 for(int j=0;j<list.size();j++){
	   			 Object object=list.get(j);
	   			 Goods pp=(Goods) object;
	   			result.add(pp);
	   		 }
	   	 }
		return list;
	}


	
}
