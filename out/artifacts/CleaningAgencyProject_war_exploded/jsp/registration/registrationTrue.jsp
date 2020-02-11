<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration Info Page</title>
</head>

<body>
<div style="background: #E0E0E0; height: 100px; padding: 5px;">
    <div style="float: left">
        <h1> <u>Rest-in-Cleanliness</u> </h1>
        <h4>Cleaning Agency</h4>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

        <div style="margin-right: 50px">
            <a href="${pageContext.request.contextPath}/">Back to login</a>
        </div>
    </div>
</div>

<p>Thank you for registration, ${newuser.name}</p>
<p>You can enter in system after your registration will be confirmed.
    It can take some time. Check your e-mail for confirmation and finishing registration.</p>
<p>Have a good day!</p>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>