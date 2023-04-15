<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="/try" >ConnectWebAndServlet</a>
<br><br><br>

<h1>使用HttpServlet获取参数</h1>
<form action="/parameters1?uname=${uname}&unumber=${unumber}&uage=${uage}" name="register" method="get">
    name<input type="text" name="uname"><br>
    idnumber<input type="text" name="unumber"><br>
    age<input type="text" name="uage"><br>
    submit<input type="submit" name="usubmit">
</form>
<br><br><br>

<h1>使用同名获取参数</h1>
<form action="/parameters2" method="post">
    name<input type="text" name="uname"><br>
    idnumber<input type="text" name="unumber"><br>
    age<input type="text" name="uage"><br>
    submit<input type="submit" name="usubmit">
</form>
<br><br><br>

<h1>日期获取的原始方式</h1>
<form action="/date1" name="date" method="post">
    date<input type="text" name="birthday"><br>
    <input type="submit">
</form>

<h1>日期获取的字符串方式</h1>
<form action="/date3" method="post">
    date<input type="text" name="birthday"><br>
    <input type="submit">
</form>

<h1>日期获取的特殊方式</h1>
<form action="/date2" name="date" method="get">
    date<input type="text" name="birthday"><br>
    <input type="submit">
</form>
<br><br><br>

<h1>从后台获取数据</h1>
<a href="/getParameters1">Method1</a>
<a href="/getParameters2">Method2</a>
<a href="/getParameters3">Method3</a>
<a href="/getParameters4">Method4</a>
<br><br><br>

<h1>设置session</h1>
<a href="/sessionAndAlert">session</a>

</body>
</html>
