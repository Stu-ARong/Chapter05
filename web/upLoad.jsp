<%--
  Created by IntelliJ IDEA.
  User: 17779
  Date: 2019/10/12
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<jsp:include page="flag.jsp"></jsp:include>
<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="提交">
</form>
</body>
</html>
