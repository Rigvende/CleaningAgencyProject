<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Login</title>
<body>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: left">
        <h1> Rest-in-Cleanliness </h1>
        <h4>Cleaning Agency</h4>
    </div>
</div>
<br/>
<a href="${pageContext.request.contextPath}/jsp/registration/registrationForm.jsp">Register now!</a>
<hr/>
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
    ${errorLoginPassMessage}
    ${wrongAction}
    ${nullPage}
    <input type="submit" value="Login"/>
</form>
<jsp:include page="/jsp/footer.jsp"/>
</body></html>