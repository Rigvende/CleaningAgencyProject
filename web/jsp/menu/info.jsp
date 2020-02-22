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

    <div class="blr" style="padding: 30px; background: #E0E0E0; height: 140px; width: auto; font-size:18px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
    <u style="font-size: 50px"><fmt:message key="text.company"/></u>
    <br/>
    <b style="font-family: 'Palatino Linotype',serif; margin-left: 145px">
    <u><fmt:message key="text.company2"/></u></b>
    <br/><br/>
    <div style="text-align: left; margin-left: 30px; font-size: 15px; font-family: 'Palatino Linotype',sans-serif">
    <b><fmt:message key="text.company3"/></b>
    </div>
    </div>

    <div style="text-align: center; margin-left: 100px; margin-right: 100px; font-family: 'Palatino Linotype', sans-serif; font-size: 18px">
    <p><fmt:message key="text.info1"/></p>
    <p><fmt:message key="text.info"/></p>
    <p><fmt:message key="text.info3"/></p>
    </div>

    <div style="padding: 30px; background: royalblue; font-family: 'Book Antiqua', sans-serif; font-size: 20px">
    <b><fmt:message key="text.info4"/></b>
    </div>

    <ul style="font-size: 18px; font-family: 'Book Antiqua', serif">
    <li style="background: royalblue; width: max-content"><fmt:message key="text.info5"/></li>
    <br/>
    <fmt:message key="text.info6"/>
    <br/><br>
    <li style="background: royalblue; width: max-content"><fmt:message key="text.info5"/></li>
    <br/>
    <fmt:message key="text.info6"/>
    <br/><br/>
    <li style="background: royalblue; width: max-content">
    <fmt:message key="text.info7"/></li>
    <br/>
    <fmt:message key="text.info8"/>
    <br/>
    <fmt:message key="text.info9"/>
    <br/>
    <br/>
    <li style="background: royalblue; width: max-content"><fmt:message key="text.info12"/></li>
    <br/>
    <fmt:message key="text.info13"/>
    <br/>
    <br/>
    <li style="background: royalblue; width: max-content"><fmt:message key="text.info10"/></li>
    <br/>
    <fmt:message key="text.info11"/>

    </ul>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>