<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.change"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/><br/>

<form name="addServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="addentity" />

    <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px"><b>
    <fmt:message key="text.add"/>
        <hr/>

    <fmt:message key="field.service"/>
    <br/>
    <label>
        <input style="width: 250px" type="text" name="servicechange" value=""/>
    </label>
    <br/><br/>

    <fmt:message key="field.cost"/>
    <br/>
    <label>
        <input style="width: 250px" type="text" name="cost" value=""/>
    </label>
    <br/><br/>

    <fmt:message key="field.discount2"/>
    <br/>
    <label>
        <input style="width: 250px" type="text" name="discount" value=""/>
    </label>
    </b>

    <div style="color: crimson">${errorAddServiceMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/><%request.getSession(true); session.removeAttribute("errorAddServiceMessage");%>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px" type="submit" value="<fmt:message key="button.addservice"/>"/>
    </div>
</form>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>