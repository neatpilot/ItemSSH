package org.cn.pilot.itemmgr.utils;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 模拟Spring，从配置文件中找到接口的实例
 * 
 * @author npinc
 * @version --- ---[ Apr 9, 2013 10:36:09 PM ] -->
 */
public class BeanFactory {
	private static BeanFactory instance = new BeanFactory();

	public static BeanFactory getInstance() {
		return instance;
	}

	private Document document;

	public BeanFactory() {
		try {
			document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream("applicationContext.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new AppException("[ERROR] BeanFactory读取applicationContext.xml失败");
		}
	}

	private Map<String, Object> beansMap = new HashMap<String, Object>();

	/**
	 * 通过接口Class对象到对应的实现
	 * 
	 * @param c
	 * @return
	 */
	public Object getBean(Class c) {
		String qualifiedInterfaceName = c.getName();
		synchronized (beansMap) {
			// 内存存在？
			if (beansMap.containsKey(qualifiedInterfaceName)) {
				return beansMap.get(qualifiedInterfaceName);
			}
			// 内存中不存在
			Object object = null;
			// 1. 初始化
			try {
				Element elt = (Element) document.selectObject("//bean[@id=\"" + qualifiedInterfaceName + "\"]");
				object = Class.forName(elt.attributeValue("class")).newInstance();
				// 2. 放入内存
				beansMap.put(qualifiedInterfaceName, object);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new AppException("[ERROR] BeanFactory读取class失败");
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new AppException("[ERROR] BeanFactory读取class失败");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new AppException("[ERROR] BeanFactory读取class失败");
			}
			return object;
		}
	}

}
