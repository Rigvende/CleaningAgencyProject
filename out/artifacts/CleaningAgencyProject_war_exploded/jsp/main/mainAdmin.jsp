<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html><head>
    <title>Main Page Admin</title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<h2 style="color: olivedrab">Welcome, ${user.name}!</h2>
<h3>What do you want to do now?</h3>
<br/>
<%--    добавить форму посмотреть заказы с инфой о клиенте, вывести юзеров, вывсести всех клинеров, вывести всех клиентов,
отправить сообщение, вставить страничку редактирования инфы (удалить, изменить), форму поменять роль --%>

<jsp:include page="/WEB-INF/view/menu.jsp"/>
<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>