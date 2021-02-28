<%@ page language="java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.order"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<div class="little-float">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div><br/><br/><br/><br/>

<div class="little-right little-margin-left little-center">
<p class="little-font"><fmt:message key="text.order6"/> </p>
<p class="little-font"><fmt:message key="text.order7"/> </p>
</div>

<form name="changeBurialForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="placeorder" />
    <div class="action-header"><b>
        <fmt:message key="text.change1"/><hr/>
        <br/><fmt:message key="field.name1"/><br/>
        <label>
            <input class="little-width" type="text" name="firstname" value="${user.name}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label><br/>

        <br/><fmt:message key="field.lastname1"/><br/>
        <label>
            <input class="little-width" type="text" name="lastname" value="${user.lastname}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label><br/>

        <br/><fmt:message key="field.phone1"/><br/>
        <label>
            <input class="little-width" type="number" name="phone" value="${user.phone}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label><br/>

        <br/><fmt:message key="field.email1"/><br/>
        <label>
            <input class="little-width" type="text" name="email" value="${user.email}"
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label><br/>

        <br/><fmt:message key="field.location"/><br/>
        <label>
            <input class="little-width" type="text" name="location"
                   value="${not empty client.location ? client.location : '---'}"/>
        </label><br/>

        <br/><fmt:message key="field.relative"/><br/>
        <label>
            <input class="little-width" type="text" name="relative"
                   value="${not empty client.relative ? client.relative : '---'}"/>
        </label><br/>

        <br/><fmt:message key="text.order8"/><br/>
        <label>
            <input class="little-width" type="number" name="days" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label><br/></b>

        <div class="little-error">${errorPlaceOrder}</div>
        ${wrongAction}
        ${nullPage}
        <br/><br/><ctg:remove-attr/>
        <input class="action-submit" type="submit" value="<fmt:message key="button.makeorder"/>"/>
    </div><br/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>