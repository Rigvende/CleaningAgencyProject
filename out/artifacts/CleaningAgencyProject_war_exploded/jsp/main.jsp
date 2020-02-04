<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html><head><title>Welcome</title></head>
<body>
    <h3>Welcome</h3>
    <hr/>
        <ctg:info-time/>
    <hr/>
        Hello! What do you want to do now?
    <hr/>
    <a href="controller?command=profile">Profile</a>
    <a href="controller?command=catalogue">Catalogue</a>
    <a href="controller?command=info">Info</a>
    <hr/>
    <a href="controller?command=logout">Logout</a>
</body>
</html>