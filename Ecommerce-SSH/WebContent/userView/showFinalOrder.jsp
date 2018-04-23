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
<body class=body>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<%String Score = (String)session.getAttribute("Score"); 
session.setAttribute("Score", Score);%>
	<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回购物"></a>
<div align="center">
	<h1>订单</h1>
	<div id="middle">
	<div align="center">
	<table>
		<tr><td>用户id：</td><td><s:property value="user.id"/></td></tr>
		<tr><td>用户名：</td><td><s:property value="user.name"/></td></tr>
	</table><br>
	总金额：<s:property value="beforecount"/>
	使用积分：<s:property value="useScore"/>
	支付金额：<s:property value="count"/>
	<s:iterator value="orders">
	<table align="center">
		<tr><th>订单id</th><th>收货地址</th><th>联系方式</th><th>收货人姓名</th><th>时间</th><th>状态</th></tr>
		<tr><td><s:property value="id"/></td>
		<td><s:property value="address"/></td>
		<td><s:property value="phonenumber"/></td>
		<td><s:property value="name"/></td>
		<td><s:property value="time"/></td>
		<td><s:property value="state"/></td>
		</tr>
		</table>
		<table align="center">
			<tr><th>订单项id</th><th>商品名称</th><th>商品属性</th><th>数量</th><th>单价</th></tr>
			<s:iterator value="orderItemlist">
			<tr><td><s:property value="id"/></td>
			<td><s:property value="commodity.name"/></td>
			<td><s:property value="commodity.property"/></td>
			<td><s:property value="needNum"/></td>
			<td><s:property value="commodity.price"/></td>
			</tr>
			</s:iterator>
		</table>
		<br><br>
		</s:iterator>
	</div>
	</div>
	</div>
</body>
</html>