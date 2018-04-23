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
	<h1>所有留言</h1>
	<div id="middle">
	<div align="center">
	<table align="center">
		<tr><th>留言id</th><th>用户id</th><th>用户名</th><th>商品id</th><th>商品名称</th><th>留言信息</th></tr>
		<s:iterator value="leaveMessagelist">
		<tr>
		<td><s:property value="id"/></td>
		<td><s:property value="user.id"/></td>
		<td><s:property value="user.name"/></td>
		<td><s:property value="commodity.id"/></td>
		<td><s:property value="commodity.name"/></td>
		<td><s:property value="contact"/></td>
		<td><a href='adminAction_deleteMessage.action?leaveMessage.id=<s:property value="id"/>'><INPUT TYPE="BUTTON" NAME="button1" VALUE="删除留言"></a></td>
		</tr>
		</s:iterator>
	</table>
	</div>
	</div>
		<s:form action="adminAction_findleaveMessages.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">
			<s:textfield name="commodity.id" label="商品id" />
			<s:textfield name="user.id" label="用户id" />
			<s:submit value="查找" align="center"/>
		</table>
	</s:form>
</div>
</body>

</html>