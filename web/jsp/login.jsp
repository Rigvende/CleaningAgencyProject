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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/align.css"/>"/>
</head>

<body>
<div style="float: right; padding: 10px; text-align: right;">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200px">
    <div style="margin-right: 80px">
        <a style="font-size: 20px; font-family: 'Book Antiqua',serif;"
           href="${pageContext.request.contextPath}/controller?command=info">
            <fmt:message key="button.info"/>
        </a>
    </div>
</div>

<div class="blr" style="padding: 30px; background: #E0E0E0; height: 150px; width: auto; font-size:18px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
    <u style="font-size: 50px"><fmt:message key="text.company"/></u>
    <br/>
    <b style="font-family: 'Palatino Linotype',serif; margin-left: 145px"><u><fmt:message key="text.company2"/></u></b>
    <br/><br/>
    <div style="text-align: left; margin-left: 30px; font-size: 15px; font-family: 'Palatino Linotype',sans-serif">
        <b><fmt:message key="text.company3"/></b>
    </div>
</div>

<div style="float: left; padding: 30px">
    <form name="registration" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="regredirect"/>
        <input style="color: #0c4f5b; font-family: 'Monotype Corsiva', sans-serif; width: 200px;
            font-size: 24px; background-color: lemonchiffon; height: 50px;"
               type="submit" value="<fmt:message key="button.regproposal"/>"/>
    </form>
</div>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/locale.jsp"/>
</div>

<br/><br/><br/><br/><br/><b>
    <p style="text-align: right; margin-right: 20px; font-family: 'Palatino Linotype',cursive; font-size: 18px">
        <fmt:message key="text.info2"/>
    </p>
    <p style="text-align: right; margin-right: 20px; font-family: 'Palatino Linotype',cursive; font-size: 18px">
        <fmt:message key="text.info22"/>
    </p></b>
<hr/>
<br/>

<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <div id="log">
        <input type="hidden" name="command" value="login"/>

        <div style="color: #0c4f5b; font-family: 'Palatino Linotype',serif; font-size: 18px;"><fmt:message
                key="field.login"/></div>
        <label>
            <input type="text" name="login" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>

        <div style="color: #0c4f5b; font-family: 'Palatino Linotype',serif; font-size: 18px;"><fmt:message
                key="field.password"/></div>
        <label>
            <input type="password" name="password" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>

        <div style="color: crimson; font-family: 'Palatino Linotype', sans-serif">${errorLoginPassMessage}</div>
        ${wrongAction}
        ${nullPage}
        <br/><ctg:remove-attr/>

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 170px;
            font-size: 18px;  height: 30px" type="submit" value="<fmt:message key="button.login"/>"/>
    </div>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>