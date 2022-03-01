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
<div>
<a href="controller?mainPage=1&sort=dateTime" class="btn btn-primary my-2"><fmt:message key="index.sort.datetime"/></a>
<a href="controller?mainPage=1&sort=name" class="btn btn-primary my-2 "><fmt:message key="index.sort.name"/></a>
<a href="controller?mainPage=1&sort=places" class="btn btn-primary my-2"><fmt:message key="index.sort.places"/></a>

<a class="nav-link dropdown-toggle" href="#" id="Admin" role="button"
   data-bs-toggle="dropdown" aria-expanded="false">
<c:if test="${ limitPlaces==false }">
    <fmt:message key="index.allSession"/>
</c:if>
    <c:if test="${limitPlaces==true}">
        <fmt:message key="index.limitSession"/>
    </c:if>
</a>
<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
    <li><a class="dropdown-item"
           href="controller?mainPage=1&limitPlaces=true"><fmt:message key="index.limitSession"/></a></li>
    <li><a class="dropdown-item"
           href="controller?mainPage=1&limitPlaces=false"><fmt:message key="index.allSession"/></a></li>

</ul>
</div>

<c:if test="${mainCell1 == null}">
    <h1 class="text-center">

        <fmt:message key="index.sort.sorry"/>

    </h1>
</c:if>
<div class="row mb-2">
    <c:if test="${mainCell1 != null}">
        <div class="col-md-6">
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col-auto d-none d-lg-block">
                    <img class="bd-placeholder-img" width="200" height="250"
                         src="images/img${mainCell1.film.filmId}.jpg">
                </div>
                <div class="col p-4 d-flex flex-column position-static">
                    <a href="controller?command=sessionPage&sessionId=${mainCell1.sessionId}" class="stretched-link">
                        <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                     dateStyle="short" timeStyle="short"
                                                                     value="${mainCell1.dateTime}"/></h3></a>
                    <h4>${mainCell1.film.title} ${mainCell1.film.releaseYear}</h4>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.director"/> ${mainCell1.film.producer}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.genre"/> ${mainCell1.film.genre}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainCell1.film.price}
                    <fmt:message key="index.session.UAH"/>  ${hallCapacity}</h6>
                    <fmt:message key="index.freePlaces"/> ${maxHallCapacity - mainCell1.numberOfTickets}

                </div>

            </div>
        </div>
    </c:if>
    <c:if test="${mainCell2 != null}">
    <div class="col-md-6">
        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col-auto d-none d-lg-block">
                <img class="bd-placeholder-img" width="200" height="250"
                     src="images/img${mainCell2.film.filmId}.jpg">
            </div>
            <div class="col p-4 d-flex flex-column position-static">
                <a href="controller?command=sessionPage&sessionId=${mainCell2.sessionId}" class="stretched-link">
                    <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                 dateStyle="short" timeStyle="short"
                                                                 value="${mainCell2.dateTime}"/></h3></a>
                <h4>${mainCell2.film.title} ${mainCell2.film.releaseYear}</h4>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.director"/> ${mainCell2.film.producer}</h6>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.genre"/> ${mainCell2.film.genre}</h6>
                <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainCell2.film.price}
                <fmt:message key="index.session.UAH"/></h6>
                <fmt:message key="index.freePlaces"/> ${maxHallCapacity - mainCell2.numberOfTickets}

            </div>

        </div>
        </c:if>
    </div>

    <c:if test="${mainCell3 != null}">
        <div class="col-md-6">
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col-auto d-none d-lg-block">
                    <img class="bd-placeholder-img" width="200" height="250"
                         src="images/img${mainCell3.film.filmId}.jpg">
                </div>
                <div class="col p-4 d-flex flex-column position-static">
                    <a href="controller?command=sessionPage&sessionId=${mainCell3.sessionId}" class="stretched-link">
                        <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                     dateStyle="short" timeStyle="short"
                                                                     value="${mainCell3.dateTime}"/></h3></a>
                    <h4>${mainCell3.film.title} ${mainCell3.film.releaseYear}</h4>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.director"/> ${mainCell3.film.producer}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message
                        key="index.session.genre"/> ${mainCell3.film.genre}</h6>
                    <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainCell3.film.price}
                    <fmt:message key="index.session.UAH"/></h6>
                    <fmt:message key="index.freePlaces"/> ${maxHallCapacity - mainCell3.numberOfTickets}
                </div>

            </div>
        </div>
    </c:if>
    <c:if test="${mainCell4 != null}">
    <div class="col-md-6">
        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div class="col-auto d-none d-lg-block">
                <img class="bd-placeholder-img" width="200" height="250"
                     src="images/img${mainCell4.film.filmId}.jpg">
            </div>
            <div class="col p-4 d-flex flex-column position-static">
                <a href="controller?command=sessionPage&sessionId=${mainCell4.sessionId}" class="stretched-link">
                    <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                                 dateStyle="short" timeStyle="short"
                                                                 value="${mainCell4.dateTime}"/></h3></a>
                <h4>${mainCell4.film.title} ${mainCell4.film.releaseYear}</h4>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.director"/> ${mainCell4.film.producer}</h6>
                <p class="card-text mb-auto"><h6><fmt:message
                    key="index.session.genre"/> ${mainCell4.film.genre}</h6>
                <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${mainCell4.film.price}
                <fmt:message key="index.session.UAH"/></h6>
                <fmt:message key="index.freePlaces"/> ${maxHallCapacity - mainCell4.numberOfTickets}
            </div>
        </div>
        </c:if>
    </div>
</div>
<div class="text-center">
    <c:if test="${mainCell1 != null}">
        <c:if test="${temp.mainNextPage == null || temp.mainNextPage > 5 }">
            <a href="controller?mainPage=${temp.mainPreviousPage}" class="btn btn-primary my-2">&#8592;<fmt:message
                    key="index.navigation.back"/></a>
        </c:if>
    </c:if>
    <c:if test="${temp.mainNextPage != null}">
        <a href="controller?mainPage=${temp.mainNextPage}" class="btn btn-primary my-2"><fmt:message
                key="index.navigation.next"/>&#8594;</a>
    </c:if>
</div>

</body>
</html>





