<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Cinema Main</title>
</head>
<jsp:include page="header.jsp"/>

<body style="background-color: #7cb5bd">

<a href="#" class="btn btn-primary my-2"><fmt:message key="index.sort.datetime"/></a>
<a href="#" class="btn btn-primary my-2 "><fmt:message key="index.sort.name"/></a>
<a href="#" class="btn btn-primary my-2"><fmt:message key="index.sort.places"/></a>
<div class="row mb-2">
    <c:if test="${mainSession1 != null}">
        <div class="col-md-6">
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col-auto d-none d-lg-block">
                    <img class="bd-placeholder-img" width="200" height="250"
                         src="images/img${mainSession1.film.filmId}.jpg">
                </div>
                <div class="col p-4 d-flex flex-column position-static">
                    <a href="controller?command=sessionPage&sessionId=${mainSession1.sessionId}" class="stretched-link">
                        <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                     dateStyle="short" timeStyle="short"
                                                                     value="${mainSession1.dateTime}"/></h3></a>
                    <h4>${mainSession1.film.title} ${mainSession1.film.releaseYear}</h4>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.director"/> ${mainSession1.film.producer}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.genre"/> ${mainSession1.film.genre}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainSession1.film.price}
                    <fmt:message key="index.session.UAH"/></h6>

                </div>

            </div>
        </div>
    </c:if>
    <c:if test="${mainSession2 != null}">
    <div class="col-md-6">
        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col-auto d-none d-lg-block">
                <img class="bd-placeholder-img" width="200" height="250"
                     src="images/img${mainSession2.film.filmId}.jpg">
            </div>
            <div class="col p-4 d-flex flex-column position-static">
                <a href="controller?command=sessionPage&sessionId=${mainSession2.sessionId}" class="stretched-link">
                    <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                 dateStyle="short" timeStyle="short"
                                                                 value="${mainSession2.dateTime}"/></h3></a>
                <h4>${mainSession2.film.title} ${mainSession2.film.releaseYear}</h4>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.director"/> ${mainSession2.film.producer}</h6>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.genre"/> ${mainSession2.film.genre}</h6>
                <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainSession2.film.price}
                <fmt:message key="index.session.UAH"/></h6>

            </div>

        </div>
        </c:if>
    </div>

    <c:if test="${mainSession3 != null}">
        <div class="col-md-6">
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col-auto d-none d-lg-block">
                    <img class="bd-placeholder-img" width="200" height="250"
                         src="images/img${mainSession3.film.filmId}.jpg">
                </div>
                <div class="col p-4 d-flex flex-column position-static">
                    <a href="controller?command=sessionPage&sessionId=${mainSession3.sessionId}" class="stretched-link">
                        <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                     dateStyle="short" timeStyle="short"
                                                                     value="${mainSession3.dateTime}"/></h3></a>
                    <h4>${mainSession3.film.title} ${mainSession3.film.releaseYear}</h4>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.director"/> ${mainSession3.film.producer}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.genre"/> ${mainSession3.film.genre}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainSession3.film.price}
                    <fmt:message key="index.session.UAH"/></h6>

                </div>

            </div>
        </div>
    </c:if>
    <c:if test="${mainSession4 != null}">
    <div class="col-md-6">
        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col-auto d-none d-lg-block">
                <img class="bd-placeholder-img" width="200" height="250"
                     src="images/img${mainSession4.film.filmId}.jpg">
            </div>
            <div class="col p-4 d-flex flex-column position-static">
                <a href="controller?command=sessionPage&sessionId=${mainSession4.sessionId}" class="stretched-link">
                    <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                 dateStyle="short" timeStyle="short"
                                                                 value="${mainSession4.dateTime}"/></h3></a>
                <h4>${mainSession4.film.title} ${mainSession4.film.releaseYear}</h4>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.director"/> ${mainSession4.film.producer}</h6>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.genre"/> ${mainSession4.film.genre}</h6>
                <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainSession4.film.price}
                <fmt:message key="index.session.UAH"/></h6>

            </div>

        </div>
        </c:if>
    </div>
</div>
<div class="text-center">
    <form name="paginationForm" method="GET" action="controller">
        <button type="submit"  name="mainPage" value="${temp.mainPreviousPage}" class="btn btn-primary align-center">
            <fmt:message key="login.submit"/></button>
    </form>
</div>
<div class="text-center">
    <c:if test="${temp.mainNextPage == null || temp.mainNextPage > 5}">
        <a href="controller?mainPage=${temp.mainPreviousPage}" class="btn btn-primary my-2">&#8592;<fmt:message
                key="index.navigation.back"/></a>
    </c:if>
    <c:if test="${temp.mainNextPage != null}">
        <a href="controller?mainPage=${temp.mainNextPage}" class="btn btn-primary my-2"><fmt:message
                key="index.navigation.next"/>&#8594;</a>
    </c:if>
</div>

</body>
</html>





