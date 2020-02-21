<%@ page language="java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.mail"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>
<br/><br/>

<form name="mailForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="mail" />

    <table style="margin-left: 50px">
        <tr>
            <td style="color: #0c4f5b; font-size: 18px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
                <fmt:message key="field.sendto"/>
            </td>
            <td><label>
                <input type ="text" name="to" value="${formerguest.email}"/>
            </label></td>
        </tr>

        <tr>
            <td style="color: #0c4f5b; font-size: 18px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
                <fmt:message key="field.subject"/>
            </td>
            <td><label>
                <input type ="text" name="subject" value="<fmt:message key="text.mail"/>"/>
            </label></td>
        </tr>
    </table>
    <hr/>

    <label>
        <textarea style="margin-left: 50px" name="body" rows="5" cols="100"><fmt:message key="text.mail2"/>${formerguest.name}!
<fmt:message key="text.mail3"/>
<fmt:message key="text.mail4"/>${formerguest.login}
<fmt:message key="text.mail5"/>${formerguest.password}

            <fmt:message key="text.mail6"/>
            <br/>
            <fmt:message key="text.info7"/>
            <br/>
            <fmt:message key="text.info8"/>
            <br/>
            <fmt:message key="text.info9"/>
        </textarea>
    </label>
    <br/><br/>

    <div style="color: crimson">${errorMail}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/><ctg:remove-attr/>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px; margin-left: 50px" type="submit" value="<fmt:message key="button.send"/>"/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>