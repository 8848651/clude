
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/Web_war/ServletLoading" method="POST">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>

<script>

    if (window.XMLHttpRequest) {
        // 用于现代浏览器的代码
        xmlhttp = new XMLHttpRequest();
    } else {
        // 应对老版本 IE 浏览器的代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //注意前后端分离这里要写全
    xhttp.open("GET", "http://localhost/Web_war/ServletLoading");
    xhttp.send();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
        }
    };
</script>

</body>
</html>
