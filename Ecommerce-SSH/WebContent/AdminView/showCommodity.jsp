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

<body class=body>

<a href="adminAction_adminHome.action"><%out.print(admin.getName()); %></a>
<div align="center">
	<h1>商品信息</h1>
	<div id="middle" >
	<div align="center">
	<table>
	<tr><td>商品id:</td><td><s:property value="commodity.id"/></td></tr>
	<tr><td>商品名称：</td><td><s:property value="commodity.name"/></td></tr>
	<tr><td>商品分类：</td><td><s:property value="commodity.property"/></td></tr>
	<tr><td>商品单价：</td><td><s:property value="commodity.price"/></td></tr>
	<tr><td>商品库存：</td><td><s:property value="commodity.num"/></td></tr>
	<tr><td>商品状态：</td><td><s:property value="commodity.state"/></td></tr>
	<tr><td>商品描述：</td><td><s:property value="commodity.comdescribe"/></td></tr>
	<tr><td>商品图片：</td><td><IMG SRC=<s:property value="commodity.picsrc"/> WIDTH=200 HEIGHT=200></td></tr>
	</table>
	</div>
	</div>
<a href="adminAction_passCommodity.action?commodity.id=<s:property value="commodity.id"/>&commodity.state=yes"><INPUT TYPE="BUTTON" NAME="button1" VALUE="已审核"></a>	
<a href="adminAction_passCommodity.action?commodity.id=<s:property value="commodity.id"/>&commodity.state=no"><INPUT TYPE="BUTTON" NAME="button1" VALUE="不通过"></a>
<a href="adminAction_showAllCommodity.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回"></a>
${msg }
</div>
</body>

</html>