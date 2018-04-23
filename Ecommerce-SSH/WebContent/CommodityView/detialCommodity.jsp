<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "com.po.*" %>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<body class=body>

<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="购物城"></a>
<div align="center">

	<h1>详细信息</h1>
	<div id="middle">
	<div align="center">
		<IMG SRC=<s:property value="commodity.picsrc"/> WIDTH=200 HEIGHT=200>
	<table align="center">
		<tr><td>商品名称：</td><td><s:property value="commodity.name"/></td></tr>
		<tr><td>单价：</td><td><s:property value="commodity.price"/></td></tr>
		<tr><td>数量：</td><td><s:property value="commodity.num"/></td></tr>
		<tr><td>属性：</td><td><s:property value="commodity.property"/></td></tr>
		<tr><td>描述：</td><td><s:property value="commodity.comdescribe"/></td></tr>

	</table>
	
	<s:form  method="post" validate="false" namespace="/">
	<s:hidden name="commodity.id" value="%{commodity.id}" />
	
	<s:textfield name="needNum" label="数量" size="10" value="1"/>
	<s:submit value="加入购物车" align="center" action="shoppingAction_addCart"/>
	<s:submit value="购买" align="center" action="orderAction_beforeTuristPlaceOrder"/>
	</s:form>
	${msg}
	
	<table  align="center">
		<tr><th>留言</th></tr>
		<s:iterator value="leaveMessages">
		<tr><td><s:property value="user.name"/></td><td><s:property value="contact"/></td></tr>
		</s:iterator>
	</table>
	
	<s:form action="commodityAction_leaveMessage.action" method="post" validate="false" namespace="/">
		<s:hidden name="commodity.id" value="%{commodity.id}" />
		<s:textfield name="leaveMessage.contact" label="留言" size="10" value="我想问老板。。。"/>
	<s:submit value="提交" align="center" />
	</s:form>
	</div>
</div>
</div>
</body>
</html>