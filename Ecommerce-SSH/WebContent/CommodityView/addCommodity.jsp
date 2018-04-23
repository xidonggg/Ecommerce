<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "com.po.*" %>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加商品页面</title>
</head>
<%User user = (User)session.getAttribute("user"); 
session.setAttribute("user", user);
%>
<body class=body>
<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回购物"></a>
<div align="center">
<h4 align="center">添加商品</h4>
<div id="middle" >
<div align="center">
	<s:form action="commodityAction_add.action" method="post" validate="false" namespace="/" enctype="multipart/form-data">
		<table width="50%" height="76" border="0" align="center">
			<s:textfield name="commodity.name" label="商品名称"/>
			<s:textfield name="commodity.num" label="数量"/>
			<s:textfield name="commodity.price" label="单价"/>
			<s:select name="property" label="商品属性" list="#{1:'日用品',2:'零食',3:'电子产品'}" listKey="key" listValue="value" headerKey="1" headerValue="日用品"/>
			<s:textarea name="commodity.comdescribe" label="描述" style="width:200px; height:100px"/>
			<s:file name="image" label="上传图片"/>
			<s:submit value="发布" align="center"/>
		</table>
	</s:form>
</div>
</div>
</div>
</body>
</html>