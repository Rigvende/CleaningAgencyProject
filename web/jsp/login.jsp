<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<c:set var="locale"
       value="${not empty locale ? pageContext.session.getAttribute('locale') : 'ru_RU'}"
       scope="session"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.login"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<div class="up-link-float">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200px">
    <div class="up-link-margin">
        <a class="up-link" href="${pageContext.request.contextPath}/controller?command=info">
            <fmt:message key="button.info"/>
        </a>
    </div>
</div>

<div class="blr header-text">
    <u class="big-text"><fmt:message key="text.company"/></u><br/>
    <b class="subheader-text"><u><fmt:message key="text.company2"/></u></b><br/><br/>
    <div class="sub-text"><b><fmt:message key="text.company3"/></b></div>
</div>

<div class="reg-btn">
    <form name="registration" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="regredirect"/>
        <input class="reg-submit" type="submit" value="<fmt:message key="button.regproposal"/>"/>
    </form>
</div>
<div class="little-right">
    <jsp:include page="/WEB-INF/view/locale.jsp"/>
</div><br/><br/><br/><br/><br/><b>

    <p class="login-info"><fmt:message key="text.info2"/></p>
    <p class="login-info"><fmt:message key="text.info22"/></p></b><hr/><br/>

<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <div id="log">
        <input type="hidden" name="command" value="login"/>
        <div class="positions"><fmt:message key="field.login"/></div>
        <label>
            <input type="text" name="login" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>

        <div class="positions"><fmt:message key="field.password"/></div>
        <label>
            <input type="password" name="password" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>

        <div class="little-error little-font"> ${errorLoginPassMessage}</div>
        ${wrongAction}
        ${nullPage}
        <br/><ctg:remove-attr/>
        <input class="menu-action-submit" type="submit" value="<fmt:message key="button.login"/>"/>
    </div>
</form>
<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>