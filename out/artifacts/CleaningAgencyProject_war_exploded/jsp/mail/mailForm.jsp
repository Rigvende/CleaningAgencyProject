<%@ page language="java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.mail"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>
<br/>
<br/>

<form name="mailForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="mail" />

    <table>
        <tr>
            <td><fmt:message key="field.sendto"/></td>
            <td><label>
                <input type ="text" name="to" value="${formerguest.email}"/>
            </label></td>
        </tr>

        <tr>
            <td><fmt:message key="field.subject"/></td>
            <td><label>
                <input type ="text" name="subject" value="<fmt:message key="text.mail"/>"/>
            </label></td>
        </tr>
    </table>
    <hr/>

    <label>
        <textarea name="body" rows="5" cols="100"><fmt:message key="text.mail2"/>${formerguest.name}!
<fmt:message key="text.mail3"/>
<fmt:message key="text.mail4"/>${formerguest.login}
<fmt:message key="text.mail5"/>${formerguest.password}

            <fmt:message key="text.mail6"/>
        </textarea>
    </label>
    <br/><br/>
    <input type="submit" value="<fmt:message key="button.send"/>"/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>