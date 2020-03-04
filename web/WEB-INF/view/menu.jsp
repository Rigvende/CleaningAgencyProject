<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div style="padding: 5px;">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 30px">
    <form name="profileForm" method="get" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="profile" />

        <input style="color: sienna; font-family: 'Book Antiqua',Serif; width: 150px; font-size: 18px;
            height: 30px" type="submit" value="<fmt:message key="button.profile"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 30px">
    <form name="catalogueForm" method="get" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="catalogue" />

        <input style="color: sienna; font-family: 'Book Antiqua',Serif; width: 150px; font-size: 18px;
            height: 30px" type="submit" value="<fmt:message key="button.catalogue"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 30px">
    <form name="basketForm" method="get" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="basket" />

        <input style="color: sienna; font-family: 'Book Antiqua',Serif; width: 150px; font-size: 18px;
            height: 30px" type="submit" value="<fmt:message key="button.basket"/>"/>
    </form>
    </div>

    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-left: 30px">
    <form name="infoForm" method="get" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="info" />

        <input style="color: sienna; font-family: 'Book Antiqua',Serif; width: 150px; font-size: 18px;
            height: 30px" type="submit" value="<fmt:message key="button.info"/>"/>
    </form>
    </div>
</div>