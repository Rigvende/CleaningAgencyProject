<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div class="blr" style="padding: 30px; background: #E0E0E0; height: 150px; width: auto; font-size:20px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
    <h1> <u><fmt:message key="text.company"/></u> </h1>
    <h5><fmt:message key="text.company2"/></h5>
</div>

    <jsp:include page="/WEB-INF/view/logout.jsp"/>
</div>