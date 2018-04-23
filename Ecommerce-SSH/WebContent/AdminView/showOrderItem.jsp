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
<%String stringid = (String)session.getAttribute("stringid");
session.setAttribute("stringid",stringid);%>
<body class=body>

<a href="adminAction_adminHome.action"><%out.print(admin.getName()); %></a>
<div align="center">
	<h1>订单信息</h1>
	<div id="middle">
	<div align="center">
	<table>
	<tr><td>订单id:</td><td><s:property value="order.id"/></td></tr>
	<tr><td>收货地址:</td><td><s:property value="order.address"/></td></tr>
	<tr><td>联系方式:</td><td><s:property value="order.phonenumber"/></td></tr>
	<tr><td>收货人姓名:</td><td><s:property value="order.name"/></td></tr>
	<tr><td>时间:</td><td><s:property value="order.time"/></td></tr>
	<tr><td>状态:</td><td><s:property value="order.state"/></td></tr>
	<tr><td>总金额:</td><td><s:property value="count"/></td></tr>
	<tr><td>使用积分:</td><td><s:property value="order.useScore"/></td></tr>
	</table>
	<table border="1">
		<tr><th>订单项id</th><th>商品名称</th><th>单价</th><th>数量</th></tr>
		<s:iterator value="orderItemlist">
	
		<tr><td><s:property value="id"/></td>
		<td><s:property value="commodity.name"/></td>
		<td><s:property value="commodity.price"/></td>
		<td><s:property value="needNum"/></td>
		</tr>
		</s:iterator>
	</table>
	<h1>修改订单</h1>
		<s:form action="adminAction_updateOrder.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0">
			<s:textfield name="order.name" label="收货人姓名" value="%{order.name}"/>
			<s:textfield name="order.phonenumber" label="电话" value="%{order.phonenumber}"/>
			<s:textfield name="order.address" label="地址" value="%{order.address}"/>
			<s:submit value="确认" align="center"/>
		</table>
	</s:form>
	</div>
	</div>
<a href="adminAction_showAllOrder.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回"></a>
</div>
</body>

</html>