package Manager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Bean.User;
import Util.HibernateUtil;

public class UserManagerImp implements IUserManager{
     
	private  HibernateUtil util=HibernateUtil.getHibernateUtilInstance();
	private Session session=null;
	private Transaction tras=null;
	@Override
	public boolean addUser(String username, String password, String phoneNumber, String email, String qq) {
		// TODO Auto-generated method stub
		boolean flag=true;
		User u=new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setPhoneNumber(phoneNumber);
		u.setEmail(email);
		u.setQq(qq);
		
		try {
			session=util.OpenSession();
			tras=session.beginTransaction();
			session.save(u);
			tras.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tras!=null){
				tras.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return flag;
	}

}
