package org.cn.pilot.itemmgr.dao;

import java.util.List;

import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;

/**
 * @author npinc
 * @version --- ---[ Apr 14, 2013 9:50:26 PM ] -->
 */
public interface DataDictDao {

	/**
	 * 获取物料类别
	 * 
	 * @return
	 */
	public List<ItemCategory> getItemCategoryList();

	/**
	 * 获取物料单位
	 * 
	 * @return
	 */
	public List<ItemUnit> getItemUnitList();
}