<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>

<header>
</header>
<main>
    <form action="/Web_war/loginServlet" method="post" id="form">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input id="username" name="username" value="${cookie.usename.value}" type="text">
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input id="password" name="password"  value="${cookie.password.value}" type="password">
        </div>
        <div class="form-group">
            <input id="remember" name="remember" value="1" type="checkbox">
            <label for="remember">记住我</label>
        </div>
        <div class="form-group">
            <input type="submit" class="button" value="登陆">
            <input type="reset" class="button" value="重置">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">注册</a>
        </div>
        <div class="error-msg">${loginmistake}</div>
    </form>
</main>
<!--
<script src="../JS/Login.js"></script>
-->
</body>
</html>