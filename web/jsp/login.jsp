<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Login</title></head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<a href="${pageContext.request.contextPath}/jsp/registration/registrationForm.jsp">Register now!</a>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login" />
    <br/>Login:<br/>
    <label>
        <input type="text" name="login" value=""/>
    </label>
    <br/>Password:<br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="Login"/>
</form>
<jsp:include page="/jsp/footer.jsp"/>
</body></html>