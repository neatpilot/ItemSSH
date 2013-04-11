package org.cn.pilot.itemmgr.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 读取sys-config.xml项目配置信息
 * 
 * @author npinc
 * @version --- ---[ Apr 9, 2013 10:51:05 PM ] -->
 */
public class ConfigReader {
	private static ConfigReader instance = new ConfigReader();

	public static ConfigReader getInstance() {
		return instance;
	}

	private Document doc;

	private JdbcInfo jdbcInfo;

	public ConfigReader() {
		try {
			doc = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream("sys-config.xml"));
			Element driverNameElt = (Element) doc.selectObject("/config/database-info/driver-name");
			Element urlElt = (Element) doc.selectObject("/config/database-info/url");
			Element userNameElt = (Element) doc.selectObject("/config/database-info/username");
			Element passwordElt = (Element) doc.selectObject("/config/database-info/password");

			jdbcInfo = new JdbcInfo();
			jdbcInfo.setDriverName(driverNameElt.getStringValue());
			jdbcInfo.setPassword(passwordElt.getStringValue());
			jdbcInfo.setUrl(urlElt.getStringValue());
			jdbcInfo.setUsername(userNameElt.getStringValue());

		} catch (DocumentException e) {
			e.printStackTrace();
			throw new AppException("[ERROR] ConfigReader读取失败");
		}
	}

	public JdbcInfo getJdbcInfo() {
		return jdbcInfo;
	}
}
