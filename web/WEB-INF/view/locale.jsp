<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div style="float: right; padding: 10px; text-align: right;">

        <form name="localeForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="locale" />
<%--                <input type="hidden" name="language" value="${locale}" />--%>
                <input type="submit" name = "language" value="<fmt:message key="button.locale"/>"/>
        </form>

</div>