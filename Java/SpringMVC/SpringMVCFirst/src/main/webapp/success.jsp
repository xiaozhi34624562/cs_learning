<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<body>
<h2>Successfully connected!</h2>
<br><br><br>
<h1>Method1 to get parameters from front = ${method1} </h1>
<h1>Method2 to get parameters from front = ${method2} </h1>
<h1>Method3 to get parameters from front = ${method3} </h1>
<h1>Method4 to get parameters from front = ${method4} </h1>
<br><br><br>

<h1>设置session的方法${sessionScope.sessionValue}</h1>
<br><br><br>

<h1>Restful</h1>
<h1>Restful的method: ${sessionScope.get}</h1>
<h1>Restful的method: ${sessionScope.post}</h1>
<h1>Restful的method: ${sessionScope.delete}</h1>
<h1>Restful的method: ${sessionScope.put}</h1>
</body>
</html>
