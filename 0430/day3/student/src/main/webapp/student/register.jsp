<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>학생-등록</title>
    <link rel="stylesheet" href="/style.css" />
    <meta charset="UTF-8" />
</head>

<body>
<!-- todo action 주소 설정
    //등록
        action = /student/register
    //수정
        action = /student/update
-->

<form method="post" action="${action}">
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <td><input type="text" name="id" value="${student.id}" required /></td>
        </tr>
        <!-- todo input 구현 -->

        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>
</body>
</html>