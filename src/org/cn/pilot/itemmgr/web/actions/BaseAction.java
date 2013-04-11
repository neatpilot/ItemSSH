package org.cn.pilot.itemmgr.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 类似Filter功能，不过可以跟Action配置URL保持一致
 * 
 * @author npinc
 * @version --- ---[ Apr 9, 2013 9:43:30 PM ] -->
 */
public class BaseAction extends DispatchAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// session 检查
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("index");
		}
		return super.execute(mapping, form, request, response);
	}

}
