<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<div class="little-float">
    <jsp:include page="/WEB-INF/view/logout.jsp"/>
</div>

<div class="blr header-text">
    <u class="big-text"><fmt:message key="text.company"/></u><br/>
    <b class="subheader-text"><u><fmt:message key="text.company2"/></u></b><br/><br/>
    <div class="sub-text">
        <b><fmt:message key="text.company3"/></b>
    </div>
</div>