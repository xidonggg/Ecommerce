<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的商品</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>

<body class=body>
<a href="userAction_userHome.action"><s:property value="user.name"/></a>
<div align="center">
	<h1>我发布的商品</h1>
	<div id="middle">
	<div align="center">
	<table border="1" align = "center">
		<tr><th>图片</th><th>商品名</th><th>属性</th><th>单价</th><th>数量</th><th>描述</th><th>状态</th></tr>
		<s:iterator value="commodities"> 
	
		<tr><td><a href="viewDetails.action?commodity.id=<s:property value='id'/>"><IMG SRC=<s:property value="picsrc"/> WIDTH=200 HEIGHT=200></a></td>
		<td><s:property value="name"/></td>
		<td><s:property value="property"/></td>
		<td><s:property value="price"/>元</td>
		<td><s:property value="num"/></td>
		<td><s:property value="comdescribe"/></td>
		<td><s:property value="state"/></td>
		<td><a href="commodityAction_updateCommodityState.action?commodity.id=<s:property value='id'/>"><INPUT TYPE="BUTTON" NAME="button1" VALUE="下架"></a></td>
		</tr>
		
		</s:iterator>
	</table>
	</div>
	</div>
</div>
</body>

</html>