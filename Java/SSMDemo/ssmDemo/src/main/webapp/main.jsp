<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>学生管理</h2> <h4><a href="/logout">退出</a></h4>
<h1>根据id查询学生信息</h1>
<form action="/getById" method="post">
    id:<input type="text" name="id"><br><br>
    <input type="submit">
</form>
<h1>根据id删除学生信息</h1>
<form action="/deleteById" method="post">
    id:<input type="text" name="id"><br><br>
    <input type="submit">
</form>
<h1>根据id修改学生信息</h1>
<form action="/updateInfo" method="post">
    id:<input type="text" name="id"><br><br>
    name:<input type="text" name="name"><br><br>
    passwd:<input type="text" name="passwd"><br><br>
    age:<input type="text" name="stuage"><br><br>
    number:<input type="text" name="stunumber"><br><br>
    <input type="submit">
</form>
<h1>添加学生信息</h1>
<form action="/addInfo" method="post">
    id:<input type="text" name="id"><br><br>
    name:<input type="text" name="name"><br><br>
    passwd:<input type="text" name="passwd"><br><br>
    age:<input type="text" name="stuage"><br><br>
    number:<input type="text" name="stunumber"><br><br>
    <input type="submit">
</form>
</body>
</html>

