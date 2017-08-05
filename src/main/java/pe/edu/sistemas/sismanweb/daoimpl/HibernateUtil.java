package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			 Configuration configuration = new Configuration();
			 configuration.configure("hibernate.cfg.xml");
			 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()). build();
			 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			 //sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (HibernateException he) {
			System.err.println("Ocurrio un error en la inicializacion de la SessionFactory: " + he);
			throw new ExceptionInInitializerError(he);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
