<%--<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>

<%--<fmt:setBundle basename="message"/>--%>

<%--<div style="float: right; padding: 10px; text-align: right;">--%>

<%--        <form name="localeForm" method="post" action="${pageContext.request.contextPath}/controller">--%>
<%--                <input type="hidden" name="command" value="locale" />--%>
<%--                <input type="submit" name = "language" value="<fmt:message key="button.locale"/>"/>--%>
<%--        </form>--%>

<%--</div>--%>

<%--    <br/>--%>
<%--    <br/>--%>
<%--    <br/>--%>
<%--    <br/>--%>
<%--    <jsp:include page="/WEB-INF/view/locale.jsp"/>--%>

<%-------------------------------%>
<%--<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>

<%--<c:set var="language"--%>
<%--value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"--%>
<%--scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--<fmt:setBundle basename="message" />--%>

<%--<html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title><fmt:message key="title.login"/></title>--%>
<%--</head>--%>

<%--<body>--%>
<%--<form>--%>
<%--    <select id="language" name="language" onchange="submit()">--%>
<%--        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>--%>
<%--        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>--%>
<%--    </select>--%>

<%--<div style="background: #E0E0E0; height: 100px; padding: 5px;">--%>

<%--    <div style="float: left">--%>
<%--        <h1> <u><fmt:message key="text.company"/></u> </h1>--%>
<%--        <h4><fmt:message key="text.company2"/></h4>--%>
<%--    </div>--%>

<%--    <div style="float: right; padding: 10px; text-align: right;">--%>
<%--        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">--%>
<%--        <div style="margin-right: 85px">--%>
<%--            <a href="controller?command=info"><fmt:message key="button.info"/></a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<br/>--%>
<%--<form name="registration" method="post" action="${pageContext.request.contextPath}/controller">--%>
<%--    <input type="hidden" name="command" value="regredirect" />--%>
<%--    <div style="color: crimson">${errorLogoutMessage}</div>--%>
<%--    ${wrongAction}--%>
<%--    ${nullPage}--%>
<%--    <input type="submit" value="<fmt:message key="button.regproposal"/>"/>--%>
<%--</form>--%>

<%--<hr/>--%>
<%--<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">--%>
<%--    <input type="hidden" name="command" value="login" />--%>
<%--    <br/><fmt:message key="field.login"/><br/>--%>
<%--    <label>--%>
<%--        <input type="text" name="login" value=""/>--%>
<%--    </label>--%>
<%--    <br/><fmt:message key="field.password"/><br/>--%>
<%--    <label>--%>
<%--        <input type="password" name="password" value=""/>--%>
<%--    </label>--%>
<%--    <br/>--%>
<%--    <div style="color: crimson">${errorLoginPassMessage}</div>--%>
<%--    ${wrongAction}--%>
<%--    ${nullPage}--%>
<%--    <br/>--%>
<%--    <input type="submit" value="<fmt:message key="button.login"/>"/>--%>
<%--</form>--%>

<%--<jsp:include page="/WEB-INF/view/footer.jsp"/>--%>
<%--</body>--%>
<%--</html>--%>