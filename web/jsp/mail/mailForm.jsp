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

<form name="mailForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="mail" />
    <table>
        <tr>
            <td><fmt:message key="field.sendto"/></td>
            <td><label>
                <input type ="text" name="to"/>
            </label></td>
        </tr>
        <tr>
            <td><fmt:message key="field.subject"/></td>
            <td><label>
                <input type ="text" name="subject"/>
            </label></td>
        </tr>
    </table>
    <hr/>
    <label>
        <textarea name="body" rows="5" cols="45"></textarea>
    </label>
    <br/><br/>
    <input type="submit" value="<fmt:message key="button.send"/>"/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>