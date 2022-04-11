<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Login</title>
</head>

<jsp:include page="header.jsp"/>
<body class="text-center" style="background-color: #7cb5bd">

<h3><fmt:message key="login.title"/></h3>
<form name="loginForm" method="POST" action="controller">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label"><fmt:message key="login.email"/></label>
        <input type="hidden" name="command" value="login"/>
        <input style="width: 22rem" name="email" value="" type="email" class="form-control mx-auto"
               id="exampleInputEmail1" aria-describedby="emailHelp">
        <div id="emailHelp" class="font-weight-bold">
            <div class="text-danger">
                <c:if test="${temp.emailError == true}">
                    <p>
                        <fmt:message key="login.error.message"/>
                    </p>
                </c:if>
            </div>
        </div>
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label"><fmt:message key="login.password"/></label>
        <input type="password" name="password" value="" style="width: 22rem" class="form-control mx-auto"
               id="exampleInputPassword1">
        <div id="passwordHelp" class="font-weight-bold">
            <div class="text-danger ">
                <c:if test="${temp.emailError == true}">
                    <p>
                        <fmt:message key="login.password.info"/>
                    </p>
                </c:if>
            </div>
        </div>
    </div>

    <button type="submit" class="btn btn-primary align-center"><fmt:message key="login.submit"/></button>
</form>

</body>
</html>
