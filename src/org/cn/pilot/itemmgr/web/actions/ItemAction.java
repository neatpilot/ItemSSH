package org.cn.pilot.itemmgr.web.actions;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.cn.pilot.itemmgr.domain.Item;
import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;
import org.cn.pilot.itemmgr.service.DataDictService;
import org.cn.pilot.itemmgr.service.ItemService;
import org.cn.pilot.itemmgr.utils.PageModel;
import org.cn.pilot.itemmgr.web.forms.ItemActionForm;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:44:49 PM ] -->
 */
public class ItemAction extends BaseAction {
	private ItemService itemService;
	private DataDictService dataDictService;

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setDataDictService(DataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ItemActionForm iaf = (ItemActionForm) form;
		int pageNo = iaf.getPageNo();
		// 第一次查询，或者条件查询无匹配（查不到）
		// Web层validate
		if (0 == pageNo) {
			pageNo = 1;
		}
		// int pageSize = Integer.parseInt(this.getServlet().getServletContext().getInitParameter("page_size"));
		// Spring使用DelegatingActionProxy代理，this.getServlet()为null，所以改为使用request.getSession.getServletContext()
		int pageSize = Integer.parseInt(request.getSession().getServletContext().getInitParameter("page_size"));
		String queryString = iaf.getQueryString();
		PageModel<Item> pageModel = itemService.findAllItem(queryString, pageNo, pageSize);
		// 传递给JSP
		request.setAttribute("pageModel", pageModel);

		return mapping.findForward("list");
	}

	/**
	 * 转向添加页面（request-- itemCategoryList，itemUnitList）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<ItemCategory> itemCategoryList = dataDictService.getItemCategoryList();
		List<ItemUnit> itemUnitList = dataDictService.getItemUnitList();

		request.setAttribute("itemCategoryList", itemCategoryList);
		request.setAttribute("itemUnitList", itemUnitList);

		return mapping.findForward("show_add");
	}

	/**
	 * 添加（成功--> 显示页面）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ItemActionForm iaf = (ItemActionForm) form;

		Item item = new Item();
		BeanUtils.copyProperties(item, iaf);
		// ActionForm只含有原始数据
		ItemUnit itemUnit = new ItemUnit();
		itemUnit.setId(iaf.getUnit());
		item.setItemUnit(itemUnit);
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setId(iaf.getCategory());
		item.setItemCategory(itemCategory);

		itemService.addItem(item);

		return mapping.findForward("item_maint");
	}

	/**
	 * 显示修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ItemActionForm iaf = (ItemActionForm) form;
		Item item = itemService.findItemById(iaf.getItemNo());
		request.setAttribute("item", item);

		List<ItemCategory> itemCategoryList = dataDictService.getItemCategoryList();
		List<ItemUnit> itemUnitList = dataDictService.getItemUnitList();

		request.setAttribute("itemCategoryList", itemCategoryList);
		request.setAttribute("itemUnitList", itemUnitList);
		return mapping.findForward("show_modify");
	}

	/**
	 * 修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1. init
		ItemActionForm iaf = (ItemActionForm) form;
		// 2. collect
		Item item = new Item();
		BeanUtils.copyProperties(item, iaf);
		// 2---- 从网页获取的是itemCategory.id
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setId(iaf.getCategory());
		item.setItemCategory(itemCategory);
		// 2---- 从网页获取的是itemCategory.id
		ItemUnit itemUnit = new ItemUnit();
		itemUnit.setId(iaf.getUnit());
		item.setItemUnit(itemUnit);
		// 3---- BL
		itemService.modifyItem(item);
		// 4 return
		return mapping.findForward("item_maint");
	}

	/**
	 * 删除Action
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1. init
		ItemActionForm iaf = (ItemActionForm) form;
		// 2. collect
		String[] itemNos = iaf.getSelectFlag();
		// 3. BL
		itemService.delItem(itemNos);
		// 4. return forward
		return mapping.findForward("item_maint");
	}

	/**
	 * 打开详细页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1. init
		ItemActionForm iaf = (ItemActionForm) form;
		// 2. collect
		Item item = itemService.findItemById(iaf.getItemNo());
		// 3. forward attribute
		request.setAttribute("item", item);
		// 4. return forward
		return mapping.findForward("show_detail");
	}

	/**
	 * 显示上传页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1. init
		ItemActionForm iaf = (ItemActionForm) form;
		// 2. collect
		// 3. forward attribute
		Item item = itemService.findItemById(iaf.getItemNo());
		request.setAttribute("item", item);
		// 4. return forward
		return mapping.findForward("show_upload");
	}

	/**
	 * 上传Item图片
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1. init
		ItemActionForm iaf = (ItemActionForm) form;
		// 2. collect
		FormFile image = iaf.getItemFile();
		String fileName = image.getFileName();
		String itemNo = iaf.getItemNo();
		// String realPath = this.getServlet().getServletContext().getRealPath("/uimages");
		String realPath = request.getSession().getServletContext().getRealPath("/uimages");
		// 3. write & change file name into database
		// TODO 应该放到BL层去做（传byte[]）,而且保存的文件名应该是完整路径
		itemService.modifyUploadFileNameField(itemNo, fileName);
		String imagePath = realPath + "\\" + fileName;
		FileOutputStream fos = new FileOutputStream(imagePath);
		fos.write(image.getFileData());
		fos.flush();
		fos.close();
		// 4. return forward
		return mapping.findForward("item_maint");
	}
}
