package org.cn.pilot.itemmgr.service;

import java.util.List;

import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:19 PM ] -->
 */
public interface DataDictService {
	/**
	 * 取得物料类别代码列表
	 * 
	 * @return
	 */
	public List<ItemCategory> getItemCategoryList();

	/**
	 * 取得物单位列表
	 * 
	 * @return
	 */
	public List<ItemUnit> getItemUnitList();
}
