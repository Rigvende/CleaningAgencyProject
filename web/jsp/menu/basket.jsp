<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.basket"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
    </head>

    <body>
    <c:if test="${empty user}">
        <jsp:forward page="/jsp/login.jsp"/>
    </c:if>

    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    </div>

    <div style="float: right">
    <jsp:include page="/WEB-INF/view/backToCatalogue.jsp"/>
    </div>
    <br/><br/><br/><br/>


    <c:if test="${empty basketList}">
    <div style="color: #0c4f5b; margin-left: 50px"><h3> <fmt:message key="text.emptybasket"/></h3></div>
    <br/><br/>
    </c:if>

    <c:if test="${not empty basketList}">

        <form name="deletePositionForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="deleteentity" />
        <input type="hidden" name="entitytype" value="position_basket" />

        <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.id"/>
        </b>
        <label>
        <input type="text" name="id" value=""/>
        </label>

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
        height: 25px;" type="submit" value="<fmt:message key="button.deleteposition"/>"/>
        </form>
        <br/>

        <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.positions3"/>
        <br/>
        <fmt:message key="text.positions4"/>
        <br/>
        <fmt:message key="text.positions5"/>
        </div>

    <div style="text-align: center">
    <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
    <fmt:message key="text.positions"/>
    </u></h5>
    </div>
    <br/>

    <div style="text-align: center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
    <tr style="background-color: royalblue">
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
    </table>
    <br/>

        <div style="color: #0c4f5b; font-size: 20px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.positions2"/> ${totalCost}
        </div>
    </div>
    <br/>

        <div style="text-align: center">
        <form name="confirmOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="orderredirect" />

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
        height: 40px;" type="submit" value="<fmt:message key="button.makeorder"/>"/>
        </form>
        </div>
        <br/>
    </c:if>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>