import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Po.Group;
import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;

public class TesTJoinQuery extends TestCase{
	
	public void testQuery1() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
		     String hql="select u.id,u.name,g.id,g.name from User u ,Group g where u.group.id=g.id ";
		     Query query=session.createQuery(hql);
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	Object[] objArr=list.get(i);
		    	System.out.println("u.id"+objArr[0]+"  u.name="+objArr[1]+"  g.id="+objArr[2]+"  g.name="+objArr[3]);
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
			//这样就产生笛卡尔积
		     String hql="select u.id,u.name,g.id,g.name from User u ,Group g";
		     Query query=session.createQuery(hql);
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	Object[] objArr=list.get(i);
		    	System.out.println("u.id"+objArr[0]+"  u.name="+objArr[1]+"  g.id="+objArr[2]+"  g.name="+objArr[3]);
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
			//这样就产生笛卡尔积
		     String hql="select u.id,u.name,g.id,g.name from User u join u.group g";
		     Query query=session.createQuery(hql);
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	Object[] objArr=list.get(i);
		    	System.out.println("u.id"+objArr[0]+"  u.name="+objArr[1]+"  g.id="+objArr[2]+"  g.name="+objArr[3]);
		     }
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	//插入几个没有Group的User
	public   void testSave(){
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		Transaction tras=null;
		try {
			session=util.OpenSession();
			tras=session.beginTransaction();
			
			User u1=new User();
			u1.setName("梁伟涛");
			u1.setPassword("123");
			
			
			User u2=new User();
			u2.setName("liangweitao");
			u2.setPassword("4565");
		
			session.save(u2);
			session.save(u1);			
		    tras.commit();
		} catch (Exception e) {
		     if(tras!=null){
		    	tras.rollback();
		     }
		}finally {
			session.close();
		}
	}
	public void testQuery4() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			//这样就产生笛卡尔积
		     String hql="select u.id,u.name,g.id,g.name from User u left outer join u.group g";
		     Query query=session.createQuery(hql);
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	Object[] objArr=list.get(i);
		    	System.out.println("u.id"+objArr[0]+"  u.name="+objArr[1]+"  g.id="+objArr[2]+"  g.name="+objArr[3]);
		     }
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	public void testQuery5() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			//这样就产生笛卡尔积
		     String hql="select u.id,u.name,g.id,g.name from User u right outer join u.group g";
		     Query query=session.createQuery(hql);
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	Object[] objArr=list.get(i);
		    	System.out.println("u.id"+objArr[0]+"  u.name="+objArr[1]+"  g.id="+objArr[2]+"  g.name="+objArr[3]);
		     }
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	public void testQuery6s() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			//这样就产生笛卡尔积
		     String hql="select u.id,u.name,g.id,g.name from User u full join u.group g";
		     Query query=session.createQuery(hql);
//		     query.setParameter(0, "5");
//		     query.setFirstResult(0);
//		     query.setMaxResults(3);
		     List<Object[]> list=query.list();
		     for(int i=0;i<list.size();i++){
		    	Object[] objArr=list.get(i);
		    	System.out.println("u.id"+objArr[0]+"  u.name="+objArr[1]+"  g.id="+objArr[2]+"  g.name="+objArr[3]);
		     }
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
