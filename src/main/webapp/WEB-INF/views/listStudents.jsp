<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>listStudent</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.css">
    <script src="/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>

<%--
Declaration: <%! %>
Expression: <%= %>
Scriptlet: <% %>

Implicit Object (내장 객체)

[정의] XML Document에서 namespace는 해당 document가 속해 있는 scope
또는 group을 definition하는
[MyBatis]
namespace는 DAO의 path를 써준다
--%>
<div class="container">
    <%--    기말고사 Bootstrap의 class의 속성들을 학습하기--%>
    <div class="col-md-12">
        <h3 class="text-center">Spring Boot with MyBatis Project II</h3>
        <hr>
        <br>
        <input type="button" value="학생등록"
               onclick="window.location.href='showForm'; return false;"
               class="btn btn-primary">
        <br>
        <br>
        <div class="text-center">
            <div class="card-header">
                <%--    기말고사 Bootstrap의 class의 속성들을 학습하기--%>
                <div class="fs-2">학생목록</div>
            </div>
            <div class="card-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>주소</th>
                        <th>비고</th>
                    </tr>
                    <%--                    JSTL loop statement--%>
                    <c:forEach var="tempStudent" items="${students}">
                        <c:url var="updateLink" value="/student/updateForm">
                            <c:param name="studentId" value="${tempStudent.id}"/>
                        </c:url>
                        <c:url var="deleteLink" value="/student/deleteForm">
                            <c:param name="studentId" value="${tempStudent.id}"/>
                        </c:url>
                        <tr>
                            <td>${tempStudent.id}</td>
                            <td>${tempStudent.name}</td>
                            <td>${tempStudent.email}</td>
                            <td>${tempStudent.address}</td>
                            <td>
                                <a href="${updateLink}">수정</a> |
                                <a onclick="if ( !(confirm('Are you sure about delete that?'))) return false;" href="${deleteLink}">삭제</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>


    </div>
</div>
<hr>
<div class="footer">
    <p>&nbsp;&nbsp;^^ Semyung Univ.</p>
</div>

</body>
</html>