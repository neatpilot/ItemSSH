package org.cn.pilot.itemmgr.service.impl;

import org.cn.pilot.itemmgr.dao.ItemDao;
import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.service.ItemService;
import org.cn.pilot.itemmgr.utils.AppException;
import org.cn.pilot.itemmgr.utils.PageModel;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:37 PM ] -->
 */

public class ItemServiceImpl implements ItemService {
	private ItemDao itemDao;

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public void addItem(Item item) {
		try {
			itemDao.addItem(item);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("addItem 失败");
		}
	}

	@Override
	public PageModel<Item> findAllItem(String queryString, int pageNo, int pageSize) {
		try {
			return itemDao.findAllItem(queryString, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("findAllItem 失败");
		}
	}

	@Override
	public void modifyItem(Item item) {
		try {
			itemDao.modifyItem(item);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("modifyItem 失败");
		}
	}

	@Override
	public void delItem(String[] itemNos) {
		try {
			itemDao.delItem(itemNos);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("delItem 失败");
		}
	}

	@Override
	public Item findItemById(String itemNo) {
		try {
			return itemDao.findItemById(itemNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("findItemById 失败");
		}
	}

	@Override
	public void modifyUploadFileNameField(String itemNo, String uploadFileName) {
		try {
			itemDao.modifyUploadFileNameField(itemNo, uploadFileName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("modifyUpLoadFileNameField 失败");
		}
	}
}
