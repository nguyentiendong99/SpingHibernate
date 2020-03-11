package edu.fa;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.loader.custom.Return;
import org.hibernate.service.ServiceRegistry;

public class ConnectionUtil {
	public static SessionFactory sessionFactory = null;
	public static SessionFactory getSessionFactory() {
		if (sessionFactory==null) {
		Configuration cofig = new Configuration();
		cofig.configure();
		ServiceRegistry registry =
				new StandardServiceRegistryBuilder().applySettings(cofig.getProperties()).build();
		sessionFactory = cofig.buildSessionFactory(registry);
		}
		return sessionFactory;
	}
}
