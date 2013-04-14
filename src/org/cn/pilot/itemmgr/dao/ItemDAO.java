package org.cn.pilot.itemmgr.dao;

import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.utils.PageModel;
import org.hibernate.Session;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:38:57 PM ] -->
 */
public interface ItemDAO {
	/**
	 * 添加物料
	 * 
	 * @param conn
	 * @param item
	 */
	public void addItem(Session session, Item item);

	/**
	 * 根据id查询
	 * 
	 * @param conn
	 * @param itemNo
	 * @return 如果存在返回Item对象，否则返回null
	 */
	public Item findItemById(Session session, String itemNo);

	/**
	 * 分页查询
	 * 
	 * @param conn
	 * @param queryString
	 *            查询条件（物料代码或名称）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel<Item> findAllItem(Session session, String queryString, int pageNo, int pageSize);

	/**
	 * 修改物料
	 * 
	 * @param conn
	 * @param item
	 */
	public void modifyItem(Session session, Item item);

	/**
	 * 修改物料
	 * 
	 * @param conn
	 * @param item
	 */
	public void delItem(Session session, String[] itemNos);
	
	/**
	 * 保存上传的文件名称
	 * @param conn
	 * @param itemNo
	 * @param uploadFileName
	 */
	public void modifyUploadFileNameField(Session session, String itemNo, String uploadFileName);
}
