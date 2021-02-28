<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<div class="up-link-float">
        <form name="localeForm" method="get" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="locale" />
                <input class="locale-text" type="submit" name = "language" value="<fmt:message key="button.locale"/>"/>
        </form>
</div>