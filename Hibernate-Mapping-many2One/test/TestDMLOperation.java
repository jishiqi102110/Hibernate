import org.hibernate.Session;
import org.hibernate.Transaction;

import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;

public class TestDMLOperation extends TestCase{
	//和缓存不同步，不建议使用
	public   void testSave(){
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		Transaction tras=null;
		try {
			session=util.OpenSession();
			tras=session.beginTransaction();
            String hql="insert into User(name) select u.name || '_new' from User u where u.group is null";			
		    int res=session.createQuery(hql).executeUpdate();
		    System.out.println(res);
		    tras.commit();
		} catch (Exception e) {
		     if(tras!=null){
		    	tras.rollback();
		     }
		}finally {
			session.close();
		}
	}
	public   void testUpDate(){
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		Transaction tras=null;
		try {
			session=util.OpenSession();
			tras=session.beginTransaction();
            String hql="update User u set u.group.id=3 where u.group is null";			
		    int res=session.createQuery(hql).executeUpdate();
		    System.out.println(res);
		    tras.commit();
		} catch (Exception e) {
		     if(tras!=null){
		    	tras.rollback();
		     }
		}finally {
			session.close();
		}
	}

}
