<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<html>
<head>
    <title>Change Location</title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<form name="changeBurialForm" method="POST" action="controller">
    Change all fields below you want.
    <hr/>
    <input type="hidden" name="command" value="changeburial" />
    <br/>Location:<br/>
    <label>
        <input type="text" name="location" value=""/>
    </label>
    <br/>Relative:<br/>
    <label>
        <input type="text" name="relative" value=""/>
    </label>
    <div style="color: crimson">${errorChangeMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>
    <br/>
    <input type="submit" value="Change location"/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>