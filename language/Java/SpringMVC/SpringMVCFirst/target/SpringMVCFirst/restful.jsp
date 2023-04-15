<%--
  Created by IntelliJ IDEA.
  User: xiaozhi
  Date: 12.12.20
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Restful</title>
</head>
<body>
    <h1>get请求</h1>
    <form action="/TryRestful/xiaozhi1" method="get">
        name<input type="text" uname="getMethod"><br>
        <input type="submit" >
    </form>
    <br><br>

    <h1>post请求</h1>
    <form action="/TryRestful/xiaozhi2" method="post">
        name<input type="text" uname="postMethod"><br>
        <input type="submit" >
    </form>
    <br><br>

    <h1>delete请求</h1>
    <form action="/TryRestful/xiaozhi3" method="post">
        <input type="hidden" name="_method" value="delete">
        name<input type="text" uname="deleteMethod"><br>
        <input type="submit" >
    </form>
    <br><br>

    <h1>put请求</h1>
    <form action="/TryRestful/xiaozhi4" method="post">
        <input type="hidden" name="_method" value="put">
        name<input type="text" uname="putMethod"><br>
        <input type="submit" >
    </form>
    <br><br>

</body>
</html>
