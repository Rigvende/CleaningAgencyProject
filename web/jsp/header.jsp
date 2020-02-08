<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: left">
        <h1> <u>Rest-in-Cleanliness</u> </h1>
        <h4>Cleaning Agency</h4>
    </div>
    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
        <div style="margin-right: 70px">

        <form name="logoutForm" method="get" action="controller">
            <input type="hidden" name="command" value="logout" />
            ${errorLogoutMessage}
            ${wrongAction}
            ${nullPage}
            <input type="submit" value="Logout"/>
        </form>

        </div>
    </div>
</div>