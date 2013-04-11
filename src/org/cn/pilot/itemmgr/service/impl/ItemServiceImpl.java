package org.cn.pilot.itemmgr.service.impl;

import java.sql.Connection;

import org.cn.pilot.itemmgr.dao.ItemDAO;
import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.service.ItemService;
import org.cn.pilot.itemmgr.utils.AppException;
import org.cn.pilot.itemmgr.utils.BeanFactory;
import org.cn.pilot.itemmgr.utils.DBUtil;
import org.cn.pilot.itemmgr.utils.PageModel;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:37 PM ] -->
 */
public class ItemServiceImpl implements ItemService {
	public void addItem(Item item) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if (getItemDAO().findItemById(conn, item.getItemNo()) != null) {
				throw new AppException("物料代码重复，代码=[" + item.getItemNo() + "]！");
			}
			getItemDAO().addItem(conn, item);
		} catch (AppException e) {
			throw e;
		} finally {
			DBUtil.close(conn);
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageModel findAllItem(String queryString, int pageNo, int pageSize) {
		Connection conn = null;
		PageModel pageModel = null;
		try {
			conn = DBUtil.getConnection();
			pageModel = getItemDAO().findAllItem(conn, queryString, pageNo, pageSize);
		} finally {
			DBUtil.close(conn);
		}
		return pageModel;
	}

	/**
	 * 修改物料
	 * 
	 * @param item
	 */
	public void modifyItem(Item item) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			getItemDAO().modifyItem(conn, item);
		} finally {
			DBUtil.close(conn);
		}
	}

	/**
	 * 删除物料
	 * 
	 * @param item
	 */
	public void delItem(String[] itemNos) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			getItemDAO().delItem(conn, itemNos);
		} finally {
			DBUtil.close(conn);
		}
	}

	/**
	 * 根据物料代码查询
	 * 
	 * @param itemNo
	 * @return
	 */
	public Item findItemById(String itemNo) {
		Connection conn = null;
		Item item = null;
		try {
			conn = DBUtil.getConnection();
			item = getItemDAO().findItemById(conn, itemNo);
		} finally {
			DBUtil.close(conn);
		}
		return item;
	}

	private ItemDAO getItemDAO() {
		ItemDAO itemDao = (ItemDAO) BeanFactory.getInstance().getBean(ItemDAO.class);
		return itemDao;
	}

	@Override
	public void modifyUploadFileNameField(String itemNo, String uploadFileName) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			getItemDAO().modifyUploadFileNameField(conn, itemNo, uploadFileName);
		} finally {
			DBUtil.close(conn);
		}
	}
}
