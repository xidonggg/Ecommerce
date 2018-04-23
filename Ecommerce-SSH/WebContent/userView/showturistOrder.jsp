<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<body class=body>
<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="购物城"></a>
<div align="center">
	<h1>订单</h1>
	<div id="middle">
	<div align="center">
	<table  align="center">
	<tr><th>商品名</th><th>属性</th><th>单价</th><th>数量</th></tr>
		<tr>
		<td><s:property value="commodity.name"/></td>
		<td><s:property value="commodity.property"/></td>
		<td><s:property value="commodity.price"/></td>
		<td><s:property value="needNum"/></td>
		</tr>
	</table>
		总金额：<s:property value="count"/> 元 <br>
	<s:form action="orderAction_turistplaceOrder.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0">
			<s:hidden name="user.id" value="%{user.id}" />
			<s:hidden name="commodity.id" value="%{commodity.id}" />
			<s:hidden name="needNum" value="%{needNum}" />
			<s:textfield name="order.name" label="收货人姓名"/><!--  lable=<s:property value= "user.name" />/>lable=<s:property value= "user.phonenumber" /> lable=<s:property value= "user.address" />-->
			<s:textfield name="order.phonenumber" label="电话" />
			<s:textfield name="order.address" label="地址" />
			<s:submit value="确认" align="center"/>
		</table>
	</s:form>
	</div>
	</div>
	</div>
</body>
</html>