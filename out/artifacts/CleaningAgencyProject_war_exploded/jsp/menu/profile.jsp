<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Your Profile</title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<br/>
<div style="color: olivedrab">
    <b>PERSONAL INFO</b>
</div>

<hr/>
<b>Role:</b> ${user.role}
<br/>
<b>Name:</b> ${user.name}
<br/>
<b>Lastname:</b> ${user.lastname}
<br/>
<b>Phone:</b> ${user.phone}
<br/>
<b>E-mail:</b> ${user.email}
<br/>
<b>Address:</b> ${user.address}
<br/>

<br/>
<form name="changeProfile" method="post" action="controller">
    <input type="hidden" name="command" value="changeredirect"/>
    <div style="color: crimson">${errorLogoutMessage}</div>
    ${wrongAction}
    ${nullPage}
    <input type="submit" value="Change info"/>
</form>

<c:if test="${ not empty user and user.role eq 'client' }">
    <b>Personal discount:</b> ${client.discount}
    <br/>
    <hr/>
    <br/>
    <b>Burial location:</b> ${client.location}
    <br/>
    <b>Relative's name:</b> ${client.relative}
    <br/>
    <br/>
    <form name="changeBurial" method="post" action="controller">
        <input type="hidden" name="command" value="changeburialredirect"/>
        <div style="color: crimson">${errorLogoutMessage}</div>
            ${wrongAction}
            ${nullPage}
        <input type="submit" value="Change location"/>
    </form>
</c:if>

<c:if test="${ not empty user and user.role eq 'cleaner' }">
    <b>Commission:</b> ${cleaner.commission}
    <br/>
</c:if>
<br/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/>
<br/>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>