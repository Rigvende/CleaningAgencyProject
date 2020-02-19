<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.change"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<form name="addServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <h3><fmt:message key="text.add"/></h3>
    <input type="hidden" name="command" value="addentity" />

    <fmt:message key="field.service"/>
    <br/>
    <label>
        <input type="text" name="servicechange" value=""/>
    </label>
    <br/>

    <fmt:message key="field.cost"/>
    <br/>
    <label>
        <input type="text" name="cost" value=""/>
    </label>
    <br/>

    <fmt:message key="field.discount2"/>
    <br/>
    <label>
        <input type="text" name="discount" value=""/>
    </label>

    <div style="color: crimson">${errorAddServiceMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/>

    <input type="submit" value="<fmt:message key="button.addservice"/>"/>
</form>
<br/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>