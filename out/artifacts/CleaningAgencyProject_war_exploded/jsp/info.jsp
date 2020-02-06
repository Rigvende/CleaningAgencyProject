<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Info Page</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<p>We are glad that you have chosen our agency.</p>
<p>The company is engaged in cleaning and restoration of burial places of your friends and relatives.</p>
<p><i>You can get acquainted with our services and prices in the "Catalogue" section.</i></p>
<br/>
<b>Our contact information:</b>
<br/>
[Legal address]:
<br/>
Minsk, Lenin St., Building 1.
<br/>
[Phones]:
<br/>
8 (017) 220-00-00
<br/>
8 (029) 222-00-00
<br/>
[License for the implementation of cleaning services]:
<br/>
# 123456789 issued by the Ministry of Justice of the Republic of Belarus 01-01-2020
<jsp:include page="/jsp/footer.jsp"/>
<img src="${pageContext.request.contextPath}/data/fon.jpg" alt="back">
</body>
</html>