<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head>
    <title>Welcome</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<h2 style="color: olivedrab">Welcome, ${user.name}!</h2>
<h3>Hello! What do you want to do now?</h3>
<br/>
<jsp:include page="/jsp/menu.jsp"/>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>