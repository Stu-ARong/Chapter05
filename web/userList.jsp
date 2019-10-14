<%--
  Created by IntelliJ IDEA.
  User: 17779
  Date: 2019/10/11
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>显示所有的用户</title>
</head>
<body>
<jsp:include page="flag.jsp"></jsp:include>
<table border="1px" align="center">
    <tr>
        <td width="100px">用户名</td>
        <td width="100px">密码</td>
        <td width="100px">消息</td>
        <td width="150px" colspan="2">操作</td>
    </tr>
    <c:forEach items="${users.resultList}" varStatus="status" var="user">
        <c:if test="${status.count%2!=0}">
            <tr style="background-color:forestgreen;color: white">
        </c:if>
        <c:if test="${status.count%2==0}">
            <tr>
        </c:if>
        <td>${user.userName}</td>
        <td>${user.pwd}</td>
        <td>${user.messageSrc}</td>
        <td><a href="user?action=delete&userName=${user.userName}">删除</a></td>
        <td><a href="user?action=findUser&userName=${user.userName}">修改</a></td>
        </tr>
    </c:forEach>

    <tr>
        <c:if test="${users.totalPage>1}">
            <td colspan="3">
                <c:if test="${users.pageNo-1>=1}">
                    <a href="user?action=findAll&pageNo=1">首页</a>
                    <a href="user?action=findAll&pageNo=${users.pageNo-1}">上一页</a>
                </c:if>

                <c:if test="${users.pageNo +1 <= users.totalPage}">
                    <a href="user?action=findAll&pageNo=${users.pageNo+1}">下一页</a>
                    <a href="user?action=findAll&pageNo=${users.totalPage}">末页</a>
                </c:if>
            </td>
        </c:if>
        <td colspan="2" align="right" style="text-align: center">
            <a href="addUser.jsp">添加用户</a>
        </td>
    </tr>

</table>

</body>
</html>
