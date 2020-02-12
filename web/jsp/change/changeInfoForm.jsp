<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>

<html>
<head>
    <title>Change Info</title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<br/>
<form name="changeForm" method="POST" action="${pageContext.request.contextPath}/controller">
    Change all fields below you want.
    <hr/>
    <input type="hidden" name="command" value="changeinfo" />
    <br/>Name:<br/>
    <label>
        <input type="text" name="firstname" value=""/>
    </label>
    <br/>Lastname:<br/>
    <label>
        <input type="password" name="lastname" value=""/>
    </label>
    <br/>Phone:<br/>
    <label>
        <input type="number" name="phone" value=""/>
    </label>
    <br/>E-mail:<br/>
    <label>
        <input type="text" name="email" value=""/>
    </label>
    <br/>Address:<br/>
    <label>
        <input type="text" name="address" value=""/>
    </label>
    <div style="color: crimson">${errorChangeMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>
    <br/>
    <input type="submit" value="Change info"/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>