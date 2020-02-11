<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.reginfo"/></title>
</head>

<body>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: left">
        <h1> <u><fmt:message key="text.company"/></u> </h1>
        <h4><fmt:message key="text.company2"/></h4>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

        <div style="margin-right: 50px">
            <a href="${pageContext.request.contextPath}/"><fmt:message key="link.login"/></a>
        </div>
    </div>
</div>

<p><fmt:message key="text.reginfo1"/> ${newuser.name}</p>
<p><fmt:message key="text.reginfo2"/></p>
<p><fmt:message key="text.reginfo3"/></p>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>