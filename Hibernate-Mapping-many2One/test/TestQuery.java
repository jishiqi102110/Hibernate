import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;
public class TestQuery extends TestCase {
	public void testQuery1() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Query query= session.createQuery("select id,name from User");
			//返回的对象数组；
			List list= query.list();
			for(int i=0;i<list.size();i++){
				Object obj=list.get(i);
				//System.out.println("ItemName"+obj.getClass().getName());
				Object[] objArr=(Object[])obj;
				System.out.println(objArr[0]);
				System.out.println(objArr[1]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	//动态实例化查询,面向对象查询
	public void testQuery2() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Query query= session.createQuery("select new User(id,name) from User");
			//返回的对象数组；
			List list= query.list();
			for(int i=0;i<list.size();i++){
				Object obj=list.get(i);
				//System.out.println("ItemName"+obj.getClass().getName());
                 User u=(User)obj;
                 System.out.println("ID="+u.getId()+"    name="+u.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	//面向对象查询查出所有的对象
		public void testQuery3() {
			HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
			Session session=null;
			try{
				session=util.OpenSession();
				Query query= session.createQuery("from User");
				//返回的对象数组；
				List list= query.list();
				for(int i=0;i<list.size();i++){
					Object obj=list.get(i);
					//System.out.println("ItemName"+obj.getClass().getName());
	                 User u=(User)obj;
	                 System.out.println("ID="+u.getId()+"    name="+u.getName());
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				session.close();
			}
		}
		//面向对象查询查出所有的对象使用Iterrate
		/*
		 * Iterator和list的区别
		 * Iterator查询后会往缓存里面放置，并使用缓存
		 * list查询过后会放置数据，但是不使用缓存，而是重新查询
		 */
				public void testQuery4() {
					HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
					Session session=null;
					try{
						session=util.OpenSession();
						Query query= session.createQuery("from User");
						//返回的对象数组；
						Iterator<User> iter=query.iterate();
						while(iter.hasNext()){
							User u=iter.next();
							System.out.println("ID+"+u.getId()+"  name="+u.getName());
						}
					}catch(Exception e){
						e.printStackTrace();
					}finally {
						session.close();
					}
				}
}
