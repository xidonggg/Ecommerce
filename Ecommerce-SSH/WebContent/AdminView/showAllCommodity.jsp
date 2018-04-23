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
	<h1>所有商品</h1>
	<div id="middle">
	<div align="center">
	<table align="center">
		<tr><th>商品id</th><th>商品名称</th><th>种类</th><th>单价</th><th>数量</th><th>状态</th></tr>
		<s:iterator value="commoditylist">
	
		<tr><td><a href="adminAction_findCommodity?commodity.id=<s:property value="id"/>"><s:property value="id"/></a></td>
		<td><s:property value="name"/></td>
		<td><s:property value="property"/></td>
		<td><s:property value="price"/></td>
		<td><s:property value="num"/></td>
		<td><s:property value="state"/></td>
		</tr>
		</s:iterator>
	</table>
	</div>
	</div>
	<s:form action="adminAction_findCommodities.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">
		<!--<s:textfield name="user.id" label="用户id"/>  -->
			<s:textfield name="commodity.name" label="商品名称" value=""/>
			<s:select name="propertytype" label="种类" list="#{1:'日用品',2:'零食',3:'电子产品'}" listKey="key" listValue="value" headerKey="0" headerValue="全部"/>
			<s:select name="statetype" label="状态" list="#{1:'待审核',2:'已审核',3:'不通过',4:'已下架'}" listKey="key" listValue="value" headerKey="0" headerValue="全部"/>
			<s:submit value="查找" align="center"/>
		</table>
	</s:form>
</div>
</body>

</html>