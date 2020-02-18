<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.changecleaner"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<form name="changeClientForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <h3><fmt:message key="text.change1"/></h3>
    <hr/><input type="hidden" name="command" value="changecleaner" />

    <br/>
    <fmt:message key="field.commission"/>
    <br/>
    <label>
        <input type="text" name="commission" value="${cleaner.commission}"/>
    </label>

    <br/>
    <fmt:message key="field.notes"/>
    <br/>
    <label>
        <input type="text" name="notes" value="${cleaner.notes}"/>
    </label>

    <div style="color: crimson">${errorChangeMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>
    <br/>

    <input type="submit" value="<fmt:message key="button.changecleaner"/>"/>
</form>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/>
<br/>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>