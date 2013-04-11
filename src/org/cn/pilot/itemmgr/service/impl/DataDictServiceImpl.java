package org.cn.pilot.itemmgr.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cn.pilot.itemmgr.domain.ItemCategory;
import org.cn.pilot.itemmgr.domain.ItemUnit;
import org.cn.pilot.itemmgr.service.DataDictService;
import org.cn.pilot.itemmgr.utils.AppException;
import org.cn.pilot.itemmgr.utils.DBUtil;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:41:55 PM ] -->
 */
public class DataDictServiceImpl implements DataDictService {
	/**
	 * 取得物料类别代码列表
	 * 
	 * @return
	 */
	public List<ItemCategory> getItemCategoryList() {
		String sql = "select id, name from t_data_dict where category='C'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemCategory ic = new ItemCategory();
				ic.setId(rs.getString("id"));
				ic.setName(rs.getString("name"));
				itemCategoryList.add(ic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("取得物料类别错误！");
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return itemCategoryList;
	}

	/**
	 * 取得物单位列表
	 * 
	 * @return
	 */
	public List<ItemUnit> getItemUnitList() {
		String sql = "select id, name from t_data_dict where category='D'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemUnit> itemUnitList = new ArrayList<ItemUnit>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemUnit iu = new ItemUnit();
				iu.setId(rs.getString("id"));
				iu.setName(rs.getString("name"));
				itemUnitList.add(iu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("取得计量单位错误！");
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return itemUnitList;
	}
}
