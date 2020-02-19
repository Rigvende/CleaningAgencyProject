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
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
    <div style="float: right; padding: 10px; text-align: right;">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div style="margin-right: 82px">
        <a href="${pageContext.request.contextPath}/controller?command=info"><fmt:message key="button.info"/></a>
    </div>
    </div>

    <div class="blr" style="padding: 30px; background: #E0E0E0; height: 150px; width: auto; font-size:20px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
        <h1> <u><fmt:message key="text.company"/></u> </h1>
        <h5><fmt:message key="text.company2"/></h5>
    </div>

    <div style="float: right"><jsp:include page="/WEB-INF/view/locale.jsp"/></div>

    <div style="float: left; padding: 30px">
<form name="registration" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="regredirect" />
    <input type="submit" value="<fmt:message key="button.regproposal"/>"/>
    <br/>
</form>
    </div>
<br/><br/><br/><br/><br/><hr/><br/>


<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login" />
    <div style="margin-left: 50px">
    <br/><fmt:message key="field.login"/><br/>
    <label>
        <input type="text" name="login" value=""/>
    </label>
    <br/>

    <fmt:message key="field.password"/><br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <br/>

    <div style="color: crimson">${errorLoginPassMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>

    <input type="submit" value="<fmt:message key="button.login"/>"/>
    </div>
</form>


<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>