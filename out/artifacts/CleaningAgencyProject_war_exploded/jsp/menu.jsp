<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding: 5px;">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div style="margin-left: 70px">

    <form name="profileForm" method="get" action="controller">
        <input type="hidden" name="command" value="profile" />
        ${errorLogoutMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Profile"/>
    </form>

    </div>
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div style="margin-left: 60px">

    <form name="catalogueForm" method="get" action="controller">
        <input type="hidden" name="command" value="catalogue" />
        ${errorLogoutMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Catalogue"/>
    </form>

    </div>
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div style="margin-left: 70px">

    <form name="basketForm" method="get" action="controller">
        <input type="hidden" name="command" value="basket" />
        ${errorLogoutMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Basket"/>
    </form>

    </div>
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
    <div style="margin-left: 80px">

    <form name="infoForm" method="get" action="controller">
        <input type="hidden" name="command" value="info" />
        ${errorLogoutMessage}
        ${wrongAction}
        ${nullPage}
        <input type="submit" value="Info"/>
    </form>

</div>
</div>