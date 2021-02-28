<%@ page language="java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.mail"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<div class="little-float">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div><br/><br/>
<c:if test="${ not empty formerguest }">

<form name="mailForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="mail" />
    <table class="little-margin-left">
        <tr>
            <td class="field-mail">
                <fmt:message key="field.sendto"/>
            </td>
            <td><label>
                <input class="big-width" type ="text" name="to" value="${formerguest.email}"
                       required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                       oninput="setCustomValidity('')"/>
            </label></td>
        </tr>

        <tr>
            <td class="field-mail">
                <fmt:message key="field.subject"/>
            </td>
            <td><label>
                <input class="big-width" type ="text" name="subject" value="<fmt:message key="text.mail"/>"
                       required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                       oninput="setCustomValidity('')"/>
            </label></td>
        </tr>
    </table><hr/>

    <label>
        <textarea class="little-margin-left" name="body" rows="5" cols="100">
            <fmt:message key="text.mail2"/>${formerguest.name}!
            <fmt:message key="text.mail3"/>
            <fmt:message key="text.mail4"/>${formerguest.login}
            <fmt:message key="text.mail5"/>${formerguest.password}

            <fmt:message key="text.mail6"/>
            <fmt:message key="text.info7"/>
            <fmt:message key="text.info8"/>
            <fmt:message key="text.info9"/>
        </textarea>
    </label><br/><br/>

    <div class="little-error">${errorMail}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/><ctg:remove-attr/>
    <input class="action-submit" type="submit" value="<fmt:message key="button.send"/>"/>
</form>
</c:if>

<c:if test="${ not empty orderDone }">
    <form name="mailForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="mail" />
        <table class="little-margin-left">
            <tr>
                <td class="field-mail">
                    <fmt:message key="field.sendto"/>
                </td>
                <td><label>
                    <input class="big-width" type ="text" name="to" value="${orderDone.email}"/>
                </label></td>
            </tr>

            <tr>
                <td class="field-mail">
                    <fmt:message key="field.subject"/>
                </td>
                <td><label>
                    <input class="big-width" type ="text" name="subject" value="<fmt:message key="text.mail9"/>"/>
                </label></td>
            </tr>
        </table><hr/>

        <label>
        <textarea class="little-margin-left" name="body" rows="5" cols="100">
            <fmt:message key="text.mail7"/>${orderDone.name}!
            <fmt:message key="text.mail8"/>

            <fmt:message key="text.mail6"/>
            <fmt:message key="text.info7"/>
            <fmt:message key="text.info8"/>
            <fmt:message key="text.info9"/>
        </textarea>
        </label><br/><br/>

        <div class="little-error">${errorMail}</div>
            ${wrongAction}
            ${nullPage}
        <br/><br/><ctg:remove-attr/>
        <input class="action-submit" type="submit" value="<fmt:message key="button.send"/>"/>
    </form>
</c:if>
<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>