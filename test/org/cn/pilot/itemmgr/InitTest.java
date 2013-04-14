package org.cn.pilot.itemmgr;

import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;
import org.cn.pilot.itemmgr.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author npinc
 * @version --- ---[ Apr 14, 2013 4:11:13 AM ] -->
 */
public class InitTest {
	@Test
	public void addData() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();

			ItemCategory c1 = new ItemCategory();
			c1.setId("A01");
			c1.setName("中药");
			session.save(c1);
			ItemCategory c2 = new ItemCategory();
			c2.setId("A02");
			c2.setName("西药");
			session.save(c2);

			ItemUnit u1 = new ItemUnit();
			u1.setId("B01");
			u1.setName("公斤");
			session.save(u1);

			ItemUnit u2 = new ItemUnit();
			u2.setId("B02");
			u2.setName("吨");
			session.save(u2);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			HibernateUtils.closeSession(session);
		}
	}
}
