<%--
  Created by IntelliJ IDEA.
  User: HeWei
  Date: 2019/4/12
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<h1>欢迎登陆</h1>
<form action="/loginUser" method="post">
    <input name="username" type="text"/><br>
    <input name="password" type="password"/><br>

    <input type="submit" value="提交">
</form>

</body>
</html>
