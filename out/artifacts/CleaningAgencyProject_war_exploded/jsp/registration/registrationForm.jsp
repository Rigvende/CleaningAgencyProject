<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.registration"/></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
    </head>

    <body>
    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
        <div style="margin-right: 75px">
            <a style="font-size: 20px; font-family: 'Book Antiqua',serif"
               href="${pageContext.request.contextPath}/controller?command=info">
                <fmt:message key="button.info"/>
            </a>
        </div>
    </div>

    <div class="blr" style="padding: 30px; background: #E0E0E0; height: 140px; width: auto; font-size:18px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
        <u style="font-size: 50px"><fmt:message key="text.company"/></u>
        <br/>
        <b style="font-family: 'Palatino Linotype',serif; margin-left: 145px"><fmt:message key="text.company2"/></b>
        <br/><br/>
        <div style="text-align: left; margin-left: 30px; font-size: 15px; font-family: 'Palatino Linotype',sans-serif">
            <fmt:message key="text.company3"/>
        </div>
    </div>

    <h3 style="color: #0c4f5b; float: left; margin-left: 45px"><fmt:message key="text.registration"/></h3>
    <br/><br/><br/><br/><hr/><br/>

    <form name="registrationForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <div style="float: left; margin-left: 45px">
       <input type="hidden" name="command" value="registration" />

            <div style="color: crimson">${errorRegistrationMessage}</div>
            ${wrongAction}
            ${nullPage}

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.loginreg"/>
            </h4>
            <label>
            <input type="text" name="loginreg" value=""/>
            </label>

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.passwordreg"/>
            </h4>
            <label>
            <input type="password" name="passwordreg" value="" />
            </label>

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.passwordagain"/>
            </h4>
            <label>
            <input type="password" name="passwordagain" value=""/>
            </label>

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.name"/>
            </h4>
            <label>
            <input type="text" name="firstname" value=""/>
            </label>

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.lastname"/>
            </h4>
            <label>
            <input type="text" name="lastname" value=""/>
            </label>

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.phone"/>
            </h4>
            <label>
            <input type="text" name="phone" value=""/>
            </label>

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.email"/>
            </h4>
            <label>
            <input type="text" name="email" value=""/>
            </label>

            <h4 style="color: #0c4f5b">
                <fmt:message key="field.address"/>
            </h4>
            <label>
            <input type="text" name="address" value=""/>
            </label>
            <br/><br/><br/>

            <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 20px;
            height: 40px" type="submit" value="<fmt:message key="button.registration"/>"/></div>
    </form>

    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>