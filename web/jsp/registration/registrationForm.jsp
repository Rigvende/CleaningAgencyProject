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
        Fill the registration form below. All fields marked by * are necessary.
        <hr/>
        <input type="hidden" name="command" value="registration" />
        <br/>Login (from 5 to 15 symbols; only latin symbols, _ or digits)*:<br/>
        <label>
            <input type="text" name="login" value=""/>
        </label>
        <br/>Password (from 5 to 15 symbols; only latin symbols, _ or digits)*:<br/>
        <label>
            <input type="password" name="password" value=""/>
        </label>
        <br/>Repeat password*:<br/>
        <label>
            <input type="password" name="password_repeat" value=""/>
        </label>
        <br/>Name*:<br/>
        <label>
            <input type="text" name="name" value=""/>
        </label>
        <br/>Lastname*:<br/>
        <label>
            <input type="text" name="lastname" value=""/>
        </label>
        <br/>Phone*:<br/>
        <label>
            <input type="number" name="phone" value=""/>
        </label>
        <br/>Address:<br/>
        <label>
            <input type="text" name="address" value=""/>
        </label>
        <br/>E-mail:<br/>
        <label>
            <input type="text" name="email" value=""/>
        </label>
        <br/>
        ${errorRegistrationMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="submit" value="Register"/>
    </form>
    </body>
</html>