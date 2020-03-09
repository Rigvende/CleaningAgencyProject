<%@ page language="java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.order"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>

<jsp:include page="/WEB-INF/view/header.jsp"/>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>
<br/><br/><br/><br/>

<div style="text-align: center; margin-right: 50px; margin-left: 50px">
<p style="font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.order6"/> </p>
<p style="font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.order7"/> </p>
</div>

<form name="changeBurialForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="placeorder" />

    <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px"><b>
        <fmt:message key="text.change1"/>
        <hr/>

        <br/><fmt:message key="field.name1"/><br/>
        <label>
            <input style="width: 250px" type="text" name="firstname" value="${user.name}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>

        <br/><fmt:message key="field.lastname1"/><br/>
        <label>
            <input style="width: 250px" type="text" name="lastname" value="${user.lastname}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>

        <br/><fmt:message key="field.phone1"/><br/>
        <label>
            <input style="width: 250px" type="number" name="phone" value="${user.phone}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>

        <br/><fmt:message key="field.email1"/><br/>
        <label>
            <input style="width: 250px" type="text" name="email" value="${user.email}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>

        <br/><fmt:message key="field.location"/><br/>
        <label>
            <input style="width: 250px" type="text" name="location"
                   value="${not empty client.location ? client.location : '---'}"/>
        </label>
        <br/>

        <br/><fmt:message key="field.relative"/><br/>
        <label>
            <input style="width: 250px" type="text" name="relative"
                   value="${not empty client.relative ? client.relative : '---'}"/>
        </label>
        <br/>

        <br/><fmt:message key="text.order8"/><br/>
        <label>
            <input style="width: 250px" type="number" name="days" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>
    </b>

        <div style="color: crimson">${errorPlaceOrder}</div>
        ${wrongAction}
        ${nullPage}
        <br/><br/><ctg:remove-attr/>

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px" type="submit" value="<fmt:message key="button.makeorder"/>"/>
    </div>
    <br/>
</form>

</body>
</html>
