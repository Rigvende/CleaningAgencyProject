<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.change"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/><br/>

<form name="changeForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeuser" />

    <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px"><b>
        <fmt:message key="text.change1"/>
    <hr/>

    <fmt:message key="field.name1"/><br/>
    <label>
        <input style="width: 250px" type="text" name="firstname" value="${user.name}"/>
    </label>
        <br/>

    <br/><fmt:message key="field.lastname1"/><br/>
    <label>
        <input style="width: 250px" type="text" name="lastname" value="${user.lastname}"/>
    </label>
        <br/>

    <br/><fmt:message key="field.phone1"/><br/>
    <label>
        <input style="width: 250px" type="number" name="phone" value="${user.phone}"/>
    </label>
        <br/>

    <br/><fmt:message key="field.email1"/><br/>
    <label>
        <input style="width: 250px" type="text" name="email" value="${user.email}"/>
    </label>
        <br/>

    <br/><fmt:message key="field.address"/><br/>
    <label>
        <input style="width: 250px" type="text" name="address" value="${user.address}"/>
    </label>
    </b>

    <div style="color: crimson">${errorChangeUserMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><ctg:remove-attr/>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px" type="submit" value="<fmt:message key="button.change"/>"/>
    </div>
    <br/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>