<%--
  Created by IntelliJ IDEA.
  User: 17779
  Date: 2019/10/14
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<jsp:include page="flag.jsp"></jsp:include>
<form action="user?action=addUser" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="userName"> <br>
    密 码： &nbsp;<input type="password" name="pwd"> <br>
    消息来源：&nbsp; <input type="text" name="messageSrc"> <br>
    头像： &nbsp;&nbsp;<input type="file" name="photo"> <br>
    <input type="submit" value="提 交">
</form>
</body>
</html>
