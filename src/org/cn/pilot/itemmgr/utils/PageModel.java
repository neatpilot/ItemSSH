package org.cn.pilot.itemmgr.utils;

import java.util.List;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 10:36:53 PM ] -->
 */
public class PageModel<T> {
	// 结果集
	private List<T> list;

	// 记录数
	private int totalRecords;

	// 每页多少条数据
	private int pageSize;

	// 第几页
	private int pageNo;

	/**
	 * 返回总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		if (0 == totalRecords) {
			return 0;
		}
		return (totalRecords + pageSize - 1) / pageSize;
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	public int getTopPageNo() {
		if (0 == totalRecords) {
			return 0;
		}
		return 1;
	}

	/**
	 * 上一页
	 * 
	 * @return
	 */
	public int getPreviousPageNo() {
		if (0 == totalRecords) {
			return 0;
		}
		if (this.pageNo <= 1) {
			return 1;
		}
		return this.pageNo - 1;
	}

	/**
	 * 下一页
	 * 
	 * @return
	 */
	public int getNextPageNo() {
		if (0 == totalRecords) {
			return 0;
		}
		if (this.pageNo >= getBottomPageNo()) {
			return getBottomPageNo();
		}
		return this.pageNo + 1;
	}

	/**
	 * 尾页
	 * 
	 * @return
	 */
	public int getBottomPageNo() {
		return getTotalPages();
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
