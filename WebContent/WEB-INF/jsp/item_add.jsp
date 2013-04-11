<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>添加物料</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/drp.css">
<script src="${pageContext.request.contextPath }/script/client_validate.js"></script>
<script type="text/javascript">
	function addUser() {
		var itemFrom = document.getElementById("itemForm");
		itemFrom.action = "${pageContext.request.contextPath }/item.do";
		itemFrom.method = "post";
		//构造hidden field
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden"); // 
		hiddenField.setAttribute("name", "command");
		hiddenField.setAttribute("value", "add");
		itemFrom.appendChild(hiddenField); // append the newly created control to the form
		itemFrom.submit();
	}
</script>
</head>

<body class="body1">
	<form id="itemForm" name="itemForm" target="_self" id="itemForm">
		<div align="center">
			<table width="95%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" height="25">
				<tr>
					<td width="522" class="p1" height="25" nowrap><img
						src="${pageContext.request.contextPath }/images/mark_arrow_03.gif" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;物料维护&gt;&gt;添加</b></td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%" height="29">
						<div align="right">
							<font color="#FF0000">*</font>物料代码:&nbsp;
						</div>
					</td>
					<td width="78%"><input name="itemNo" type="text" class="text1" id="itemNo" size="10" maxlength="10"></td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">
							<font color="#FF0000">*</font>物料名称:&nbsp;
						</div>
					</td>
					<td><input name="itemName" type="text" class="text1" id="itemName" size="20" maxlength="20"></td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">物料规格:&nbsp;</div>
					</td>
					<td><label> <input name="spec" type="text" class="text1" id="spec" size="20" maxlength="20">
					</label></td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">物料型号:&nbsp;</div>
					</td>
					<td><input name="pattern" type="text" class="text1" id="pattern" size="20" maxlength="20"></td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">
							<font color="#FF0000">*</font>类别:&nbsp;
						</div>
					</td>
					<%-- JSTL 动态添加item category --%>

					<td><select name="category" class="select1" id="category">
							<c:forEach items="${itemCategoryList }" var="item">
								<option value="${item.id }">${item.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">
							<font color="#FF0000">*</font>计量单位:&nbsp;
						</div>
					</td>
					<%-- JSTL 动态添加item unit --%>
					<td><select name="unit" class="select1" id="unit">
							<c:forEach items="${itemUnitList }" var="item">
								<option value="${item.id }">${item.name }</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<div align="center">
				<input name="btnAdd" class="button1" type="button" id="btnAdd" value="添加" onclick="addUser()">
				&nbsp;&nbsp;&nbsp;&nbsp; <input name="btnBack" class="button1" type="button" id="btnBack" value="返回"
					onClick="history.go(-1)">
			</div>
		</div>
	</form>
</body>
</html>
