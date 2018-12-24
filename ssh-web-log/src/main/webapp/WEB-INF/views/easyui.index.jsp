<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页</title>
<%@ include file="/commons/base.jsp"%>
</head>
<script type="text/javascript">
	$(function() {
		var obj = new Object();
		obj.title = "日志列表";
		obj.width = "700";
		obj.url = "query.action";
		obj.selectorId = "#LogDataGrid";
		obj.type = "GET";
		loadDataGrid(obj);
	});

	function formatterPath(value, row, index) {
		value = (value == null) ? "" : value;
		return "<span title="+value+">" + value + "</span>"
	}
	function formatterInfo(value, row, index) {
		return "<span title="+value+">" + value + "</span>"
	}
	function formatDate(value) {
		var date = new Date(value);//如果date为10位不需要乘1000
		var Y = date.getFullYear() + "-";
		var M = (date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-";
		var D = (date.getDate() < 10 ? "0" + (date.getDate()) : date.getDate()) + " ";
		var h = (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
		var m = (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) + ":";
		var s = (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
		return Y + M + D + h + m + s;
	}

	function openDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "报文");
		var row = $('#LogDataGrid').datagrid('getSelected');
		$('#fm').form('load', row);
	}

	//复制文本域内容
	function copyInfo() {
		$("#msgInfo").select();
		document.execCommand("Copy");
		alert("复制成功");
	}
</script>
<body>
	<table id="LogDataGrid" title="日志列表" toolbar="#toolbar">
		<thead>
			<tr>
				<th field="id" width="60">PRIMARY_ID</th>
				<th field="msgCode" width="140">消息编码</th>
				<th field="holderName" width="70">客户姓名</th>
				<th field="operateStatus" width="40">状态</th>
				<th field="operateType" width="40">类型</th>
				<th field="userId" width="80">用户ID</th>
				<th field="fromIp" width="80">请求IP</th>
				<th field="msgFileIp" width="80">消息文件IP</th>
				<th field="msgPath" width="220" formatter="formatterPath">日志路径</th>
				<th field="msgInfo" width="220" formatter="formatterInfo">日志内容</th>
				<th field="createTime" width="140" formatter="formatDate">创建时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openDialog()">打开</a>
	</div>


	<div id="dlg" class="easyui-dialog" style="width: 80%; height: 90%; padding: 10px 20px;" closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post" novalidate>
			<textarea id="msgInfo" name="msgInfo" style="width: 80%; height: 90%;"></textarea>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="copyInfo()">复制</a>
	</div>
</body>
</html>
