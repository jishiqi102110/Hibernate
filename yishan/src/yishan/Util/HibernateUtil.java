package yishan.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static HibernateUtil instance;
	private SessionFactory factory;
	public static HibernateUtil getHibernateUtil (){
		if(instance==null){
			instance=new HibernateUtil();
		}
		return instance;
	}
      public HibernateUtil(){
    	  factory =new Configuration().configure().buildSessionFactory();
    	  
      }
      public Session Openssion(){
    	  return factory.openSession();
      }
}
