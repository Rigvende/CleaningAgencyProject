<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html><head>
    <title><fmt:message key="title.mainadmin"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<h2 style="color: olivedrab"><fmt:message key="text.main1"/> ${user.name}!</h2>
<h3><fmt:message key="text.main2"/></h3>
<br/>
<%--    добавить форму посмотреть заказы с инфой о клиенте, вывести юзеров, вывсести всех клинеров, вывести всех клиентов,
отправить сообщение, вставить страничку редактирования инфы (удалить, изменить), форму поменять роль --%>

<jsp:include page="/WEB-INF/view/menu.jsp"/>
<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>