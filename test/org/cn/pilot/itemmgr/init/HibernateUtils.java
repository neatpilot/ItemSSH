package org.cn.pilot.itemmgr.init;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 在test中初始化数据
 * @author npinc
 * @version --- ---[ Apr 15, 2013 3:22:08 AM ] -->
 */
public class HibernateUtils {

	private static SessionFactory factory;

	static {
		try {
			Configuration cfg = new Configuration().configure();

			factory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Session getSession() {
		return factory.openSession();
	}

	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
}
