<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="locale"
       value="${not empty locale ? pageContext.session.getAttribute('locale') : 'ru_RU'}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.login"/></title>
</head>

<body>

<div style="background: #E0E0E0; height: 100px; padding: 5px;">

    <div style="float: left">
        <h1> <u><fmt:message key="text.company"/></u> </h1>
        <h4><fmt:message key="text.company2"/></h4>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
        <div style="margin-right: 82px">
            <a href="${pageContext.request.contextPath}/controller?command=info"><fmt:message key="button.info"/></a>
        </div>
    </div>
</div>

<div style="float: right"><jsp:include page="/WEB-INF/view/locale.jsp"/></div>

<br/>
<form name="registration" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="regredirect" />
    <input type="submit" value="<fmt:message key="button.regproposal"/>"/>
</form>

<hr/>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login" />
    <br/><fmt:message key="field.login"/><br/>
    <label>
        <input type="text" name="login" value=""/>
    </label>
    <br/><fmt:message key="field.password"/><br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <br/>
    <div style="color: crimson">${errorLoginPassMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="button.login"/>"/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>