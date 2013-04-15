package org.cn.pilot.itemmgr.dao;

import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.utils.PageModel;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:38:57 PM ] -->
 */
public interface ItemDao {
	/**
	 * 添加物料
	 * 
	 * @param item
	 */
	public void addItem(Item item);

	/**
	 * 根据id查询
	 * 
	 * @param itemNo
	 * @return 如果存在返回Item对象，否则返回null
	 */
	public Item findItemById(String itemNo);

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询条件（物料代码或名称）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<Item> findAllItem(String queryString, int pageNo, int pageSize);

	/**
	 * 修改物料
	 * 
	 * @param item
	 */
	public void modifyItem(Item item);

	/**
	 * 修改物料
	 * 
	 * @param item
	 */
	public void delItem(String[] itemNos);

	/**
	 * 保存上传的文件名称
	 * 
	 * @param itemNo
	 * @param uploadFileName
	 */
	public void modifyUploadFileNameField(String itemNo, String uploadFileName);
}
