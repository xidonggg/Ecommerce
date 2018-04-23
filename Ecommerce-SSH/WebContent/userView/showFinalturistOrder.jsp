<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游客订单</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<body class=body>
	<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回购物"></a>
<div align="center">
	<h1>订单</h1>
	<div id="middle">
	<div align="center">
	<table>
		<tr><td>订单编号：</td><td><s:property value="order.id"/> </td></tr>
		<tr><td>下单时间：</td><td><s:property value="order.time"/> </td></tr>	
		<tr><td>收货人姓名：</td><td><s:property value="order.name"/> </td></tr>
		<tr><td>联系方式：</td><td><s:property value="order.phonenumber"/> </td></tr>
		<tr><td>地址：</td><td><s:property value="order.address"/> </td></tr>
		<tr><td>总金额：</td><td><s:property value="count"/> 元 </td></tr>
	</table>
	<br><br>
	<table>
	<tr><th>商品名</th><th>属性</th><th>单价</th><th>数量</th></tr>
		<tr>
		<td><s:property value="commodity.name"/></td>
		<td><s:property value="commodity.property"/></td>
		<td><s:property value="commodity.price"/></td>
		<td><s:property value="needNum"/></td>
		</tr>
	</table>
	<br>
	</div>
	</div>
	</div>
</body>
</html>