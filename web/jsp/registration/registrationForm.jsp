<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.registration"/></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
    </head>

    <body>

    <div style="float: right; padding: 10px; text-align: right;">
        <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

        <div style="margin-right: 40px">
            <a style="font-size: 20px; font-family: 'Book Antiqua',serif" href="${pageContext.request.contextPath}/"><fmt:message key="link.login"/></a>
        </div>
    </div>

    <div class="blr" style="padding: 30px; background: #E0E0E0; height: 140px; width: auto; font-size:18px;
    font-family:'Papyrus', cursive; color: darkgoldenrod; margin-right:150px; min-width: 1920px">
        <u style="font-size: 50px"><fmt:message key="text.company"/></u>
        <br/>
        <b style="font-family: 'Palatino Linotype',serif; margin-left: 145px">
            <u><fmt:message key="text.company2"/></u></b>
        <br/><br/>
        <div style="text-align: left; margin-left: 30px; font-size: 15px; font-family: 'Palatino Linotype',sans-serif">
            <b><fmt:message key="text.company3"/></b>
        </div>
    </div>

    <form name="registrationForm" method="POST" action="${pageContext.request.contextPath}/controller">
       <input type="hidden" name="command" value="registration" />

        <div style="float: right; padding: 10px; text-align: right;">
            <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
            <div style="margin-right: 75px">
                <a style="font-size: 20px; font-family: 'Book Antiqua',serif"
                   href="${pageContext.request.contextPath}/controller?command=info">
                    <fmt:message key="button.info"/>
                </a>
            </div>
        </div><br/><br/><br/>

        <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif;
        font-size: 18px;"><b>
            <br/><div style="padding: 30px; background: #4f90ff; color: #0d3e4b">
            <fmt:message key="text.registration"/></div>

            <div style="color: crimson; font-size: 14px">${errorRegistrationMessage}</div>
            ${wrongAction}
            ${nullPage}
            <ctg:remove-attr/>

            <br/><fmt:message key="field.loginreg"/>
            <br/>
            <label>
            <input style="width: 250px" type="text" name="loginreg" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label>
            <br/><br/>

            <fmt:message key="field.passwordreg"/>
            <br/>
            <label>
            <input style="width: 250px" type="password" name="passwordreg" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label>
            <br/><br/>

            <fmt:message key="field.passwordagain"/>
            <br/>
            <label>
            <input style="width: 250px" type="password" name="passwordagain" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label>
            <br/><br/>

            <fmt:message key="field.name"/>
            <br/>
            <label>
            <input style="width: 250px" type="text" name="firstname" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label>
            <br/><br/>

            <fmt:message key="field.lastname"/>
            <br/>
            <label>
            <input style="width: 250px" type="text" name="lastname" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label>
            <br/><br/>

            <fmt:message key="field.phone"/>
            <br/>
            <label>
            <input style="width: 250px" type="text" name="phone" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label>
            <br/><br/>

            <fmt:message key="field.email"/>
            <br/>
            <label>
            <input style="width: 250px" type="text" name="email" value=""
                   required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
                   oninput="setCustomValidity('')"/>
            </label>
            <br/><br/>

            <fmt:message key="field.address"/>
            <br/>
            <label>
            <input style="width: 250px" type="text" name="address" value=""/>
            </label>
            <br/><br/></b>

            <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 20px;
            height: 40px" type="submit" value="<fmt:message key="button.registration"/>"/></div>
    </form>
    <br/>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>