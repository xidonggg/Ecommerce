<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class=body>
<%Admin admin = (Admin)session.getAttribute("admin"); 
session.setAttribute("admin", admin);%>
<div align="center">
	<h1>管理员首页</h1>
	<div id="middle">
	<div align="center">
	<table>
		<tr><td>用户id：</td><td><s:property value="admin.id" /></td></tr>
		<tr><td>用户名：</td><td><s:property value="admin.name" /></td></tr>
		<tr><td>密码：</td><td><s:property value="admin.password" /></td></tr>
		<tr><td>邮箱：</td><td><s:property value="admin.email" /></td></tr>
		<tr><td>手机号：</td><td><s:property value="admin.phonenumber" /></td></tr>
	</table>
	</div>
	</div>
<a href="adminAction_beforeaddAdmin.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="添加管理员"></a>
<a href="adminAction_beforeupdateAdmin.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="修改个人信息"></a>
<a href="adminAction_showAllUser.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="管理用户"></a>
<a href="adminAction_showAllCommodity.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="管理商品"></a>
<a href="adminAction_showAllOrder.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="管理订单"></a>
<a href="adminAction_showAllLeaveMessage.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="管理留言"></a>
<a href="adminAction_logout.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="注销"></a>
</div>
</body>
</html>