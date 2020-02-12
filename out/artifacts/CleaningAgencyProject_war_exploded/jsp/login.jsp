<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="locale"
       value="${not empty param.language ? param.language : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="message"/>

<html lang="${locale}">
<head>
    <title><fmt:message key="title.login"/></title>
</head>

<body>
<form>
    <label for="locale"></label>
    <select id="locale" name="locale" onchange="submit()">
        <option value="en" ${locale == 'en' ? 'selected' : ''}>Русский</option>
        <option value="ru" ${locale == 'ru' ? 'selected' : ''}>English</option>
    </select>
</form>


<div style="background: #E0E0E0; height: 100px; padding: 5px;">

    <div style="float: left">
        <h1> <u><fmt:message key="text.company"/></u> </h1>
        <h4><fmt:message key="text.company2"/></h4>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
        <div style="margin-right: 85px">
            <a href="controller?command=info"><fmt:message key="button.info"/></a>
        </div>
    </div>

    <br/>
    <br/>
    <br/>
    <br/>
    <jsp:include page="/WEB-INF/view/locale.jsp"/>
</div>

<br/>
<form name="registration" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="regredirect" />
    <div style="color: crimson">${errorLogoutMessage}</div>
    ${wrongAction}
    ${nullPage}
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