<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
    <ctg:info-time/>
    <h3>Do you really want to log out?</h3>
    <br/>
    <a href="${pageContext.request.contextPath}/jsp/mainAdmin.jsp">Back to Main Page.</a>
    <form name="logoutForm" method="POST" action="controller">
        <input type="hidden" name="command" value="logout" />
        <br/>
        ${errorLogoutMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <input type="submit" value="Logout"/>
    </form>
</body>
</html>