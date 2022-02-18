<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Sign up</title>
</head>
<jsp:include page="header.jsp"/>
<body class="text-center align-content-center"
      class="p-3 mb-2 bg-secondary text-white text-align: center"
      style="background-color: #7cb5bd">
<h3><fmt:message key="signUp.signUp"/></h3>
<form name="registerForm" method="POST" action="controller">
    <input type="hidden" name="command" value="signUp"/>
    <div class="input-group mb-3 mx-auto " style="width: 30rem">
        <span  class="input-group-text" id="inputGroup0"><fmt:message key="signUp.firstName"/></span>
        <input  type="text" class="form-control" name="firstName" value="" aria-label="input"
               aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="input-group mb-3 mx-auto " style="width: 30rem">
        <span  class="input-group-text" id="inputGroup1"><fmt:message key="signUp.lastName"/></span>
        <input  type="text" class="form-control" name="lastName" value=""  aria-label="input"
                aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="text-danger">
        <c:if test="${temp.signUpDataError == true}">
            <p>
                <fmt:message key="signUp.name.info"/>
            </p>
        </c:if>
    </div>
    <div class="input-group mb-3 mx-auto " style="width: 30rem">
        <span  class="input-group-text" id="inputGroup2"><fmt:message key="signUp.email"/></span>
        <input  type="email" class="form-control" name="email" value="" aria-label="input"
                aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="text-success">
        <c:if test="${temp.signUpEmailError == false}">
            <p>
                <fmt:message key="signUp.email.info"/>
            </p>
        </c:if>
    </div>
    <div class="text-danger">
        <c:if test="${temp.signUpEmailError == true}">
            <p>
                <fmt:message key="signUp.email.identity.error"/>
            </p>
        </c:if>
    </div>
    <div class="input-group mb-3 mx-auto " style="width: 30rem">
        <span  class="input-group-text" id="inputGroup3"><fmt:message key="signUp.password"/></span>
        <input  type="password"  name="password" value="" class="form-control" aria-label="input"
                aria-describedby="inputGroup-sizing-default">
    </div>
    <div class="text-success">
        <c:if test="${temp.signUpDataError == false}">
            <p>
                <fmt:message key="signUp.password.info"/>
            </p>
        </c:if>
    </div>
    <div class="text-danger">
        <c:if test="${temp.signUpDataError == true}">
            <p>
                <fmt:message key="login.password.info"/>
            </p>
        </c:if>
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="signUp.submit"/></button>
</form>


</body>
</html>
