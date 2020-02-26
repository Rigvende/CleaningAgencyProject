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
    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    </div>

    <div style="float: right">
    <jsp:include page="/WEB-INF/view/backToCatalogue.jsp"/>
    </div>
    <br/><br/>
    <br/><br/>


    <c:set var="admins" scope="session" value="${basketList}"/>
    <c:set var="totalCount" scope="session" value="${basketList.size()}"/>
    <c:set var="perPage" scope="session" value="${10}"/>
    <c:set var="pageStart" value="${param.start}"/>

    <c:if test="${empty pageStart or pageStart < 0}">
        <c:set var="pageStart" value="0"/>
    </c:if>

    <c:if test="${totalCount < pageStart}">
        <c:set var="pageStart" value="${pageStart - perPage}"/>
    </c:if>


    <c:if test="${empty basketList}">
    <div style="color: #0c4f5b; margin-left: 50px"><h3> <fmt:message key="text.emptybasket"/></h3></div>
    <br/><br/>
    </c:if>

    <c:if test="${not empty basketList}">

        <form name="deletePositionForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="deleteentity" />
        <input type="hidden" name="entitytype" value="position" />

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

        <form name="confirmOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="makeorder" />

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
        height: 25px;" type="submit" value="<fmt:message key="button.makeorder"/>"/>
        </form>
        <br/>

        <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.positions3"/>
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
    </tr>

    <c:forEach var="position" items="${basketList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
        <td><c:out value="${position.idService}" /></td>
        <td><c:out value="${position.service}" /></td>
        </tr>
    </c:forEach>
    </table>
    <br/>

        <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.positions2"/><%--        totalCost--%>
        </div>

    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </div>
    <br/>
    </c:if>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>