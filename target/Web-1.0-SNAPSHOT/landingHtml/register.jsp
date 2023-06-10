<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>

    <form id="reg-form" action="/Web_war/RegistServlet" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" style="display: none">${registmistake}</span>
                </td>
            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">${registmistake}</span>
                </td>
            </tr>

            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="Captcha" type="text" id="Captcha">
                    <span id="Captcha_err" class="err_msg" style="display: none">${Captchamistake}</span>
                </td>
            </tr>

            <tr>
                <td><a href="#" id="Captchapass">换一张</a></td>
                <td>
                    <img src="/Web_war/CapthcaServlet" id="imgs">
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input  value="注 册" type="submit" id="reg_btn">
        </div>

        <br class="clear">

        <script>
            document.getElementById("Captchapass").onclick=function () {
                let milliseconds = new Date().getMilliseconds();
                //？不要忘
                document.getElementById("imgs").src="/Web_war/CapthcaServlet?"+milliseconds;
            }
        </script>
    </form>

</div>
</body>
</html>