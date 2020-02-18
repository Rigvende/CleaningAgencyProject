<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.registration"/></title>
    </head>

    <body>
    <div style="background: #E0E0E0; height: 100px; padding: 5px;">
        <div style="float: left">
            <h1> <u><fmt:message key="text.company"/></u> </h1>
            <h4><fmt:message key="text.company2"/></h4>
        </div>

        <div style="float: right; padding: 10px; text-align: right;">
            <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
            <div style="margin-right: 82px">
                <a href="${pageContext.request.contextPath}/controller?command=info"><fmt:message key="button.info"/></a>
            </div>
        </div>
    </div>

    <br/>
    <form name="registrationForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <fmt:message key="text.registration"/>
        <hr/><input type="hidden" name="command" value="registration" />
        <br/>

        <fmt:message key="field.loginreg"/><br/>
        <label>
            <input type="text" name="loginreg" value=""/>
        </label>
        <br/>

        <fmt:message key="field.passwordreg"/><br/>
        <label>
            <input type="password" name="passwordreg" value=""/>
        </label>
        <br/>

        <fmt:message key="field.passwordagain"/><br/>
        <label>
            <input type="password" name="passwordagain" value=""/>
        </label>
        <br/>

        <fmt:message key="field.name"/><br/>
        <label>
            <input type="text" name="firstname" value=""/>
        </label>
        <br/>

        <fmt:message key="field.lastname"/><br/>
        <label>
            <input type="text" name="lastname" value=""/>
        </label>
        <br/>

        <fmt:message key="field.phone"/><br/>
        <label>
            <input type="text" name="phone" value=""/>
        </label>
        <br/>

        <fmt:message key="field.email"/><br/>
        <label>
            <input type="text" name="email" value=""/>
        </label>
        <br/>

        <fmt:message key="field.address"/><br/>
        <label>
            <input type="text" name="address" value=""/>
        </label>

        <div style="color: crimson">${errorRegistrationMessage}</div>
        ${wrongAction}
        ${nullPage}
        <br/><br/>

        <input type="submit" value="<fmt:message key="button.registration"/>"/>
    </form>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>