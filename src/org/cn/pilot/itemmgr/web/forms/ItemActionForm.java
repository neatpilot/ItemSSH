package org.cn.pilot.itemmgr.web.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * Item 模块对应的ActionForm
 * 
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:45:18 PM ] -->
 */
public class ItemActionForm extends ActionForm {
	private String itemNo;

	private String itemName;

	private String spec;

	private String pattern;

	private String category;

	private String unit;

	private String uploadFileName;

	// 查询字符串
	private String queryString;

	// 页号
	private int pageNo;

	// 选中标记
	private String[] selectFlag;

	// 上传文件
	private FormFile itemFile;

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public FormFile getItemFile() {
		return itemFile;
	}

	public void setItemFile(FormFile itemFile) {
		this.itemFile = itemFile;
	}
}
