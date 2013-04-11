<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<html>
<head>
<title>物料维护</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/drp.css">
<script src="${pageContext.request.contextPath }/script/npincutils.js"></script>
<script type="text/javascript">
	function upload() {
		var itemForm = document.getElementById("itemForm");
		appendPostParameter(itemForm, "${pageContext.request.contextPath }/item.do", "upload");
		// 添加itemNo
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden"); // 
		hiddenField.setAttribute("name", "itemNo");
		hiddenField.setAttribute("value", "${itemForm.itemNo}");
		itemForm.appendChild(hiddenField);
		// 提交
		itemForm.submit();
	}
</script>
</head>
<body class="body1">
	<%-- 上传表单 --%>
	<form name="itemForm" target="_self" id="itemForm" enctype="multipart/form-data">
		<div align="center">
			<table width="95%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap><img
						src="${pageContext.request.contextPath }/images/mark_arrow_03.gif" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;物料维护&gt;&gt;上传物料图片</b></td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="29">
						<div align="right">物料代码:&nbsp;</div>
					</td>
					<td>${item.itemNo }</td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">物料名称:&nbsp;</div>
					</td>
					<td>${item.itemName }</td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">物料规格:&nbsp;</div>
					</td>
					<td>${item.spec }</td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">物料型号:&nbsp;</div>
					</td>
					<td>${item.pattern }</td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">类别:&nbsp;</div>
					</td>
					<td>${item.itemCategory.name }</td>
				</tr>
				<tr>
					<td height="26">
						<div align="right">计量单位:&nbsp;</div>
					</td>
					<td>${item.itemUnit.name }</td>
				</tr>
				<tr>
					<td height="74">
						<div align="right">图片:&nbsp;</div>
					</td>
					<td><img src="${pageContext.request.contextPath }/uimages/${item.uploadFileName}" width="85" height="49"></td>
				</tr>
				<tr>
					<td width="22%" height="29">
						<div align="right">
							<font color="#FF0000">*</font>选择图片:&nbsp;
						</div>
					</td>
					<td width="78%"><input name=itemFile type="file" class="text1" size="40" maxlength="40"></td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<div align="center">
				<input name="btn_upload" class="button1" type="button" id="btn_upload" value="上传" onclick="upload()">
				&nbsp;&nbsp;&nbsp;&nbsp; <input name="btnBack" class="button1" type="button" id="btnBack" value="返回"
					onClick="history.go(-1)">
			</div>
		</div>
	</form>
</body>
</html>

