<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
    <ctg:info-time/>
    <hr/>
    <form name="registrationForm" method="POST" action="controller">
        <input type="hidden" name="command" value="registration" />
        <br/>Login:<br/>
        <label>
            <input type="text" name="login" value=""/>
        </label>
        <br/>Password:<br/>
        <label>
            <input type="password" name="password" value=""/>
        </label>
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="submit" value="Login"/>
    </form>
    </body>
</html>
