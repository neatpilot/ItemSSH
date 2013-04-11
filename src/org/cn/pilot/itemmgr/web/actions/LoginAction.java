package org.cn.pilot.itemmgr.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cn.pilot.itemmgr.web.forms.LoginActionForm;

/**
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:44:09 PM ] -->
 */
public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1. init
		LoginActionForm laf = (LoginActionForm) form;
		// 2. collect
		String username = laf.getUsername();
		String password = laf.getPassword();
		// 3. BL
		// TODO 简略的在写
		if ("admin".equals(username) && "admin".equals(password)) {
			request.getSession().setAttribute("user", username);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("index");
		}
	}
}
