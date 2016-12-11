import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;

public class TestQuerySQl extends TestCase{	    
	public void testQuery1() {
		
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
		     String sql="select * from t_user where uid<?";
		     Query query=session.createSQLQuery(sql);
		     query.setParameter(0, "5");
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
public void testQuery2() {
		
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
		     String sql="select * from t_user where uid<:sid";
		     Query query=session.createSQLQuery(sql);
		     query.setParameter("sid", "5");
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
