<jsp:directive.page contentType="text/html; charset=Utf-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>User</title>
</head>
<jsp:include page="header.jsp"/>
<body class="text-center" class="p-3 mb-2 bg-secondary text-white text-align: center" style="background-color: #7cb5bd">
<h3><fmt:message key="user.title.profile"/></h3>
<p><fmt:message key="user.firstName"/>: ${user.firstName}</p>
<p><fmt:message key="user.lastName"/>: ${user.lastName}</p>
<p><fmt:message key="user.email"/>: ${user.email}</p>
<p><fmt:message key="user.balance"/> ${user.balance}</p>
<h3><fmt:message key="user.replenish"/></h3>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="updateBalance"/>
    <input type="number" name="sum" value="" style="width: 22rem" class="form-control mx-auto"
           id="addMoney">
    <div class="text-danger">
        <c:if test="${temp.attributes.errorUpdateSum == true}">
            <p>
                <fmt:message key="user.error.replenish"/>
            </p>
        </c:if>
    </div>
    <button type="submit" class="btn btn-primary align-center"><fmt:message key="user.submit"/></button>
</form>
</body>
</html>
