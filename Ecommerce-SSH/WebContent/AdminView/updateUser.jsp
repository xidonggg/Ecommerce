<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "com.po.*" %>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户信息页面</title>
</head>
<%Admin admin = (Admin)session.getAttribute("admin"); 
session.setAttribute("admin", admin);%>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);%>
<body class=body>
<a href="adminAction_adminHome.action"><%out.print(admin.getName()); %></a>
<div align="center">
<h4 align="center">修改用户信息</h4>
<div id="middle">
<div align="center">
	<s:form action="adminAction_updateUser.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">	
			<s:textfield name="user.name" label="用户名" value="%{user.name}"/>
			<s:textfield name="user.password" label="密码" value="%{user.password}"/>
			<s:textfield name="repwd" label="确认密码" value="%{user.password}"/>
			<s:textfield name="user.phonenumber" label="手机号码" value="%{user.phonenumber}"/>
			<s:textfield name="user.address" label="地址" value="%{user.address}"/>
			<s:textfield name="user.email" label="电子邮箱" value="%{user.email}"/>

			<s:submit value="确定" align="center"/>
		</table>
	</s:form>
</div>
</div>
<a href="adminAction_showAllUser.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回"></a>
</div>
</body>
</html>