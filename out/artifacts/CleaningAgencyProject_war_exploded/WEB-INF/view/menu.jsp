<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<div class="little-padding">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div class="little-margin">
    <form name="profileForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="profile" />
        <input class="menu-text" type="submit" value="<fmt:message key="button.profile"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div class="little-margin">
    <form name="catalogueForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="catalogue" />
        <input class="menu-text" type="submit" value="<fmt:message key="button.catalogue"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div class="little-margin">
    <form name="basketForm" method="get" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="basket" />
        <input class="menu-text" type="submit" value="<fmt:message key="button.basket"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div class="little-margin">
    <form name="infoForm" method="get" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="info" />
        <input class="menu-text" type="submit" value="<fmt:message key="button.info"/>"/>
    </form>
    </div>
</div>