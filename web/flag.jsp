<%--
  Created by IntelliJ IDEA.
  User: 17779
  Date: 2019/10/11
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>显示提示信息</title>
</head>
<body>
    <c:if test="${flag!=null}">
        <script>
            alert("${flag}")
        </script>
    </c:if>
    <c:remove var="flag"></c:remove>
</body>
</html>
