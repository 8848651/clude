<%--
  Created by IntelliJ IDEA.
  User: 86151
  Date: 2023/4/5
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Firmjsp</title>
</head>
<body>
<h1>${name},欢迎您</h1>

<input type="button" value="新增" id="ADD">
<input type="button" value="退出" id="delete"><br>
<hr>

<table border="1" cellspacing="0" width="800">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${brands}" var="brand" varStatus="status">
        <tr align="center">
            <td>${status.count}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.description}</td>
            <c:if test="${brand.status == 1}">
                <td>启用</td>
            </c:if>
            <c:if test="${brand.status != 1}">
                <td>禁用</td>
            </c:if>


            <td><a href="/Web_war/FirmServletRevise?id=${brand.id}">修改</a>

             <a href="/Web_war/FirmServletdelete?id=${brand.id}">删除</a></td>


        </tr>
    </c:forEach>
</table>


<script>
    document.getElementById("ADD").onclick=function () {
        location.href="/Web_war/FirmJSP/Firmadd.jsp";
    }

    document.getElementById("delete").onclick=function () {
        location.href="/Web_war/landingHtml/login.jsp";
    }
</script>
</body>
</html>
