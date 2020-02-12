<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div style="padding: 5px;">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 70px">
    <form name="profileForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="profile" />
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="<fmt:message key="button.profile"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 70px">
    <form name="catalogueForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="catalogue" />
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="<fmt:message key="button.catalogue"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 70px">
    <form name="basketForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="basket" />
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="<fmt:message key="button.basket"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 77px">
    <form name="infoForm" method="get" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="info" />
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="<fmt:message key="button.info"/>"/>
    </form>
</div>
</div>