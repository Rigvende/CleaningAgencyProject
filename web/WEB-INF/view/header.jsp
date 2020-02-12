<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: left">
        <h1> <u><fmt:message key="text.company"/></u> </h1>
        <h4><fmt:message key="text.company2"/></h4>
    </div>

    <jsp:include page="/WEB-INF/view/logout.jsp"/>
</div>