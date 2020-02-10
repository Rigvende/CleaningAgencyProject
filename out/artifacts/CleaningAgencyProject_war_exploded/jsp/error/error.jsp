<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Error Page</title>
</head>
    <body>
    <jsp:include page="/jsp/header.jsp"/>
        Request from ${pageContext.errorData.requestURI} is failed
        <br/>
        Servlet name : ${pageContext.errorData.servletName}
        <br/>
        Status code : ${pageContext.errorData.statusCode}
        <br/>
        Exception : ${pageContext.exception}
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
    <jsp:include page="/jsp/footer.jsp"/>
    </body>
</html>