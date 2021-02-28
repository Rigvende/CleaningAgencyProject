<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.info"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>

    <div class="blr header-text">
    <u class="big-text"><fmt:message key="text.company"/></u><br/>
    <b class="subheader-text">
    <u><fmt:message key="text.company2"/></u></b><br/><br/>
    <div class="sub-text">
    <b><fmt:message key="text.company3"/></b>
    </div>
    </div>

    <div class="info-text">
    <p><fmt:message key="text.info1"/></p>
    <p><fmt:message key="text.info"/></p>
    <p><fmt:message key="text.info3"/></p>
    </div>

    <div class="info">
    <b><fmt:message key="text.info4"/></b>
    </div>

    <ul class="up-link">
    <li class="info-content"><fmt:message key="text.info5"/></li><br/>
    <fmt:message key="text.info6"/><br/><br/>

    <li class="info-content">
    <fmt:message key="text.info7"/></li><br/>
    <fmt:message key="text.info8"/><br/>
    <fmt:message key="text.info9"/><br/><br/>

    <li class="info-content"><fmt:message key="text.info12"/></li><br/>
    <fmt:message key="text.info13"/><br/><br/>

    <li class="info-content"><fmt:message key="text.info10"/></li><br/>
    <fmt:message key="text.info11"/>
    </ul>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>