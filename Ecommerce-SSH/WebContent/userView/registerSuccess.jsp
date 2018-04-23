<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<body class=body>
<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="购物城"></a>
<div align="center">
	<h1>注册成功</h1>
	<div id="middle">
	<div align="center">
	<table>
		<tr><td>用户id：</td><td><s:property value="user.id" /></td></tr>
		<tr><td>用户名：</td><td><s:property value="user.name" /></td></tr>
		<tr><td>密码：</td><td><s:property value="user.password" /></td></tr>
		<tr><td>邮箱：</td><td><s:property value="user.email" /></td></tr>
		<tr><td>手机号：</td><td><s:property value="user.phonenumber" /></td></tr>
		<tr><td>地址：</td><td><s:property value="user.address" /></td></tr>
	</table>
	</div>
	</div>
	</div>
</body>
</html>