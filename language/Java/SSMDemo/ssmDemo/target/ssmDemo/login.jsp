<%--
  Created by IntelliJ IDEA.
  User: xiaozhi
  Date: 03.01.21
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>欢迎登录</h1>
<form method="post" action="/login">
    username<input type="text" name="name"><br><br>
    password<input type="password" name="passwd"><br><br>
    <input type="submit">
</form>
</body>
</html>
