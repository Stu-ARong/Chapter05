<%--
  Created by IntelliJ IDEA.
  User: 17779
  Date: 2019/10/11
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改角色信息</title>
</head>
<body>
<form action="role?action=update" method="post">
    编号：<input type="text" name="roleId" value="${role.roleId}" readonly style="background-color: #CCcccc"> <br>
    名称：<input type="text" name="roleName" value="${role.roleName}"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
