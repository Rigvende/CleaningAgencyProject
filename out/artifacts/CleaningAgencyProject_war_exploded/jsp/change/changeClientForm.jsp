<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.changeclient"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/><br/>

<form name="changeClientForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeclient" />

    <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px"><b>
    <fmt:message key="text.change1"/>
    <hr/>

    <br/><fmt:message key="field.discount"/><br/>
    <label>
        <input style="width: 250px"  type="text" name="discount" value="${not empty client.discount ? client.discount : '0.00'}"/>
    </label>
        <br/>

    <br/><fmt:message key="field.notes"/><br/>
    <label>
        <input style="width: 250px" type="text" name="notes" value="${client.notes}"/>
    </label>
    </b>

    <div style="color: crimson">${errorChangeClientMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/><%request.getSession(true); session.removeAttribute("errorChangeClientMessage");%>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px" type="submit" value="<fmt:message key="button.changeclient"/>"/>
    </div>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>