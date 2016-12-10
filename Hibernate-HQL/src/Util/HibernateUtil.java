package Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static HibernateUtil instance;
	private SessionFactory factory=null;
	public static HibernateUtil getHibernateUtilInstance(){
		if(instance==null){
			instance=new HibernateUtil();
		}
		return instance;
	}
	public HibernateUtil(){
		factory=new Configuration().configure().buildSessionFactory();
	}
	public Session OpenSession(){
		return factory.openSession();
	}
}
