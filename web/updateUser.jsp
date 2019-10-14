<%--
  Created by IntelliJ IDEA.
  User: 17779
  Date: 2019/10/11
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
<form action="user?action=update" method="post">
    用户名：<input type="text" value="${user.userName}" name="userName"> <br>
    密码：<input type="text" value="${user.pwd}" name="pwd"> <br>
    消息：<input type="text" value="${user.messageSrc}" name="messageSrc"> <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
