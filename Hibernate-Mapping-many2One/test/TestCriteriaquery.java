import java.security.acl.Group;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import Po.User;

import Util.HibernateUtil;
import junit.framework.TestCase;
public class TestCriteriaquery  extends TestCase{
	public void testQuery1() {
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Criteria c=session.createCriteria(User.class);
			//返回的对象数组；
			c.add(Restrictions.like("name","梁%"));
			
			List list= c.list();
			for(int i=0;i<list.size();i++){
				User u=(User) list.get(i);
				System.out.println("ID="+u.getId()+"name="+u.getName());
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
			Criteria c=session.createCriteria(User.class).add(Restrictions.between("id", 1, 5)).addOrder( Order.desc("id"));			//返回的对象数组；
			
			List list= c.list();
			for(int i=0;i<list.size();i++){
				User u=(User) list.get(i);
				System.out.println("ID="+u.getId()+"name="+u.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	/*
	public void TestQuery(String name ,String password){
		String hql="from Student s where 1=1";
		if(name!=null){
			hql+=" and s.name like '"+name+"'";
		}
		if(password!=null){
			hql+=" and s.password lile '"+password+"'";
		}
	}
	*/
	//多条件查询
	public void testQuery3() {
		String name="%伟%";
		String group="%佳宾%";
		HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
		Session session=null;
		try{
			session=util.OpenSession();
			Criteria c=session.createCriteria(User.class,"u");
			//关联查询
			
			
			if(name!=null){
				c.add(Restrictions.like("u.name",name));
			}
			//为关联对象加条件
			c.createCriteria("group","g");
			if(group!=null){
				c.add(Restrictions.like("g.name", group));
			}
			
			/*
			 * HQL：from User s where s.name like ‘%伟%’ and s.group.name like '%嘉宾%'；
			 */
			List list= c.list();
			for(int i=0;i<list.size();i++){
				User u=(User) list.get(i);
				System.out.println("ID="+u.getId()+"name="+u.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
}
