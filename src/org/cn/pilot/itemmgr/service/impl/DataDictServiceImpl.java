package org.cn.pilot.itemmgr.service.impl;

import java.util.List;

import org.cn.pilot.itemmgr.dao.DataDictDao;
import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;
import org.cn.pilot.itemmgr.service.DataDictService;
import org.cn.pilot.itemmgr.utils.AppException;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:55 PM ] -->
 */
public class DataDictServiceImpl implements DataDictService {
	private DataDictDao dataDictDao;

	public void setDataDictDao(DataDictDao dataDictDao) {
		this.dataDictDao = dataDictDao;
	}

	/**
	 * 取得物料类别代码列表
	 * 
	 * @return
	 */
	public List<ItemCategory> getItemCategoryList() {
		try {
			return dataDictDao.getItemCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("getItemCategoryList 失败");
		}
	}

	/**
	 * 取得物单位列表
	 * 
	 * @return
	 */
	public List<ItemUnit> getItemUnitList() {
		try {
			return dataDictDao.getItemUnitList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("getItemUnitList 失败");
		}
	}
}
