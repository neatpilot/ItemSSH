package org.cn.pilot.itemmgr.dao.hibernate;

import java.util.List;

import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author npinc
 * @version --- ---[ Apr 14, 2013 9:51:43 PM ] -->
 */
public class DataDictDaoImpl extends HibernateDaoSupport implements org.cn.pilot.itemmgr.dao.DataDictDao {

	@Override
	public List<ItemCategory> getItemCategoryList() {
		System.out.println("getItemCategoryList");
		return getHibernateTemplate().find("from ItemCategory");
	}

	@Override
	public List<ItemUnit> getItemUnitList() {
		System.out.println("getItemUnitList");
		return getHibernateTemplate().find("from ItemUnit");
	}
}
