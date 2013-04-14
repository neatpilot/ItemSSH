package org.cn.pilot.itemmgr.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Open Session In View
 * 
 * @author npinc
 * @version --- ---[ Apr 14, 2013 2:29:03 AM ] -->
 */
public class HibernateSessionFilter implements Filter {
	// 线程存储session
	private static ThreadLocal<Session> hibernateSessionLocalHolder = new ThreadLocal<Session>();
	// init就读取SessionFactory
	private static SessionFactory sessionFactory;

	/**
	 * 获取Hibernate.Session
	 * 
	 * @return
	 */
	public static Session getSession() {
		Session session = hibernateSessionLocalHolder.get();
		if (null == session) {
			// ThreadLocal 中没有
			// 1. init()
			session = sessionFactory.openSession();
			// 2. set the initialized session into ThreadLocal
			hibernateSessionLocalHolder.set(session);
		}
		return session;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO log4j
		System.out.println("[loging] ----> HibernateSessionFilter init()");
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			// 每次new一个session，放入ThreadLocal
			getSession(); // 其实有点多余，在Service层也都会显示的getSession
			chain.doFilter(request, response);
		} finally {
			// 1. 关闭session
			// 2. 从ThreadLocal中移除
			Session session = hibernateSessionLocalHolder.get();
			if (null != session) {
				if (session.isOpen()) {
					session.close();
				}
			}
			hibernateSessionLocalHolder.remove();
		}
	}

	@Override
	public void destroy() {
		// TODO
	}

	public static ThreadLocal<Session> getThreadLocal() {
		return hibernateSessionLocalHolder;
	}

}
