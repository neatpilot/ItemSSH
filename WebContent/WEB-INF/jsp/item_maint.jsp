<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>����ά��</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/drp.css">
<script src="${pageContext.request.contextPath }/script/windows.js"></script>
<script src="${pageContext.request.contextPath }/script/npincutils.js"></script>
<script type="text/javascript">
	function addItem() {
		window.self.location = "${pageContext.request.contextPath}/item.do?command=showAdd";
	}

	function modifyItem() {
		// �ҵ�����<input type="checkbox">
		var selectFlags = document.getElementsByName("selectFlag");
		// ֻ����һ����ѡ��
		var checkedCount = 0;
		var checkedIndex = -1; // ��¼ѡ�� 
		for ( var i in selectFlags) {
			if (selectFlags[i].checked) {
				checkedCount++;
				checkedIndex = i;
			}
		}
		if (0 == checkedCount) {
			alert("��ѡ��һ��");
			return; // �ǵ�return
		}
		if (checkedCount > 1) {
			alert("ֻ��ѡ��һ��");
			return;
		}

		var itemForm = document.getElementById("itemForm");

		// selectFlags[checkedIndex].value
		appendPostParameter(itemForm, "${pageContext.request.contextPath}/item.do?itemNo="
				+ selectFlags[checkedIndex].value, "showModify");
		itemForm.submit();
	}

	function deleteItem() {
		// 1. �ҵ�����<input type="checkbox"/>
		var selectedFlags = document.getElementsByName("selectFlag");
		// 2. �鿴�Ƿ��б�ѡ�е�
		var flag = false;
		for ( var i in selectedFlags) {
			if (selectedFlags[i].checked) {
				flag = true;
				break;
			}
		}
		// 3.1û��ѡ��
		if (!flag) {
			alert("����ѡ��");
			return;
		}
		// 3.2 ��ѡ��
		if (window.confirm("ȷ��ɾ��")) {
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
		window.open(path, '������ϸ��Ϣ', 'width=400, height=400, scrollbars=no');
		return false;
	}

	function uploadPic4Item() {
		// �ҵ�����<input type="checkbox">
		var selectFlags = document.getElementsByName("selectFlag");
		// ֻ����һ����ѡ��
		var checkedCount = 0;
		var checkedIndex = -1; // ��¼ѡ�� 
		for ( var i in selectFlags) {
			if (selectFlags[i].checked) {
				checkedCount++;
				checkedIndex = i;
			}
		}
		if (0 == checkedCount) {
			alert("��ѡ��һ��");
			return; // �ǵ�return
		}
		if (checkedCount > 1) {
			alert("ֻ��ѡ��һ��");
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
						src="${pageContext.request.contextPath }/images/mark_arrow_02.gif" alt="��" width="14" height="14"> &nbsp; <b>�������ݹ���&gt;&gt;����ά��</b></td>
				</tr>
			</table>
			<hr width="97%" align="center" size=0>
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="17%" height="29">
						<div align="left">���ϴ���/����:</div>
					</td>
					<td width="57%"><input name="queryString" type="text" class="text1" id="clientId4" size="50" maxlength="50"
						value="${itemForm.queryString }"></td>
					<td width="26%">
						<div align="left">
							<input name="btnQuery" type="button" class="button1" id="btnQuery" value="��ѯ" onclick="searchItemByQuery()">
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
				<td nowrap height="10" class="p2">������Ϣ</td>
				<td nowrap height="10" class="p3">&nbsp;</td>
			</tr>
		</table>
		<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table1">
			<tr>
				<td width="35" class="rd6"><input type="checkbox" id="ifAll" name="ifAll" onClick="checkAll()"></td>
				<td width="155" class="rd6">���ϴ���</td>
				<td width="155" class="rd6">��������</td>
				<td width="155" class="rd6">���Ϲ��</td>
				<td width="155" class="rd6">�����ͺ�</td>
				<td width="138" class="rd6">���</td>
				<td width="101" class="rd6">������λ</td>
			</tr>

			<!-- JSTL ��̬��� -->
			<c:choose>
				<c:when test="${empty pageModel or empty pageModel.list or fn:length(pageModel.list) eq 0}">
					<tr>
						<td colspan="7" class="rd8">û����Ϣ</td>
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
						<font color="#FFFFFF">&nbsp;��${pageModel.totalPages}ҳ</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FFFFFF">��ǰ��</font>&nbsp
						<font color="#FF0000">${pageModel.pageNo }</font>&nbsp <font color="#FFFFFF">ҳ</font>
					</div>
				</td>
				<td nowrap class="rd19" width="64%">
					<div align="right">
						<input name="btnTopPage" class="button1" type="button" id="btnTopPage" value="|&lt;&lt; " title="��ҳ"
							onClick="topPage()"> <input name="btnPreviousPage" class="button1" type="button" id="btnPreviousPage"
							value=" &lt;  " title="��ҳ" onClick="previousPage()"> <input name="btnNextPage" class="button1"
							type="button" id="btnNextPage" value="  &gt; " title="��ҳ" onClick="nextPage()"> <input
							name="btnBottomPage" class="button1" type="button" id="btnBottomPage" value=" &gt;&gt;|" title="βҳ"
							onClick="bottomPage()"> <input name="btnAdd" type="button" class="button1" id="btnAdd" value="���"
							onClick="addItem()"> <input name="btnDelete" class="button1" type="button" id="btnDelete" value="ɾ��"
							onClick="deleteItem()"> <input name="btnModify" class="button1" type="button" id="btnModify" value="�޸�"
							onClick="modifyItem()"> <input name="btnUpload" class="button1" type="button" id="btnUpload" value="�ϴ�ͼƬ"
							onClick="uploadPic4Item()">
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
