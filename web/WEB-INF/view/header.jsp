<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/logout.jsp"/>
</div>

<div class="blr" style="padding: 30px; background: #E0E0E0; height: 140px; width: auto; font-size:18px;
    font-family:'Papyrus', cursive; color: darkgoldenrod;">
    <u style="font-size: 50px"><fmt:message key="text.company"/></u>
    <br/>
    <b style="font-family: 'Palatino Linotype',serif; margin-left: 120px"><u><fmt:message key="text.company2"/></u></b>
    <br/><br/>
    <div style="text-align: left; margin-left: 30px; font-size: 15px; font-family: 'Palatino Linotype',sans-serif">
        <b><fmt:message key="text.company3"/></b>
    </div>
</div>

</div>