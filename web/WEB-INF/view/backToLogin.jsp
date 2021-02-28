<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<div class="up-link-float">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div class="little-right">
        <a class="up-link" href="${pageContext.request.contextPath}/">
            <fmt:message key="link.login"/>
        </a>
    </div>
</div>