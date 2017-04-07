package yishan.Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import yishan.Po.Goods;
import yishan.Po.User;
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
				Goods goods=(Goods) session.load(Goods.class,ID);
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
}
