import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;

public class TestConditionQuery extends TestCase {
	public void testQuery1() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Query query= session.createQuery("select id,name from User where name like ?");
			//返回的对象数组；
			query.setParameter(0, "梁%");
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
	public void testQuery2() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Query query= session.createQuery("select id,name from User where name like :pname").setParameter("pname","梁%");
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
	public void testQuery3() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Query query= session.createQuery("from User u where u.name like :pname and u.id in (:pid)").setParameter("pname","梁%").setParameterList("pid",new Object[]{2,4,6});
			//返回的对象数组；
			List list= query.list();
			for(int i=0;i<list.size();i++){
				User u=(User) list.get(i);
				System.out.println("id="+u.getId()+"name="+u.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
