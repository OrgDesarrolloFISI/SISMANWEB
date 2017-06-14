package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			 /*Configuration configuration = new Configuration();
			 configuration.configure("hibernate.cfg.xml");
			 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()). build();
			 sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
			 sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (HibernateException he) {
			System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
			throw new ExceptionInInitializerError(he);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
