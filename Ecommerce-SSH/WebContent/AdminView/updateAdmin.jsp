<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "com.po.*" %>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改信息</title>
</head>

<body class=body>
<%Admin admin = (Admin)session.getAttribute("admin"); 
session.setAttribute("admin", admin);%>

<a href="adminAction_adminHome.action"><%out.print(admin.getName()); %></a>
<a href="adminAction_adminHome.action?admin.id=${admin.id }"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回"></a>
<div align="center">
<h4 align="center">修改信息</h4>
<div id="middle">
<div align="center">
	<s:form action="adminAction_updateAdmin.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">
			<tr><td></td><td>${msg}</td></tr>	
			<s:hidden name="msg" value="update"/>
			<s:textfield name="admin.name" label="用户名" value="%{admin.name}"/>
			<s:textfield name="admin.password" label="密码" value="%{admin.password}"/>
			<s:textfield name="admin.phonenumber" label="手机号码" value="%{admin.phonenumber}"/>
			<s:textfield name="admin.email" label="电子邮箱" value="%{admin.email}"/>
			<s:submit value="确定" align="center"/>
		</table>
	</s:form>
</div>
</div>
</div>
</body>
</html>