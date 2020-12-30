<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.orderlist"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>

<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<c:set var="orders" scope="session" value="${orderList}"/>
<c:set var="totalCount" scope="session" value="${orderList.size()}"/>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>

<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>

<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>

<c:if test="${ not empty user and user.role eq 'admin' }">
<form name="changeOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeorderredirect" />

    <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
            <fmt:message key="field.id"/>
    </b>
    <label>
        <input type="text" name="id" value=""/>
    </label>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
            height: 25px; " type="submit" value="<fmt:message key="button.changeorder"/>"/>

    <div style="color: crimson; margin-left: 80px">${errorChangeOrderIdMessage}</div>
    ${wrongAction}
    ${nullPage}
    <ctg:remove-attr/>
</form>
<br/>

    <div style="text-align: center">
        <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
            <fmt:message key="text.orders"/>
        </u></h5>
    </div>

<div style="text-align: center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
        <tr style="background-color: royalblue">
            <th><fmt:message key="field.id"/></th>
            <th><fmt:message key="field.ordertime"/></th>
            <th><fmt:message key="field.deadline"/></th>
            <th><fmt:message key="field.status"/></th>
            <th><fmt:message key="field.mark"/></th>
            <th><fmt:message key="field.isclient"/></th>
            <th><fmt:message key="field.idcleaner2"/></th>
        </tr>

    <c:forEach var="orderlist" items="${orderList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${orderlist.id}" /></td>
            <td><c:out value="${orderlist.orderTime}" /></td>
            <td><c:out value="${orderlist.deadline}" /></td>
            <td><c:out value="${orderlist.orderStatus}" /></td>
            <td><c:out value="${orderlist.mark}" /></td>
            <td><c:out value="${orderlist.idClient}" /></td>
            <td><c:out value="${orderlist.idCleaner}" /></td>
        </tr>
    </c:forEach>
    </table>
    <br/>

    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
</div>
</c:if>

<c:if test="${ not empty user and user.role eq 'client' }">
    <form name="changeOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="setmark" />

        <br/><br/><br/><br/>
        <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.mark"/>
        <br/>
        </div>

        <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.id"/>
        </b><br/>
        <label>
            <input style="margin-left: 50px" type="text" name="id" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>

        <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.mark"/>
        </b><br/>
        <label>
            <input style="margin-left: 50px" type="text" name="mark" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
            height: 25px; margin-left: 50px" type="submit" value="<fmt:message key="button.setmark"/>"/>
    </form>
    <br/>

    <div style="color: crimson; margin-left: 80px">
    ${errorChangeOrderMessage}</div>
    ${wrongAction}
    ${nullPage}
    <ctg:remove-attr/>
    <br/>

    <form name="changeOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="cancel" />

        <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.cancel"/>
        </div>

        <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.id"/>
        </b>
        <label>
            <input type="text" name="id" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
            height: 25px; " type="submit" value="<fmt:message key="button.cancel"/>"/>


    </form>
    <br/>

    <div style="text-align: center">
        <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
            <fmt:message key="text.orders"/>
        </u></h5>
    </div>

    <div style="text-align: center;">
        <table border="1" cellpadding="5" cellspacing="5" align="center">
            <tr style="background-color: royalblue">
                <th><fmt:message key="field.id"/></th>
                <th><fmt:message key="field.ordertime"/></th>
                <th><fmt:message key="field.deadline"/></th>
                <th><fmt:message key="field.status"/></th>
                <th><fmt:message key="field.mark"/></th>
                <th><fmt:message key="field.name1"/></th>
                <th><fmt:message key="field.lastname1"/></th>
                <th><fmt:message key="field.phone1"/></th>
            </tr>

            <c:forEach var="orderlist" items="${orderList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
                <tr>
                    <td><c:out value="${orderlist.order.id}" /></td>
                    <td><c:out value="${orderlist.order.orderTime}" /></td>
                    <td><c:out value="${orderlist.order.deadline}" /></td>
                    <td><c:out value="${orderlist.order.orderStatus}" /></td>
                    <td><c:out value="${orderlist.order.mark}" /></td>
                    <td><c:out value="${orderlist.user.name}" /></td>
                    <td><c:out value="${orderlist.user.lastname}" /></td>
                    <td><c:out value="${orderlist.user.phone}" /></td>
                </tr>
            </c:forEach>
        </table>
        <br/>

        <a href="?start=${pageStart - perPage}"><<</a>
            ${pageStart + 1} - ${pageStart + perPage}
        <a href="?start=${pageStart + perPage}">>></a>
    </div>
</c:if>

<c:if test="${ not empty user and user.role eq 'cleaner' }">
    <form name="changeOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="setnotes" />
        <br/><br/><br/><br/>

        <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="text.notes"/>
        <br/>
        </div>

        <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.id"/>
        </b><br/>
        <label>
            <input style="margin-left: 50px" type="text" name="id" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
        </label>
        <br/>

        <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.notes"/>
        </b><br/>
        <label>
            <input style="margin-left: 50px" type="text" name="notes" value=""/>
        </label>
        <br/><br/>

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 15px;
            height: 25px; margin-left: 50px" type="submit" value="<fmt:message key="button.setnotes"/>"/>

        <div style="color: crimson; margin-left: 80px">${errorChangeClientMessage}</div>
            ${wrongAction}
            ${nullPage}
        <ctg:remove-attr/>
    </form>

    <div style="text-align: center">
        <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
            <fmt:message key="text.orders"/>
        </u></h5>
    </div>

    <div style="text-align: center">
        <table border="1" cellpadding="5" cellspacing="5" align="center">
            <tr style="background-color: royalblue">
                <th><fmt:message key="field.id"/></th>
                <th><fmt:message key="field.ordertime"/></th>
                <th><fmt:message key="field.deadline"/></th>
                <th><fmt:message key="field.status"/></th>
                <th><fmt:message key="field.mark"/></th>
                <th><fmt:message key="field.name1"/></th>
                <th><fmt:message key="field.lastname1"/></th>
                <th><fmt:message key="field.phone1"/></th>
                <th><fmt:message key="field.location"/></th>
                <th><fmt:message key="field.relative"/></th>
                <th><fmt:message key="field.notes"/></th>

            </tr>

            <c:forEach var="orderlist" items="${orderList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
                <tr>
                    <td><c:out value="${orderlist.order.id}" /></td>
                    <td><c:out value="${orderlist.order.orderTime}" /></td>
                    <td><c:out value="${orderlist.order.deadline}" /></td>
                    <td><c:out value="${orderlist.order.orderStatus}" /></td>
                    <td><c:out value="${orderlist.order.mark}" /></td>
                    <td><c:out value="${orderlist.user.name}" /></td>
                    <td><c:out value="${orderlist.user.lastname}" /></td>
                    <td><c:out value="${orderlist.user.phone}" /></td>
                    <td><c:out value="${orderlist.client.location}" /></td>
                    <td><c:out value="${orderlist.client.relative}" /></td>
                    <td><c:out value="${orderlist.client.notes}" /></td>
                </tr>
            </c:forEach>
        </table>
        <br/>

        <a href="?start=${pageStart - perPage}"><<</a>
            ${pageStart + 1} - ${pageStart + perPage}
        <a href="?start=${pageStart + perPage}">>></a>
    </div>
</c:if>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>