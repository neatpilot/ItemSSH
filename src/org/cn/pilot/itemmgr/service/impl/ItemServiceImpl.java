package org.cn.pilot.itemmgr.service.impl;

import org.cn.pilot.itemmgr.dao.ItemDAO;
import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.service.ItemService;
import org.cn.pilot.itemmgr.utils.AppException;
import org.cn.pilot.itemmgr.utils.BeanFactory;
import org.cn.pilot.itemmgr.utils.PageModel;
import org.cn.pilot.itemmgr.web.filter.HibernateSessionFilter;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:37 PM ] -->
 */

public class ItemServiceImpl implements ItemService {
	@Override
	public void addItem(Item item) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			getItemDAO().addItem(session, item);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (null != transaction) {
				transaction.rollback();
			}
			// 因为没有在DAO中截取异常，所以要在Service层中截取异常
			throw new AppException("addItem 失败");
		}
	}

	@Override
	public PageModel<Item> findAllItem(String queryString, int pageNo, int pageSize) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			PageModel<Item> allItem = getItemDAO().findAllItem(session, queryString, pageNo, pageSize);
			transaction.commit();
			return allItem;
		} catch (Exception e) {
			e.printStackTrace();
			if (null != transaction) {
				transaction.rollback();
			}
			throw new AppException("findAllItem 失败");
		}
	}

	@Override
	public void modifyItem(Item item) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			getItemDAO().modifyItem(session, item);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (null != transaction) {
				transaction.rollback();
			}
			throw new AppException("modifyItem 失败");
		}
	}

	@Override
	public void delItem(String[] itemNos) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			getItemDAO().delItem(session, itemNos);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (null != transaction) {
				transaction.rollback();
			}
			throw new AppException("delItem 失败");
		}
	}

	@Override
	public Item findItemById(String itemNo) {
		Session session = null;
		Transaction transaction = null;
		try {
			Item item = null;
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			item = getItemDAO().findItemById(session, itemNo);
			transaction.commit();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			if (null != transaction) {
				transaction.rollback();
			}
			throw new AppException("findItemById 失败");
		}
	}

	/**
	 * BeanFactory中保存了，实质是直接取的
	 * 
	 * @return
	 */
	private ItemDAO getItemDAO() {
		ItemDAO itemDao = (ItemDAO) BeanFactory.getInstance().getBean(ItemDAO.class);
		return itemDao;
	}

	@Override
	public void modifyUploadFileNameField(String itemNo, String uploadFileName) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFilter.getSession();
			transaction = session.beginTransaction();
			getItemDAO().modifyUploadFileNameField(session, itemNo, uploadFileName);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (null != transaction) {
				transaction.rollback();
			}
			throw new AppException("modifyUpLoadFileNameField 失败");
		}
	}
}
