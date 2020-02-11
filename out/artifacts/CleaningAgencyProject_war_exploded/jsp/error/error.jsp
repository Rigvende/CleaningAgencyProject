<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Error Page</title>
</head>

<body>
    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>

        <br/>
        <b>Request from</b> ${pageContext.errorData.requestURI} is failed
        <br/>
        <b>Servlet name :</b> ${pageContext.errorData.servletName}
        <br/>
        <b>Status code :</b> ${pageContext.errorData.statusCode}
        <br/>
        <b>Exception :</b> ${pageContext.exception}
        <hr/>
        <br/>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>