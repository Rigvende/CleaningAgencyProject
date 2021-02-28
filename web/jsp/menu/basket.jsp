<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.basket"/></title>
        <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    </head>
    <body>
    <c:if test="${empty user}">
        <jsp:forward page="/jsp/login.jsp"/>
    </c:if>
    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div class="little-float">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    </div>

    <div class="little-float">
    <jsp:include page="/WEB-INF/view/backToCatalogue.jsp"/>
    </div><br/><br/><br/><br/>


    <c:if test="${empty basketList}">
    <div class="send-message"><h3> <fmt:message key="text.emptybasket"/></h3></div><br/><br/>
    </c:if>
    <c:if test="${not empty basketList}">
        <form name="deletePositionForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="deleteentity" />
        <input type="hidden" name="entitytype" value="position_basket" />

        <b class="field-mail"><fmt:message key="field.id"/></b>
        <label>
        <input type="text" name="id" value=""
            required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
            oninput="setCustomValidity('')"/>
        </label>

        <input class="menu-action-submit" type="submit" value="<fmt:message key="button.deleteposition"/>"/>
        </form><br/>

        <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.positions3"/><br/>
        <fmt:message key="text.positions4"/><br/>
        <fmt:message key="text.positions5"/>
        </div>

    <div style="text-align: center">
    <h5><u class="positions">
    <fmt:message key="text.positions"/>
    </u></h5>
    </div><br/>

    <div class="little-center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
    <tr class="little-back">
        <th><fmt:message key="field.id"/></th>
        <th><fmt:message key="field.service"/></th>
        <th><fmt:message key="field.cost"/></th>
        <th><fmt:message key="field.discount2"/></th>
    </tr>

    <c:forEach var="basket_position" items="${basketList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
        <td><c:out value="${basket_position.position.id}" /></td>
        <td><c:out value="${basket_position.service.service}" /></td>
        <td><c:out value="${basket_position.service.cost}" /></td>
        <td><c:out value="${basket_position.service.sales}" /></td>
        </tr>
    </c:forEach>
    </table><br/>

        <div class="positions-cost">
        <fmt:message key="text.positions2"/> ${totalCost}
        </div>
    </div><br/>

        <div class="little-center">
        <form name="confirmOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="orderredirect" />
        <input class="menu-submit" type="submit" value="<fmt:message key="button.makeorder"/>"/>
        </form>
        </div><br/>
    </c:if>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>