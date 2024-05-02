<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
    <tbody>
    <!-- todo view 구현 -->
    <table border="1sp">
        <tr>
            <th>아이디</th>
            <td>${student.getId()}</td>
        </tr>

        <tr>
            <th>이름</th>
            <td>${student.getName()}</td>
        </tr>

        <tr>
            <th>성별</th>
            <td>${student.getGender()}</td>
        </tr>

        <tr>
            <th>나이</th>
            <td>${student.getAge()}</td>
        </tr>

        <tr>
            <th>등록일</th>
            <td>${student.getCreatedAt()}</td>
        </tr>
    </table>
    </tbody>
</table>
<ul>
    <li><a href="/student/list">리스트</a></li>
    <li>
        <!-- todo ${update_link} 설정 c:url -->
        <a href="/student/update.do?id=${student.getId()}">수정</a>
    </li>
    <li>
        <!-- todo 삭제버튼 구현, method=post -->
        <form method="post" action="/student/delete.do">
            <input type = "hidden" name = "id" value = "${student.getId()}">
            <input type = "submit" value = "삭제">
        </form>
     </li>

 </ul>

</body>
</html>