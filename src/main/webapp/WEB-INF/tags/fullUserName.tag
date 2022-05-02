<%@ attribute name="firstName" required="true" type="java.lang.String" description="First name "%>
<%@ attribute name="lastName" required="true" type="java.lang.String" description="Last name "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<span>${firstName} ${lastName}</span>
