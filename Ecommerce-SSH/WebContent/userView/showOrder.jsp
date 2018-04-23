<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写订单</title>
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
	<table>
	<tr><th>商品名</th><th>属性</th><th>单价</th><th>数量</th></tr>
	<s:iterator value="cartItems">
		<tr>
		<td><s:property value= "commodity.name" /></td>
		<td><s:property value= "commodity.property"/></td>
		<td><s:property value= "commodity.getPrice()"/></td>
		<td><s:property value="needNum"/></td>
		</tr>
	</s:iterator>
	</table>
		总金额：<s:property value="beforecount"/>元<br>
		使用积分：<s:property value="useScore"/><br>
		需支付：<s:property value="count"/> 元 <br>
	<s:form action="orderAction_placeOrder.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0">
			<s:textfield name="order.name" label="收货人姓名" value="%{user.name}"/>
			<s:textfield name="order.phonenumber" label="电话" value="%{user.phonenumber}"/>
			<s:textfield name="order.address" label="地址" value="%{user.address}"/>
			<s:submit value="确认" align="center" onclick="javascript:alert('确认下单')"/>
		</table>
	</s:form>
	<table>
		<tr><td>用户id：</td><td>${user.getId() }</td></tr>
		<tr><td>用户名：</td><td>${user.getName() }</td></tr>
		<tr><td>手机号：</td><td>${user.getPhonenumber() }</td></tr>
		<tr><td>地址：</td><td>${user.getAddress() }</td></tr>
	</table>
	</div>
	</div>
	</div>
</body>
</html>