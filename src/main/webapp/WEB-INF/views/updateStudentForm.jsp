<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: sung-hyuklee
  Date: 2024. 6. 12.
  Time: 오후 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>listStudent</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.css">
    <script src="/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <%--    기말고사 Bootstrap의 class의 속성들을 학습하기--%>
    <div class="col-md-12">
        <h3 class="text-center">Spring Boot with MyBatis Project II</h3>
        <hr>
        <br>
        <div class="card-header">
            <div class="fs-2">학생수정</div>
        </div>
        <br>
        <br>
<%--        해당 클래스에 있는 내용은 bootstrap에서 제공을 함. 스타일링해주는 속성입니다.--%>
        <div class="offcanvas-start">
            <form:form action="updateStudent" method="POST" modelAttribute="student">
                <form:hidden path="id" />
                <div class="mb-3">
                    <label for="name" class="col-md-3">이름</label>
                    <div>
                        <form:input path="name" class="form-control" aria-describedby="nameHelp"/>
                        <div id="nameHelp" class="form-text">수정할 이름을 입력하시오.</div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="col-md-3">이메일</label>
                    <div>
                        <form:input path="email" class="form-control" aria-describedby="emailHelp" />
                        <div id="emailHelp" class="form-text">수정할 이메일을 입력하시오.</div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="address" class="col-md-3">주소</label>
                    <div>
                        <form:input path="address" class="form-control" aria-describedby="addressHelp"/>
                        <div id="addressHelp" class="form-text">수정할 주소를 입력하시오.</div>
                    </div>
                </div>
                <br />
                <div class="mb-3">
                    <div class="col-md-3 col-9">
                        <form:button class="btn btn-primary">수정</form:button>
                    </div>
                </div>

            </form:form>
        </div>


    </div>
</div>

<hr>
<div class="footer">
    <p>&nbsp;&nbsp;^^ Semyung Univ.</p>
</div>
</body>
</html>
