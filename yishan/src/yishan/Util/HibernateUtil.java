package yishan.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    	  BeanFactory beanfactory = new ClassPathXmlApplicationContext("applicationContext.xml");
     	 factory= (SessionFactory) beanfactory.getBean("sessionFactory");
      }
      public Session Openssion(){
    	  return factory.openSession();
      }
}
