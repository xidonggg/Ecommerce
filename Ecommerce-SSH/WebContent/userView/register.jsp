<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
</head>

<body class=body>
<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回购物"></a>

<div align="center">
<h4 align="center">用户注册</h4>
<div id="middle" >
<div align="center">
	<s:form action="userAction_register.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">	
			<tr><td></td><td>${msg}</td></tr>	
			<s:textfield name="user.name" label="用户名"/>
			<s:textfield name="user.password" label="密码"/>
			<s:textfield name="repwd" label="确认密码"/>
			<s:textfield name="user.phonenumber" label="手机号码"/>
			<s:textfield name="user.address" label="地址"/>
			<s:textfield name="user.email" label="电子邮箱"/>
			<s:submit value="注册" align="center"/>
		</table>
	</s:form>
	</div>
</div>
</div>
</body>
</html>