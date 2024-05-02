<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body>
<h1>학생 리스트</h1>
<p><a href="/student/register" >학생(등록)</a></p>
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>cmd</th>
    </tr>
    </thead>
    <tbody>
    <!--todo list 구현하기 c:foreach -->
        <c:forEach var="item" items="${studentList}">
            <tr>
                <td>${item.getId()}</td>
                <td style="text-align: center">${item.getName()}</td>
                <td style="text-align: center">${item.getGender()}</td>
                <td style="text-align: center">${item.getAge()}</td>
                <td style="text-align: center"><a href="/student/view.do?id=${item.getId()}">cmd</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>