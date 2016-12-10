

import org.hibernate.Session;
import org.hibernate.Transaction;
import Po.Group;
import Po.User;
import Util.HibernateUtil;
import junit.framework.TestCase;

public class Test extends TestCase{
	public   void testSave(){
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		Transaction tras=null;
		try {
			session=util.OpenSession();
			tras=session.beginTransaction();
			Group g1=new Group();
			g1.setName("贵宾");
			session.save(g1);
			
			User u1=new User();
			u1.setName("梁伟涛");
			u1.setPassword("123");
			u1.setGroup(g1);
			
			
			User u2=new User();
			u2.setName("liangweitao");
			u2.setPassword("4565");
			u2.setGroup(g1);
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
  /*
   * 
   * 通过user 加在Group
   * load是lazy加载只有在使用对象的时候才进行查询
   * get不是lazy加载
   * 默认lazy加载时true
   * 可以在配置文件中设置
   * 
   * 
   * lazy加载只有在session没有关闭之前有用
   */
	
	public   void testLoad1(){
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		Transaction tras=null;
		try {
			session=util.OpenSession();
			tras=session.beginTransaction();
			User user=(User) session.load(User.class, 1);
			System.out.println("user.name"+user.getName());
			System.out.println("user.group"+user.getGroup().getName());			
		    tras.commit();
		} catch (Exception e) {
		     if(tras!=null){
		    	tras.rollback();
		     }
		}finally {
			session.close();
		}
	}
	
	public   void testGet1(){
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		Transaction tras=null;
		try {
			session=util.OpenSession();
			tras=session.beginTransaction();
			User user=(User) session.get(User.class, 1);
			System.out.println("user.name"+user.getName());
			System.out.println("user.group"+user.getGroup().getName());			
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
