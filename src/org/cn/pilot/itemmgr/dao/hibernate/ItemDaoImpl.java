package org.cn.pilot.itemmgr.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.cn.pilot.itemmgr.dao.ItemDao;
import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.utils.PageModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author npinc
 * @version --- 1 ---[ Apr 9, 2013 9:39:44 PM ] -->
 */
public class ItemDaoImpl extends HibernateDaoSupport implements ItemDao {

	@Override
	public void addItem(Item item) {
		System.out.println("addItem");
		getHibernateTemplate().save(item);
	}

	@Override
	public Item findItemById(String itemNo) {
		System.out.println("findItemById");
		return (Item) getHibernateTemplate().load(Item.class, itemNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Item> findAllItem(String queryString, int pageNo, int pageSize) {
		System.out.println("findAllItem");
		List<Item> itemList = null;
		final String queryStringFinal = queryString;
		final int pageNoFinal = pageNo;
		final int pageSizeFinal = pageSize;
		if (null == queryString || "".equals(queryString)) {
			itemList = (List<Item>) getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					return session.createQuery("from Item i join fetch i.itemCategory join fetch i.itemUnit order by i.itemNo")
							.setFirstResult((pageNoFinal - 1) * pageSizeFinal).setMaxResults(pageSizeFinal).list();
				}
			});
		} else {
			itemList = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					return session
							.createQuery(
									"from Item i join fetch i.itemCategory join fetch i.itemUnit where i.itemNo like ? i.itemName like ? order by i.itemNo")
							.setParameter(0, queryStringFinal + "%").setParameter(1, queryStringFinal + "%")
							.setFirstResult((pageNoFinal - 1) * pageSizeFinal).setMaxResults(pageSizeFinal).list();
				}
			});

		}
		// construct page model
		PageModel<Item> itemPageModel = new PageModel<Item>();
		// 1. collect
		// 2. construct
		itemPageModel.setList(itemList);
		itemPageModel.setPageNo(pageNo);
		itemPageModel.setPageSize(pageSize);
		itemPageModel.setTotalRecords(getTotalRecords(queryString));
		return itemPageModel;
	}

	/**
	 * 根据条件取得记录数
	 * 
	 * @param conn
	 * @param queryStr
	 * @return
	 */
	private int getTotalRecords(String queryString) {
		System.out.println("getTotalRecords");

		List record;
		if (null == queryString || "".equals(queryString)) {
			record = getHibernateTemplate().find("select count(*) from Item");
		} else {
			record = getHibernateTemplate().find("select count(*) from Item i where i.itemNo like ? i.itemName like ?",
					new Object[] { queryString + "%", queryString + "%" });
		}

		return ((Long) record.get(0)).intValue();
	}

	@Override
	public void modifyItem(Item item) {
		System.out.println("modifyItem");
		getHibernateTemplate().update(item);
	}

	@Override
	public void delItem(String[] itemNos) {
		System.out.println("delItem");
		for (String itemNo : itemNos) {
			getHibernateTemplate().delete(getHibernateTemplate().load(Item.class, itemNo));
		}
	}

	@Override
	public void modifyUploadFileNameField(String itemNo, String uploadFileName) {
		System.out.println("modifyUploadFileNameField");
		Item item = (Item) getHibernateTemplate().load(Item.class, itemNo);
		item.setUploadFileName(uploadFileName);
		// not need to specific saving process
		// session.save(item);
	}
}
