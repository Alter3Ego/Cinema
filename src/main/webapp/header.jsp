<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="ex" uri="WEB-INF/custom.tld" %>--%>
<%--<%@ taglib prefix="ex" uri="custom.tld" %>--%>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">

<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg  navbar-light" style="background-color: #E7DB0BFF">
        <div class="container-xl">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="controller">MyCINEMA</a>
                    </li>
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit"><fmt:message
                                key="header.search"/></button>
                    </form>
                </ul>
                <ul class="d-flex align-items-end navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <c:if test="${user.userId == null}">
                                <p><fmt:message key="header.greetings.anon"/></p>
                            </c:if>
                            <c:if test="${user.userId != null}">
                                <p><fmt:message key="header.greetings.user"/> ${user.firstName}</p>
                            </c:if>
                        </a>
                    </li>
                    <c:if test="${user.userId != null}">
                        <li class="nav-item">
                            <a class="dropdown-item" href="controller?command=logOut"><fmt:message key="header.signOut"/></a>
                        </li>
                    </c:if>
                    <c:if test="${user.userId == null}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                               aria-expanded="false"><fmt:message key="header.signIn"/></a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="login.jsp"><fmt:message key="header.logIn"/></a>
                                </li>
                                <li><a class="dropdown-item" href="signUp.jsp"><fmt:message key="header.signUp"/></a></li>
                            </ul>
                        </li>
                    </c:if>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="header.current.language"/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" <fmt:setLocale value="en_EN" scope="session"/>
                                   href="${pageContext.request.contextPath}?sessionLocale=en">EN</a></li>
                            <li><a class="dropdown-item" <fmt:setLocale value="ua_UA" scope="session"/>
                                   href="${pageContext.request.contextPath}?sessionLocale=ua">UA</a></li>

                        </ul>
                    </li>

                </ul>
            </div>
        </div>
    </nav>
</header>
</body>
</html>
