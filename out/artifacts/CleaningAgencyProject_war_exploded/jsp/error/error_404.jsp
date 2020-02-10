<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>404</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
Sorry, page does not found.
<br/>

</div>
<img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
<div style="margin-left: 60px">

    <form name="backForm" method="get" action="controller">
        <input type="hidden" name="command" value="backtomain"/>
        ${errorLogoutMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Back to Main Page"/>
    </form>

</div>
<br/>
<br/>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>