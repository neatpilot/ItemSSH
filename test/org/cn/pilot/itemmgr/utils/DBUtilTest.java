package org.cn.pilot.itemmgr.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author npinc
 * @version --- ---[ Apr 10, 2013 12:01:30 AM ] -->
 */
public class DBUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			System.out.println(DBUtil.getConnection());
			assertNotNull(DBUtil.getConnection());
		} catch (Exception e) {
			fail("cannot reach here");
		}
	}

}
