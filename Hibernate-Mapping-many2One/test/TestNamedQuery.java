import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;

public class TestNamedQuery extends TestCase{
	public void testQuery1() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
		     String sql="select * from t_user where uid<?";
		     Query query=session.getNamedQuery("TestNamedQuery");
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     List list=query.list();
		     for(int i=0;i<list.size();i++){
		    	User u=(User) list.get(i)	;    			 
		    	 System.out.println("ID"+u.getId()+"name="+u.getName());
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
		     Query query=session.getNamedQuery("TestNamedQuery2");
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     query.setParameter(0, 3);
		     List list=query.list();
		     for(int i=0;i<list.size();i++){
		    	User u=(User) list.get(i)	;    			 
		    	 System.out.println("ID"+u.getId()+"name="+u.getName());
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
		     Query query=session.getNamedQuery("TestNamedSQLQuery");
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     query.setParameter(0, 3);
		     List list=query.list();
		     for(int i=0;i<list.size();i++){
		    	 Object obj=list.get(i);
		    	 Object[] objArr=(Object[])obj;		    			 
		    	 System.out.println("ID"+objArr[0]+"name="+objArr[1]);
		     }
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
