<%--
  Created by IntelliJ IDEA.
  User: 17779
  Date: 2019/10/11
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>显示角色信息</title>
</head>
<body>
<jsp:include page="flag.jsp"></jsp:include>
<table border="1px" >
    <tr>
        <td width="50px" align="center">编号</td>
        <td width="100px" align="center">名称</td>
        <td width="150px" align="center" colspan="2">操作</td>
    </tr>
    <c:forEach items="${page.resultList}" varStatus="status" var="role">
        <c:if test="${status.count%2!=0}">
            <tr style="background-color:forestgreen;color: white">
        </c:if>
        <c:if test="${status.count%2==0}">
            <tr>
        </c:if>
        <td>${role.roleId}</td>
        <td>${role.roleName}</td>
        <td><a href="role?action=delete&roleId=${role.roleId}">删除</a></td>
        <td><a href="role?action=findUser&roleId=${role.roleId}">修改</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4" align="right">
            <div>
                <c:if test="${page.pageNo!=1}">
                    <a href="role?action=findAll&pageNo=1">首页</a>
                </c:if>
                <c:if test="${page.pageNo-1>=1}">
                <a href="role?action=findAll&pageNo=${page.pageNo-1}">
                    </c:if>
                    <c:if test="${page.pageNo-1==0}">
                    <a href="role?action=findAll&pageNo=1">
                        </c:if>上一页</a>

                    <c:if test="${page.pageNo+1>=page.totalPage}">
                    <a href="role?action=findAll&pageNo=${page.totalPage}">
                        </c:if>
                        <c:if test="${page.pageNo+1<page.totalPage}">
                        <a href="role?action=findAll&pageNo=${page.pageNo+1}">
                            </c:if>
                            下一页</a>
                        <c:if test="${page.pageNo!=page.totalPage}">
                        <a href="role?action=findAll&pageNo=${page.totalPage}">末页</a>
                        </c:if>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
