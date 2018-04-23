<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "com.po.*" %>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电子商务</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>

<body class=body>

<%if(user == null){ %>
<a href="userAction_beforelogin.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="登陆"></a>
<!--<a href="../userView/register.jsp"><INPUT TYPE="BUTTON" NAME="button2" VALUE="注册"></a>  -->
<a href="userAction_beforeregister.action"><INPUT TYPE="BUTTON" NAME="button2" VALUE="注册"></a>

<%}else{ %>
<a href="userAction_userHome.action"><%out.print(user.getName()); %></a>
<a href="shoppingAction_showCart.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="购物车"></a>
<%} %>
<div align="center">
	<h1>商品展示</h1>
		<s:form action="commodityAction_findCommodities.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">		
			<s:textfield name="commodity.name" label="商品名称" value=""/>
			<s:select name="property" label="种类" list="#{1:'日用品',2:'零食',3:'电子产品'}" listKey="key" listValue="value" headerKey="0" headerValue="全部"/>
			<s:submit value="查找" align="center"/>
		</table>
	</s:form>
	<div id="middle">
	<div align="center">
	<table align = "center">
		<tr><th>图片</th><th>商品名</th><th>属性</th><th>单价</th></tr>
		<s:iterator value="commodities"> 
	
		<tr><td><a href="viewDetails.action?commodity.id=<s:property value='id'/>"><IMG SRC=<s:property value="picsrc"/> WIDTH=200 HEIGHT=200></a></td>
		<td><s:property value="name"/></td>
		<td><s:property value="property"/></td>
		<td><s:property value="price"/>元</td>
		</tr>
		</s:iterator>
	</table>
	</div>
	</div>
</div>
</body>

</html>