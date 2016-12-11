import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Util.HibernateUtil;
import junit.framework.TestCase;
public class TestStaticsQuery extends TestCase{
	public void testQuery1() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
		     String hql="select max(id) from User ";
		     Query query=session.createQuery(hql);
		     List list=query.list();
		     System.out.println(list.get(0));
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	//分组查询
	public void testQuery2() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
		     String hql="select u.group.name, count(id) from User u group by u.group ";
		     Query query=session.createQuery(hql);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	 Object[] objArr=list.get(i);
		         System.out.println("组名："+objArr[0]+"\t人数"+objArr[1]);	 
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
		     String hql="select upper('liang'),name from User";
		     Query query=session.createQuery(hql);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	 Object[] objArr=list.get(i);
		         System.out.println("upper："+objArr[0]+"\t名字："+objArr[1]);	 
		     }
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
