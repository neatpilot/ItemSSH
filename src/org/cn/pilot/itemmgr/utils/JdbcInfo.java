package org.cn.pilot.itemmgr.utils;

/**
 * JDBC连接信息
 * 
 * @author npinc
 * @version --- ---[ Apr 9, 2013 10:36:49 PM ] -->
 */
public class JdbcInfo {
	private String driverName;

	private String url;

	private String username;

	private String password;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JdbcInfo [driverName=" + driverName + ", url=" + url + ", username=" + username + ", password=" + password + "]";
	}

}
