<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>500</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
Oops. Server has problems. Try to reload page or wait.
<br/>

</div>
<img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
<div style="margin-left: 30px">

    <form name="backForm" method="get" action="controller">
        <input type="hidden" name="command" value="backtomain"/>
        ${errorLogoutMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Back to Main Page"/>
    </form>

</div>
<br/>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>