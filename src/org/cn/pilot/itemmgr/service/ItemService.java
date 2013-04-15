package org.cn.pilot.itemmgr.service;

import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.utils.PageModel;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:02 PM ] -->
 */
public interface ItemService {
	/**
	 * 添加物料
	 * 
	 * @param item
	 */
	public void addItem(Item item);

	/**
	 * 分页查询
	 * 
	 * @param queryString
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
	 * 删除物料
	 * 
	 * @param itemNos
	 */
	public void delItem(String[] itemNos);

	/**
	 * 根据物料代码查询
	 * 
	 * @param itemNo
	 * @return
	 */
	public Item findItemById(String itemNo);

	/**
	 * 保存上传文件(在数据库中保存文件名)
	 * 
	 * @param itemNo
	 * @param uploadFileName
	 */
	public void modifyUploadFileNameField(String itemNo, String uploadFileName);

}
