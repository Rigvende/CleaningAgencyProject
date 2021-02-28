<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.registration"/></title>
        <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    </head>
    <body>
    <div class="up-link-float">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
        <div class="little-right">
            <a class="up-link" href="${pageContext.request.contextPath}/"><fmt:message key="link.login"/></a>
        </div>
    </div>

    <div class="blr header-text">
        <u class="big-text"><fmt:message key="text.company"/></u><br/>
        <b class="subheader-text">
            <u><fmt:message key="text.company2"/></u></b>
        <br/><br/>
        <div class="sub-text">
            <b><fmt:message key="text.company3"/></b>
        </div>
    </div>

    <form name="registrationForm" method="POST" action="${pageContext.request.contextPath}/controller">
       <input type="hidden" name="command" value="registration" />
        <div class="up-link-float">
            <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
            <div class="up-link-margin">
                <a class="up-link" href="${pageContext.request.contextPath}/controller?command=info">
                    <fmt:message key="button.info"/>
                </a>
            </div>
        </div><br/><br/><br/>

        <div class="action-header little-right"><b>
            <br/><div class="reg-header">
            <fmt:message key="text.registration"/></div>
            <div class="little-error little-text">${errorRegistrationMessage}</div>
            ${wrongAction}
            ${nullPage}
            <ctg:remove-attr/>
            <br/><fmt:message key="field.loginreg"/><br/>
            <label>
            <input class="little-width" type="text" name="loginreg" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label><br/><br/>
            <fmt:message key="field.passwordreg"/><br/>
            <label>
            <input class="little-width" type="password" name="passwordreg" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label><br/><br/>
            <fmt:message key="field.passwordagain"/><br/>
            <label>
            <input class="little-width" type="password" name="passwordagain" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label><br/><br/>
            <fmt:message key="field.name"/><br/>
            <label>
            <input class="little-width" type="text" name="firstname" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label><br/><br/>
            <fmt:message key="field.lastname"/><br/>
            <label>
            <input class="little-width" type="text" name="lastname" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label><br/><br/>
            <fmt:message key="field.phone"/><br/>
            <label>
            <input class="little-width" type="text" name="phone" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label><br/><br/>
            <fmt:message key="field.email"/><br/>
            <label>
            <input class="little-width" type="text" name="email" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label><br/><br/>
            <fmt:message key="field.address"/><br/>
            <label>
            <input class="little-width" type="text" name="address" value=""/>
            </label><br/><br/></b>
            <input class="action-submit" type="submit" value="<fmt:message key="button.registration"/>"/></div>
    </form><br/>
    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>