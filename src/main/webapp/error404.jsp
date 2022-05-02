<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Error404</title>
</head>
<body
        style="background-color: #7cb5bd">

<div class="container">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10">
            <p style=" text-align:center; font-size: 10rem">Error 404 </p>
            <h1 class="text-center">

                <a style=" font-size: 5rem" href="${pageContext.request.contextPath}/controller"><fmt:message key="error.return.message"/></a>

            </h1>

        </div>
        <div class="col-lg-1"></div>
    </div>
</div>
</body>
</html>
