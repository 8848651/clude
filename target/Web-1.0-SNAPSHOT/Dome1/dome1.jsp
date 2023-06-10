
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
c:if：来完成逻辑判断，替换java if else
--%>
    ${status}
    <c:if test="${status==1}">
        <h1>启用</h1>
    </c:if>

    <c:if test="${status==0}">
        <h1>禁用</h1>
    </c:if>



</body>
</html>
