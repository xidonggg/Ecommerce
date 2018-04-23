<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品发布成功</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<body class=body>
<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回购物"></a>
<div align="center">
	<h1>发布成功</h1>
<div id="middle">
<div align="center">
	<table>
		<tr><td><IMG SRC=${commodity.getPicsrc() } WIDTH=200 HEIGHT=200></td></tr>
		<tr><td>商品id：</td><td>${commodity.getId() }</td></tr>
		<tr><td>商品名称：</td><td>${commodity.getName() }</td></tr>
		<tr><td>单价：</td><td>${commodity.getPrice() }</td></tr>
		<tr><td>数量：</td><td>${commodity.getNum() }</td></tr>
		<tr><td>属性：</td><td>${commodity.getProperty() }</td></tr>
		<tr><td>描述：</td><td>${commodity.getComdescribe() }</td></tr>
		<tr><td>发布者id：</td><td>${commodity.getUser().getId() }</td></tr>
		<tr><td>发布者名字：</td><td>${commodity.getUser().getName() }</td></tr>
		<tr><td>发布者联系方式：</td><td>${commodity.getUser().getPhonenumber() }</td></tr>
	</table>
</div>
</div>
	</div>
</body>
</html>