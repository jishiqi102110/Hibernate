import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;

import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;

public class TestQueryFilter  extends TestCase{
	public void testQuery3() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Filter filter=session.enableFilter("TestQueryFilter");
			String hql="from User";
			filter.setParameter("fname","%liang%");
		     Query query=session.createQuery(hql);
		     List list=query.list();
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
}
