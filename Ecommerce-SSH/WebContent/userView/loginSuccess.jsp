<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel=stylesheet href="yb.css" type="text/css">
<%@ taglib prefix = "s" uri="/struts-tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户首页</title>
</head>
<body class=body>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="购物城"></a>
<div align="center">
	<h1>用户首页</h1>
	<div id="middle">
	<div align="center">
	<table>
		<tr><td>用户id：</td><td><s:property value="user.id" /></td></tr>
		<tr><td>用户名：</td><td><s:property value="user.name" /></td></tr>
		<tr><td>密码：</td><td><s:property value="user.password" /></td></tr>
		<tr><td>邮箱：</td><td><s:property value="user.email" /></td></tr>
		<tr><td>手机号：</td><td><s:property value="user.phonenumber" /></td></tr>
		<tr><td>地址：</td><td><s:property value="user.address" /></td></tr>
		<tr><td>拥有积分：</td><td><s:property value="user.accScore" /></td></tr>
	</table>
	</div>
	</div>
	<!-- <c:url value="/js/jquery.js"/> -->
	
	<a href="commodityAction_beforeadd.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="发布商品"></a>
	<a href="shoppingAction_showCart.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="购物车"></a>
	<a href="userAction_beforeupdate"><INPUT TYPE="BUTTON" NAME="button1" VALUE="修改个人信息"></a>
	<a href="orderAction_showUserOrder.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="查看购买订单"></a>
	<a href="orderAction_showNeedFinishOrder.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="查看需完成订单"></a>
	<a href="commodityAction_showMyCommodity.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="我的商品"></a>
	<a href="userAction_logout.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="注销登陆"></a>
	</div>
</body>
</html>