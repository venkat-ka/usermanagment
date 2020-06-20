package com.techapp.usermanagement.util;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.techapp.usermanagement.model.Access;
import com.techapp.usermanagement.model.Customer;
import com.techapp.usermanagement.model.Item;
import com.techapp.usermanagement.model.Userrole;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	
	 public static SessionFactory getSessionFactory() {
		 
		
		  if (sessionFactory == null) {
		   try {
		    Configuration configuration = new Configuration();

		    // Hibernate settings equivalent to hibernate.cfg.xml's properties
		    Properties settings = new Properties();
		    settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/techapp?useSSL=false");
		    //settings.put(Environment.URL, "jdbc:mysql://localhost:3306/techapps_techapps?useSSL=false");
		    settings.put(Environment.USER, "venkat");
		    settings.put(Environment.PASS, "pass");
		    /*
		     settings.put(Environment.USER, "techapps_techapp");
		    settings.put(Environment.PASS, "TRG+og2^2BSP");
		     */
		    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

		    settings.put(Environment.SHOW_SQL, "true");

		    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

		    settings.put(Environment.HBM2DDL_AUTO, "update");
		    settings.put(Environment.AUTOCOMMIT, true);
		    
		    configuration.setProperties(settings);
		    configuration.addAnnotatedClass(Userrole.class);
		    configuration.addAnnotatedClass(Access.class);
		    configuration.addAnnotatedClass(Customer.class);
		    configuration.addAnnotatedClass(Item.class);
		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		      .applySettings(configuration.getProperties()).build();
		   
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    return sessionFactory;

		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  return sessionFactory;
		 }
}
