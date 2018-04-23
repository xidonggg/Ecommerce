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
	<h1>所有订单</h1>
	<div id="middle">
	<div align="center">
	<table border="0" align="center">
		<tr><th>订单id</th><th>下单用户</th><th>收货地址</th><th>联系方式</th><th>收货人姓名</th><th>时间</th><th>状态</th></tr>
		<s:iterator value="orderlist">
	
		<tr><td><a href="adminAction_findOrderItems?order.id=<s:property value="id"/>"><s:property value="id"/></a></td>
		<td><s:property value="user.name"/></td>
		<td><s:property value="address"/></td>
		<td><s:property value="phonenumber"/></td>
		<td><s:property value="name"/></td>
		<td><s:property value="time"/></td>
		<td><s:property value="state"/></td>
		<td><a href='orderAction_updateStateSeller.action?stringid=<s:property value="id"/>'><INPUT TYPE="BUTTON" NAME="button1" VALUE="已发货"></a></td>
		</tr>
		</s:iterator>
	</table>
	</div>
	</div>
	<s:form action="adminAction_findOrders.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">
			
			<s:textfield name="order.user.name" label="下单用户" value=""/>
			<s:textfield name="order.address" label="收货地址" value=""/>
			<s:select name="statetype" label="状态" list="#{1:'订单失败',2:'待发货',3:'已发货',4:'已收货'}" listKey="key" listValue="value" headerKey="0" headerValue="全部"/>
			<s:submit value="查找" align="center"/>
		</table>
	</s:form>
</div>
</body>

</html>