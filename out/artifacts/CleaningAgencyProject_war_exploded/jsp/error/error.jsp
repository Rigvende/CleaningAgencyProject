<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html><title>Error Page</title>
    <body>
        <ctg:info-time/>
        <hr/>
        Request from ${pageContext.errorData.requestURI} is failed
        <br/>
        Servlet name : ${pageContext.errorData.servletName}
        <br/>
        Status code : ${pageContext.errorData.statusCode}
        <br/>
        Exception: ${pageContext.exception}
        <br/>
        Message from exception: ${pageContext.exception.message}
        <br/>
        <hr/>
    </body>
</html>