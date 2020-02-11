<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
</head>

<body>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">

    <div style="float: left">
        <h1> <u>Rest-in-Cleanliness</u> </h1>
        <h4>Cleaning Agency</h4>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
        <div style="margin-right: 85px">
    <a href="controller?command=info">Info</a>
        </div>
    </div>

</div>

<br/>
<form name="registration" method="post" action="controller">
    <input type="hidden" name="command" value="regredirect" />
    <div style="color: crimson">${errorLogoutMessage}</div>
    ${wrongAction}
    ${nullPage}
    <input type="submit" value="Register now!"/>
</form>

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
    <br/>
    <div style="color: crimson">${errorLoginPassMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>
    <input type="submit" value="Login"/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>