<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.changecleaner"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>

<jsp:include page="/WEB-INF/view/header.jsp"/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/><br/>

<form name="changeClientForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changecleaner" />

    <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px"><b>
    <fmt:message key="text.change1"/>
    <hr/>

    <br/>
    <fmt:message key="field.commission"/>
    <br/>
    <label>
        <input style="width: 250px" type="text" name="commission" value="${not empty cleaner.commission ? cleaner.commission : '0.00'}"
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')" oninput="setCustomValidity('')"/>
    </label>
        <br/>

    <br/>
    <fmt:message key="field.notes"/>
    <br/>
    <label>
        <input style="width: 250px" type="text" name="notes" value="${cleaner.notes}"/>
    </label>
    </b>

    <div style="color: crimson">${errorChangeCleanerMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><ctg:remove-attr/>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px" type="submit" value="<fmt:message key="button.changecleaner"/>"/>
    </div>
</form>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>