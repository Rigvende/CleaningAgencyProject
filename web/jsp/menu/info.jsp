<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.info"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    <div class="blr" style="padding: 30px; background: #E0E0E0; height: 150px; width: auto; font-size:20px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
    <h1> <u><fmt:message key="text.company"/></u> </h1>
    <h5><fmt:message key="text.company2"/></h5>
    </div>

    <div style="padding: 30px">
    <p><fmt:message key="text.info1"/></p>
    <p><fmt:message key="text.info2"/></p>
    <p><fmt:message key="text.info3"/></p>
    </div>

    <div style="padding: 30px; background: royalblue; font-family: 'Book Antiqua', sans-serif; font-size: 20px">
    <b><fmt:message key="text.info4"/></b>
    </div>

    <ul>
    <li style="background: royalblue; width: max-content"><fmt:message key="text.info5"/></li>
    <br/>
    <fmt:message key="text.info6"/>
    <br/>
    <br/>
    <li style="background: royalblue; width: max-content"><fmt:message key="text.info7"/></li>
    <br/>
    <fmt:message key="text.info8"/>
    <br/>
    <fmt:message key="text.info9"/>
    <br/>
    <br/>
    <li style="background: royalblue; width: max-content"><fmt:message key="text.info10"/></li>
    <br/>
    <fmt:message key="text.info11"/>
    </ul>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>