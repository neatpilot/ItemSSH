package org.cn.pilot.itemmgr.web.forms;

import org.apache.struts.action.ActionForm;

/**
 * 登录对应的ActionForm
 * 
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:45:06 PM ] -->
 */
public class LoginActionForm extends ActionForm {
	private String username;

	private String password;

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
}
