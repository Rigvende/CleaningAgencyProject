<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.confirm"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>

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

    <div style="text-align: center">
    <p style="font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.order"/> ${orderNew.id}</p>
    <p style="font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.order2"/></p>
    <p style="font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.order3"/></p>
    <p style="font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.order4"/></p>
    <p style="font-size: 16px; font-family: 'Palatino Linotype', sans-serif"><fmt:message key="text.order5"/></p>

    <form name="addOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="addorder" />

        <div style="text-align: right">
            <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
            height: 40px; margin-right: 15px" type="submit" value="<fmt:message key="button.ok"/>"/>
        </div>
    </form>
    </div>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>