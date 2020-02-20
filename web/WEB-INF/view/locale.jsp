<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div style="float: right; padding: 10px; text-align: right;">
        <form name="localeForm" method="get" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="locale" />
                <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif;
                       font-size: 14px;  height: 30px"
                       type="submit" name = "language" value="<fmt:message key="button.locale"/>"/>
        </form>
</div>