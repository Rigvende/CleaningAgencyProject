<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.change"/></title>
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

<form name="changeOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeorder" />

    <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px"><b>
    <fmt:message key="text.change1"/>
    <hr/>

    <fmt:message key="field.status"/>
        <br/>
    <label>
        <input style="width: 250px" type="text" name="status"
               value="${not empty order.orderStatus ? order.orderStatus : ''}"/>
    </label>
    <br/><br/>

    <fmt:message key="field.idcleaner"/>
        <br/>
    <label>
        <input style="width: 250px" type="text" name="id_cleaner"
               value="${not empty order.idCleaner ? order.idCleaner : ''}"/>
    </label>
    <br/>
    </b>

    <div style="color: crimson">${errorChangeOrderMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/><ctg:remove-attr/>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px" type="submit" value="<fmt:message key="button.changeorder"/>"/>
    </div>
</form>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>