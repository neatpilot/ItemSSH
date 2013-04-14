package org.cn.pilot.itemmgr.dao.impl;

import java.util.List;

import org.cn.pilot.itemmgr.dao.ItemDAO;
import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.utils.PageModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author npinc
 * @version --- 1 ---[ Apr 9, 2013 9:39:44 PM ] -->
 */
public class ItemDAO4MySQLImpl implements ItemDAO {

	@Override
	public void addItem(Session session, Item item) {
		System.out.println("addItem");
		session.save(item);
	}

	@Override
	public Item findItemById(Session session, String itemNo) {
		System.out.println("findItemById");
		return (Item) session.load(Item.class, itemNo);
	}

	@Override
	public PageModel<Item> findAllItem(Session session, String queryString, int pageNo, int pageSize) {
		System.out.println("findAllItem");
		Query query = null;
		if (null == queryString || "".equals(queryString)) {
			// query = session.createQuery("from Item i order by i.itemNo");
			query = session.createQuery("from Item i join fetch i.itemCategory join fetch i.itemUnit order by i.itemNo");
		} else {
			// query = session.createQuery("from Item i where i.itemNo like ? i.itemName like ? order by i.itemNo")
			// .setParameter(0, queryString + "%").setParameter(1, queryString + "%");
			query = session
					.createQuery(
							"from Item i join fetch i.itemCategory join fetch i.itemUnit where i.itemNo like ? i.itemName like ? order by i.itemNo")
					.setParameter(0, queryString + "%").setParameter(1, queryString + "%");
		}
		// construct page model
		PageModel<Item> itemPageModel = new PageModel<Item>();
		// 1. collect
		@SuppressWarnings("unchecked")
		List<Item> itemList = query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
		// 2. construct
		itemPageModel.setList(itemList);
		itemPageModel.setPageNo(pageNo);
		itemPageModel.setPageSize(pageSize);
		itemPageModel.setTotalRecords(getTotalRecords(session, queryString));
		return itemPageModel;
	}

	/**
	 * 根据条件取得记录数
	 * 
	 * @param conn
	 * @param queryStr
	 * @return
	 */
	private int getTotalRecords(Session session, String queryString) {
		System.out.println("getTotalRecords");
		Query query = null;
		if (null == queryString || "".equals(queryString)) {
			query = session.createQuery("select count(*) from Item i");
		} else {
			query = session.createQuery("select count(*) from Item i where i.itemNo like ? i.itemName like ?")
					.setParameter(0, queryString + "%").setParameter(1, queryString + "%");
		}
		int result = ((Long) query.uniqueResult()).intValue();
		return result;
	}

	@Override
	public void modifyItem(Session session, Item item) {
		System.out.println("modifyItem");
		session.update(item);
	}

	@Override
	public void delItem(Session session, String[] itemNos) {
		System.out.println("delItem");
		for (String itemNo : itemNos) {
			session.delete(session.load(Item.class, itemNo));
		}
	}

	@Override
	public void modifyUploadFileNameField(Session session, String itemNo, String uploadFileName) {
		System.out.println("modifyUploadFileNameField");
		Item item = (Item) session.load(Item.class, itemNo);
		item.setUploadFileName(uploadFileName);
		// not need to specific saving process
		// session.save(item);
	}
}
