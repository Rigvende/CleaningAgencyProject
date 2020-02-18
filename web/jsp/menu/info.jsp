<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.info"/></title>
</head>

<body>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: left">
        <h1> <u><fmt:message key="text.company"/> </u> </h1>
        <h4><fmt:message key="text.company2"/></h4>
    </div>

    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>

<p><fmt:message key="text.info1"/></p>
<p><fmt:message key="text.info2"/></p>
<p><i><fmt:message key="text.info3"/></i></p>
<br/>

<b><fmt:message key="text.info4"/></b>
<br/>
    <fmt:message key="text.info5"/>
    <br/>
    <fmt:message key="text.info6"/>
    <br/>
    <fmt:message key="text.info7"/>
    <br/>
    <fmt:message key="text.info8"/>
    <br/>
    <fmt:message key="text.info9"/>
    <br/>
    <fmt:message key="text.info10"/>
    <br/>
    <fmt:message key="text.info11"/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>