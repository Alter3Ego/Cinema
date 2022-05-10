<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>addSession</title>
    <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../resources/css/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
</head>

<jsp:include page="../header.jsp"/>
<body class="text-center" style="background-color: #7cb5bd">

<h2><fmt:message key="addSession.title"/></h2>

<form name="addSessionForm" method="POST" action="${pageContext.request.contextPath}/controller">

    <div class="mb-3">
        <label for="dateInput" class="form-label"><h3><fmt:message key="addSession.dateTime.label"/></h3></label>
        <input type="hidden" name="command" value="addSessionAction"/>
        <div style="width: 27rem" class="container">
            <div id="carbon-block"></div>
            <div class="jquery-script-ads"></div>

            <input  id="dateInput" name="dateTime" value="" type="text" class="form-control form_datetime2">

        </div>
    </div>
    <div class="mb-3">
        <div style="width: 27rem" class="form-group  mx-auto">
            <label for="exampleFormControlSelect1"><h3><fmt:message key="addSession.film.label"/></h3></label>
            <select name="filmId" value="" class="form-control" id="exampleFormControlSelect1">
                <c:forEach items="${temp.attributes.sessionAddPage}" var="item">
                    <option value="${item.filmId}">${item.title}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="text-success">
        <c:if test="${temp.attributes.successfulAdd == true}">
            <p>
                <fmt:message key="addSession.successfulAdd"/>
            </p>
        </c:if>
    </div>
    <div class="text-danger">
        <c:if test="${temp.attributes.errorDB == true}">
            <p>
                <fmt:message key="addSession.errorAdding"/>
            </p>
        </c:if>
    </div>



    <button type="submit" class="btn btn-primary align-center"><fmt:message key="addSession.create.button"/></button>

</form>


<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script src="../resources/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript">
    $(".form_datetime2").datetimepicker({
        format: "dd MM yyyy - hh:ii",
        autoclose: true,
        todayBtn: true,
        startDate: "2022-03-28 10:00",
        minuteStep: 10,
    });
    $('#datetimepicker').datetimepicker();
</script>


</body>
</html>
