package org.cn.pilot.itemmgr.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * database utils
 * 
 * @author npinc
 * @version --- ---[ Apr 9, 2013 10:36:37 PM ] -->
 */
public class DBUtil {
	/**
	 * 取得数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			JdbcInfo jdbcInfo = ConfigReader.getInstance().getJdbcInfo();
			Class.forName(jdbcInfo.getDriverName());
			conn = DriverManager.getConnection(jdbcInfo.getUrl(), jdbcInfo.getUsername(), jdbcInfo.getPassword());
		} catch (ClassNotFoundException e) {
			// 记录日志可以将类不能找记录进去，这样可以更准确的定位问题
			// 但是给用户不应该抛出类不能找到，应该抛出用户能够理解的错误
			e.printStackTrace();
			throw new AppException("系统出现故障，请联系系统管理员！");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("系统出现故障，请联系系统管理员！");
		}
		return conn;
	}

	public static void close(PreparedStatement pstmt) {
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void commit(Connection conn) {
		if (null != conn) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void rollback(Connection conn) {
		if (null!=conn) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setAutoCommit(Connection conn, boolean autoCommit) {
		if (null!=conn) {
			try {
				conn.setAutoCommit(autoCommit);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
