<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
</head>
<body class=body>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<%
String cart = (String)session.getAttribute("cart");
%>

<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="购物城"></a>
<div align="center">
	<h1>购物车</h1>
	<div id="middle">
	<div align="center">
	<table align="center">
		<tr><td>用户id：</td><td><s:property value="user.id"/></td></tr>
		<tr><td>用户名：</td><td><s:property value="user.name"/></td></tr>
		<tr><td>手机号：</td><td><s:property value="user.phonenumber"/></td></tr>
		<tr><td>地址：</td><td><s:property value="user.address"/></td></tr>
	</table>
	<%if(cart.equals("null") ) {%>
	购物车是空的
	<%}else{ %>
	<table align="center">
	<tr><th>商品名</th><th>属性</th><th>单价</th><th>数量</th></tr>
	<s:iterator value="cartItems">
		<tr>
		<td><s:property value= "commodity.name" /></td>
		<td><s:property value= "commodity.property"/></td>
		<td><s:property value= "commodity.getPrice()"/></td>
		<td><s:property value="needNum"/></td>
		<td><a href="shoppingAction_deleteItem.action?cartItemid=<s:property value= 'id' />"><INPUT TYPE="BUTTON" NAME="button1" VALUE="删除"></a></td>
		</tr>
	</s:iterator>
	</table>
		总金额：<s:property value="count"/> 元 <br>
		
	<s:form action="orderAction_beforePlaceOrder.action" method="post" validate="false" namespace="/">
		<s:textfield name="useScore" label="使用积分" value="0"/>
		您拥有的积分：<s:property value="user.accScore"/>
		<s:submit value="下单" align="center"/>
	</s:form>
	${msg }
	<%} %>
	</div>
	</div>
	</div>
</body>
</html>