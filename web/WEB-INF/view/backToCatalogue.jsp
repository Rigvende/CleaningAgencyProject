<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div style="float: right; padding: 10px; text-align: right;">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-right: 10px">
        <a style="font-size: 20px; font-family: 'Book Antiqua',serif"
           href="${pageContext.request.contextPath}/controller?command=backtocatalogue">
            <fmt:message key="link.catalogue"/>
        </a>
    </div>
</div>