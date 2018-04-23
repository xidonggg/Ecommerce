<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>show</title>
</head>
<%Admin admin = (Admin)session.getAttribute("admin"); 
session.setAttribute("admin", admin);%>

<body class=body>

<a href="adminAction_adminHome.action"><%out.print(admin.getName()); %></a>
<div align="center">
	<h1>所有用户</h1>
	<div id="middle" >
	<div align="center">
	<table align="center">
		<tr><th>用户id</th><th>姓名</th><th>注册日期</th><th>电话</th><th>住址</th><th>邮箱</th></tr>
		<s:iterator value="userlist">
	
		<tr><td><s:property value="id"/></td>
		<td><s:property value="name"/></td>
		<td><s:property value="birthday"/></td>
		<td><s:property value="phonenumber"/></td>
		<td><s:property value="address"/></td>
		<td><s:property value="email"/></td>
		<td><a href="adminAction_beforeupdateUser.action?user.id=<s:property value="id"/>"><INPUT TYPE="BUTTON" NAME="button1" VALUE="修改"></a></td>
		<td><a href="adminAction_deleteUser.action?user.id=<s:property value="id"/>"><INPUT TYPE="BUTTON" NAME="button1" VALUE="删除" onclick="javascript:alert('确认删除该用户？')"></a></td>
		</tr>
		</s:iterator>
	</table>
	</div>
	</div>
	<s:form action="adminAction_findUsers.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">
		<!--<s:textfield name="user.id" label="用户id"/>  -->
			
			<s:textfield name="user.name" label="用户名" value=""/>
			<s:textfield name="user.address" label="用户住址" value=""/>
			<s:submit value="查找" align="center"/>
		</table>
	</s:form>
</div>
</body>

</html>