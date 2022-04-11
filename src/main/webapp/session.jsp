<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Session</title>
</head>

<jsp:include page="header.jsp"/>
<body class="text-center" style="background-color: #7cb5bd">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <img class="bd-placeholder-img" width="400" height="500"
                 src="images/img${currentSession.film.filmId}.jpg">
        </div>
        <div class="col-md-4" id="parent">

            <h3 class="mb-0 text-center"><fmt:formatDate type="both"
                                                         dateStyle="short" timeStyle="short"
                                                         value="${currentSession.dateTime}"/></h3>
            <h4>${currentSession.film.title} ${currentSession.film.releaseYear}</h4>
            <p class="card-text mb-auto"><h6><fmt:message
                key="index.session.director"/> ${currentSession.film.producer}</h6>
            <p class="card-text mb-auto"><h6><fmt:message
                key="index.session.genre"/> ${currentSession.film.genre}</h6>
            <p class="card-text mb-auto"><h6><fmt:message key="index.session.price"/> ${currentSession.film.price}
            <fmt:message key="index.session.UAH"/> ${hallCapacity}</h6>
            <fmt:message key="index.freePlaces"/> ${maxHallCapacity - currentSession.numberOfTickets}
            <br>


            <button onclick="window.location='controller?command=sessionPage&sessionId=${currentSession.sessionId}&tickets=' + tickets"
                    class="btn-primary" id="buy"><fmt:message key="session.submit"/></button>
            <div class="text-danger ">
                <c:if test="${temp.errorLogInSession == true}">
                    <p>
                        <fmt:message key="session.login.error"/>
                    </p>
                </c:if>
                <c:if test="${temp.errorBalance == true}">
                    <p>
                        <fmt:message key="session.balance.error"/>
                    </p>
                </c:if>
                <c:if test="${temp.operationError == true}">
                    <p>
                        <fmt:message key="session.operation.error"/>
                    </p>
                </c:if>
            </div>

        </div>
        <c:if test="${user.role == 'admin'}">
        <div class="col-md-4">

            <a class="btn btn-danger" href="${pageContext.request.contextPath}/controller?command=removeSession"
               role="button"><fmt:message key="session.remove.button"/></a>

        </div>
        </c:if>



        <script>
            var tickets = [];
            let status = false;


            function start() {
                let places = document.createElement('div');
                let parent = document.querySelector('#parent');
                let buy = document.getElementById('buy');
                places.classList.add('p-3');
                places.innerHTML = "<h1> <fmt:message key="session.buy.tickets"/> </h1>";
                var user = "${user.userId}";
                var mapObj = {
                <c:forEach items="${temp.places}" var="item" varStatus="loop">
                ${item.key}:
                '${item.value}'
                ${not loop.last ? ',' : ''}
                </c:forEach>
            }
                ;

                parent.insertBefore(places, buy);
                for (let i = 0; i <= 15; i += 5) {
                    for (let j = 1; j <= 5; j++) {
                        const button = document.createElement('button');
                        button.style.height = '30px';
                        button.style.width = '30px';
                        button.textContent = "" + (j + i);
                        button.id = "place" + (j + i);
                        button.setAttribute("onclick", "changeValue(place" + (j + i) + ");");

                        var tic = mapObj[(j + i)];
                        console.log(tic);
                        if (tic !== ""
                        ) {
                            if (tic === user) {
                                button.classList.add('btn-success');
                            }
                            button.disabled = true;

                        } else {
                            button.classList.add('btn-primary');
                        }
                        console.log(button.getAttribute('aria-disabled'));
                        places.appendChild(button);
                    }
                    let br = document.createElement('br');
                    places.appendChild(br);
                }
                document.getElementById("color2").style.background = '#000000';
            }

            function changeValue(id) {

                var elementClasses = document.getElementById(id);
                console.log(id.classList.contains('btn-primary'));
                if (id.classList.contains('btn-primary')) {
                    id.classList.remove('btn-primary');
                    id.classList.add('btn-warning');
                    tickets.push(id.id);
                    console.log(tickets);
                } else {
                    id.classList.remove('btn-warning');
                    id.classList.add('btn-primary');
                    removeA(tickets, id.id);
                    console.log(tickets);
                }

            }

            window.onload = start;

            function removeA(arr) {
                var what, a = arguments, L = a.length, ax;
                while (L > 1 && arr.length) {
                    what = a[--L];
                    while ((ax = arr.indexOf(what)) !== -1) {
                        arr.splice(ax, 1);
                    }
                }
                return arr;
            }
        </script>
    </div>

</div>
</body>
</html>
