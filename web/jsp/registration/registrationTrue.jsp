<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.reginfo"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

        <div style="margin-right: 40px">
            <a style="font-size: 20px; font-family: 'Book Antiqua',serif" href="${pageContext.request.contextPath}/"><fmt:message key="link.login"/></a>
        </div>
    </div>

    <div class="blr" style="padding: 30px; background: #E0E0E0; height: 140px; width: auto; font-size:18px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
        <u style="font-size: 50px"><fmt:message key="text.company"/></u>
        <br/>
        <b style="font-family: 'Palatino Linotype',serif; margin-left: 145px"><fmt:message key="text.company2"/></b>
        <br/><br/>
        <div style="text-align: left; margin-left: 30px; font-size: 15px; font-family: 'Palatino Linotype',sans-serif">
            <fmt:message key="text.company3"/>
        </div>
    </div>


</div>
<br/><br/><br/><br/><br/>
<p style="margin-left: 50px; font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.reginfo1"/> ${newuser.name}</p>
<p style="margin-left: 50px; font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.reginfo2"/></p>
<p style="margin-left: 50px; font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.reginfo3"/></p>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>