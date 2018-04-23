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
<%Admin admin = (Admin)session.getAttribute("admin"); 
session.setAttribute("admin", admin);%>

<body class=body> 
<a href="adminAction_adminHome.action"><%out.print(admin.getName()); %></a>
<div align="center">
	<h1>注册成功</h1>
	<div id="middle">
	<div align="center">
	<table>
		<tr><td>管理员id：</td><td><s:property value="admin.id" /></td></tr>
		<tr><td>管理员名：</td><td><s:property value="admin.name" /></td></tr>
		<tr><td>密码：</td><td><s:property value="admin.password" /></td></tr>
		<tr><td>邮箱：</td><td><s:property value="admin.email" /></td></tr>
		<tr><td>手机号：</td><td><s:property value="admin.phonenumber" /></td></tr>
	</table>
	</div>
	</div>
	</div>
</body>
</html>