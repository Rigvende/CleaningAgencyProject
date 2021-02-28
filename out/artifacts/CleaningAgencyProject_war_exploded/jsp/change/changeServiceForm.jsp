<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.change"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<jsp:include page="/WEB-INF/view/backToMain.jsp"/><br/><br/><br/><br/>

<form name="changeServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeservice" />
    <div class="action-header"><b>
    <fmt:message key="text.change1"/><hr/>
    <fmt:message key="field.service"/><br/>
    <label>
        <input class="little-width" type="text" name="servicechange"
               value="${not empty service.service ? service.service : ''}"
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
               oninput="setCustomValidity('')"/>
    </label><br/><br/>

    <fmt:message key="field.cost"/><br/>
    <label>
        <input class="little-width" type="text" name="cost"
               value="${not empty service.cost ? service.cost : '0.00'}"
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
               oninput="setCustomValidity('')"/>
    </label><br/><br/>

    <fmt:message key="field.discount2"/><br/>
    <label>
        <input class="little-width" type="text" name="sales"
               value="${not empty service.sales ? service.sales : '0.00'}"
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
               oninput="setCustomValidity('')"/>
    </label></b>

    <div class="little-error">${errorChangeServiceMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/><ctg:remove-attr/>
    <input class="action-submit" type="submit" value="<fmt:message key="button.changeservice"/>"/>
    </div>
</form><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>