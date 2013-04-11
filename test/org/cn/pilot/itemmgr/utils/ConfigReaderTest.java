package org.cn.pilot.itemmgr.utils;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author npinc
 * @version --- ---[ Apr 10, 2013 12:05:14 AM ] -->
 */
public class ConfigReaderTest {

	@Test
	public void test() {
		try {
			System.out.println(ConfigReader.getInstance().getJdbcInfo());
			assertNotNull(ConfigReader.getInstance().getJdbcInfo());
		} catch (Exception e) {
			fail("cannot reach here");
		}
	}

}
