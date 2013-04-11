<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>物料维护</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/drp.css">
<script src="${pageContext.request.contextPath }/script/windows.js"></script>
<script src="${pageContext.request.contextPath }/script/npincutils.js"></script>
<script type="text/javascript">
	function addItem() {
		window.self.location = "${pageContext.request.contextPath}/item.do?command=showAdd";
	}

	function modifyItem() {
		// 找到所有<input type="checkbox">
		var selectFlags = document.getElementsByName("selectFlag");
		// 只能有一个被选中
		var checkedCount = 0;
		var checkedIndex = -1; // 记录选中 
		for ( var i in selectFlags) {
			if (selectFlags[i].checked) {
				checkedCount++;
				checkedIndex = i;
			}
		}
		if (0 == checkedCount) {
			alert("请选择一个");
			return; // 记得return
		}
		if (checkedCount > 1) {
			alert("只能选择一个");
			return;
		}

		var itemForm = document.getElementById("itemForm");

		// selectFlags[checkedIndex].value
		appendPostParameter(itemForm, "${pageContext.request.contextPath}/item.do?itemNo="
				+ selectFlags[checkedIndex].value, "showModify");
		itemForm.submit();
	}

	function deleteItem() {
		// 1. 找到所有<input type="checkbox"/>
		var selectedFlags = document.getElementsByName("selectFlag");
		// 2. 查看是否有被选中的
		var flag = false;
		for ( var i in selectedFlags) {
			if (selectedFlags[i].checked) {
				flag = true;
				break;
			}
		}
		// 3.1没有选择
		if (!flag) {
			alert("请先选择");
			return;
		}
		// 3.2 有选择
		if (window.confirm("确定删除")) {
			var itemForm = document.getElementById("itemForm");
			appendPostParameter(itemForm, "${pageContext.request.contextPath}/item.do", "del");
			itemForm.submit()
		} else {
			return;
		}
	}

	function searchItemByQuery() {
		document.getElementById("itemForm").action = "${pageContext.request.contextPath }/item.do";
		document.getElementById("itemForm").method = "post";
		document.getElementById("itemForm").submit();
	}

	function topPage() {
		window.self.location = "${pageContext.request.contextPath }/item.do?pageNo=${pageModel.topPageNo}&queryString=${itemForm.queryString}";
	}

	function previousPage() {
		window.self.location = "${pageContext.request.contextPath }/item.do?pageNo=${pageModel.previousPageNo}&queryString=${itemForm.queryString}";
	}

	function nextPage() {
		window.self.location = "${pageContext.request.contextPath }/item.do?pageNo=${pageModel.nextPageNo}&queryString=${itemForm.queryString}";
	}

	function bottomPage() {
		window.self.location = "${pageContext.request.contextPath }/item.do?pageNo=${pageModel.bottomPageNo}&queryString=${itemForm.queryString}";
	}

	function checkAll() {
		var status = document.getElementById("ifAll").checked;
		var selectFlags = document.getElementsByName("selectFlag");
		for ( var i in selectFlags) {
			selectFlags[i].checked = status;
		}
	}

	function openDetailPage(itemNo) {
		var path = "${pageContext.request.contextPath}/item.do?command=showDetail&itemNo=" + itemNo;
		alert(path);
		window.open(path, '物料详细信息', 'width=400, height=400, scrollbars=no');
		return false;
	}

	function uploadPic4Item() {
		// 找到所有<input type="checkbox">
		var selectFlags = document.getElementsByName("selectFlag");
		// 只能有一个被选中
		var checkedCount = 0;
		var checkedIndex = -1; // 记录选中 
		for ( var i in selectFlags) {
			if (selectFlags[i].checked) {
				checkedCount++;
				checkedIndex = i;
			}
		}
		if (0 == checkedCount) {
			alert("请选择一个");
			return; // 记得return
		}
		if (checkedCount > 1) {
			alert("只能选择一个");
			return;
		}

		var itemForm = document.getElementById("itemForm");

		// selectFlags[checkedIndex].value
		appendPostParameter(itemForm, "${pageContext.request.contextPath}/item.do?itemNo="
				+ selectFlags[checkedIndex].value, "showUpload");
		itemForm.submit();
	}
</script>
</head>

<body class="body1">
	<form id="itemForm" name="itemForm">
		<div align="center">
			<table width="95%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap><img
						src="${pageContext.request.contextPath }/images/mark_arrow_02.gif" alt="我" width="14" height="14"> &nbsp; <b>基础数据管理&gt;&gt;物料维护</b></td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="17%" height="29">
						<div align="left">物料代码/名称:</div>
					</td>
					<td width="57%"><input name="queryString" type="text" class="text1" id="clientId4" size="50" maxlength="50"
						value="${itemForm.queryString }"></td>
					<td width="26%">
						<div align="left">
							<input name="btnQuery" type="button" class="button1" id="btnQuery" value="查询" onclick="searchItemByQuery()">
						</div>
					</td>
				</tr>
				<tr>
					<td height="16">
						<div align="right"></div>
					</td>
					<td>&nbsp;</td>
					<td>
						<div align="right"></div>
					</td>
				</tr>
			</table>

		</div>
		<table width="95%" border="0" cellspacing="0" cellpadding="0" class="rd1" align="center">
			<tr>
				<td nowrap height="10" class="p2">物料信息</td>
				<td nowrap height="10" class="p3">&nbsp;</td>
			</tr>
		</table>
		<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
			<tr>
				<td width="35" class="rd6"><input type="checkbox" id="ifAll" name="ifAll" onClick="checkAll()"></td>
				<td width="155" class="rd6">物料代码</td>
				<td width="155" class="rd6">物料名称</td>
				<td width="155" class="rd6">物料规格</td>
				<td width="155" class="rd6">物料型号</td>
				<td width="138" class="rd6">类别</td>
				<td width="101" class="rd6">计量单位</td>
			</tr>

			<!-- JSTL 动态添加 -->
			<c:choose>
				<c:when test="${empty pageModel or empty pageModel.list or fn:length(pageModel.list) eq 0}">
					<tr>
						<td colspan="7" class="rd8">没有信息</td>
					</tr>

				</c:when>
				<c:otherwise>
					<c:forEach items="${pageModel.list }" var="list">
						<tr>
							<td class="rd8"><input type="checkbox" name="selectFlag" class="checkbox1" value="${list.itemNo }"></td>
							<!--  <td class="rd8"><a href="#" onClick="openDetailPage(${list.itemNo })" >${list.itemNo }</a></td> -->
							<td class="rd8"><a
								href="${pageContext.request.contextPath}/item.do?command=showDetail&itemNo=${list.itemNo }" target="_blank">${list.itemNo
									}</a></td>
							<td class="rd8">${list.itemName }</td>
							<td class="rd8">${list.spec }</td>
							<td class="rd8">${list.pattern }</td>
							<td class="rd8">${list.itemCategory.name }</td>
							<td class="rd8">${list.itemUnit.name }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>


		</table>
		<table width="95%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" class="rd1">
			<tr>
				<td nowrap class="rd19" height="2" width="36%">
					<div align="left">
						<font color="#FFFFFF">&nbsp;共${pageModel.totalPages}页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">当前第</font>&nbsp
						<font color="#FF0000">${pageModel.pageNo }</font>&nbsp <font color="#FFFFFF">页</font>
					</div>
				</td>
				<td nowrap class="rd19" width="64%">
					<div align="right">
						<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="首页"
							onClick="topPage()"> <input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage"
							value=" &lt;  " title="上页" onClick="previousPage()"> <input name="btnNextPage" class="button1"
							type="button" id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()"> <input
							name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="尾页"
							onClick="bottomPage()"> <input name="btnAdd" type="button" class="button1" id="btnAdd" value="添加"
							onClick="addItem()"> <input name="btnDelete" class="button1" type="button" id="btnDelete" value="删除"
							onClick="deleteItem()"> <input name="btnModify" class="button1" type="button" id="btnModify" value="修改"
							onClick="modifyItem()"> <input name="btnUpload" class="button1" type="button" id="btnUpload" value="上传图片"
							onClick="uploadPic4Item()">
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
