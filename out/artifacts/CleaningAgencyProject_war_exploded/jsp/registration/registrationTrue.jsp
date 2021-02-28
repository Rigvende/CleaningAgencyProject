<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.reginfo"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>

<body>
<div class="header-smth">
    <div class="up-link-float">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
        <div class="little-right">
            <a class="up-link" href="${pageContext.request.contextPath}/"><fmt:message key="link.login"/></a>
        </div>
    </div>

    <div class="blr header-text">
        <u class="big-text"><fmt:message key="text.company"/></u><br/>
        <b class="subheader-text"><fmt:message key="text.company2"/></b><br/><br/>
        <div class="sub-text">
            <fmt:message key="text.company3"/>
        </div>
    </div>
</div><br/><br/><br/><br/><br/>

<p class="little-font little-margin-left"><fmt:message key="text.reginfo1"/> ${newuser.name}</p>
<p class="little-font little-margin-left"><fmt:message key="text.reginfo2"/></p>
<p class="little-font little-margin-left"><fmt:message key="text.reginfo3"/></p>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>