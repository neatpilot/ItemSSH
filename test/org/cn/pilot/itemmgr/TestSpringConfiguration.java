package org.cn.pilot.itemmgr;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author npinc
 * @version --- ---[ Apr 15, 2013 2:04:32 AM ] -->
 */
public class TestSpringConfiguration {
	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");
		ac.getBean("sessionFactory");
		ac.getBean("itemService");
	}
}
