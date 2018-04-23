<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel=stylesheet href="yb.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
</head>

<body class=body>
<a href="commodityAction_showAll.action"><INPUT TYPE="BUTTON" NAME="button1" VALUE="返回购物"></a>
<h4 align="center">用户登陆</h4>

	<s:form action="userAction_login.action" method="post" validate="false" namespace="/">
		<table width="50%" height="76" border="0" align="center">
			<tr><td></td><td>${msg}</td></tr>
			<s:textfield name="user.name" label="用户名"/>
			<s:textfield name="user.password" label="密码"/>
			<s:select name="usertype" label="类型" list="#{1:'用户',2:'管理员'}" listKey="key" listValue="value" headerKey="1" headerValue="用户"/>
			<s:submit value="登陆" align="center"/>
		</table>
	</s:form>

</body>
</html>