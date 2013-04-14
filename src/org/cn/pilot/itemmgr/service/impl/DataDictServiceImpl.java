package org.cn.pilot.itemmgr.service.impl;

import java.util.List;

import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;
import org.cn.pilot.itemmgr.service.DataDictService;
import org.cn.pilot.itemmgr.utils.AppException;
import org.cn.pilot.itemmgr.web.filter.HibernateSessionFilter;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:55 PM ] -->
 */
public class DataDictServiceImpl implements DataDictService {
	/**
	 * 取得物料类别代码列表
	 * 
	 * @return
	 */
	public List<ItemCategory> getItemCategoryList() {
		System.out.println("getItemCategoryList");
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			List<ItemCategory> itemCateoryList = session.createQuery("from ItemCategory").list();
			transaction.commit();
			return itemCateoryList;
		} catch (Exception e) {
			e.printStackTrace();
			if (null!=transaction) {
				transaction.rollback();
			}
			throw new AppException("getItemCategoryList 失败");
		}
	}

	/**
	 * 取得物单位列表
	 * 
	 * @return
	 */
	public List<ItemUnit> getItemUnitList() {
		System.out.println("getItemUnitList");
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			List<ItemUnit> itemUnitList = session.createQuery("from ItemUnit").list();
			transaction.commit();
			return itemUnitList;
		} catch (Exception e) {
			e.printStackTrace();
			if (null!=transaction) {
				transaction.rollback();
			}
			throw new AppException("getItemUnitList 失败");
		}
	}
}
