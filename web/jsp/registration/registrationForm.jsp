<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<html>
    <head>
        <title>Registration</title>
    </head>

    <body>
    <div style="background: #E0E0E0; height: 100px; padding: 5px;">
        <div style="float: left">
            <h1> Rest-in-Cleanliness </h1>
            <h4>Cleaning Agency</h4>
        </div>

        <div style="float: right; padding: 10px; text-align: right;">
            <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">
            <div style="margin-right: 85px">
                <a href="${pageContext.request.contextPath}/jsp/info.jsp">Info</a>
            </div>
        </div>
    </div>

    <br/>
    <form name="registrationForm" method="POST" action="controller">
        Fill the registration form below. All fields marked by * are necessary.
        <hr/>
        <input type="hidden" name="command" value="registration" />
        <br/>Login* (from 5 to 15 symbols; only latin symbols, _ or digits):<br/>
        <label>
            <input type="text" name="loginreg" value=""/>
        </label>
        <br/>Password* (from 5 to 15 symbols; only latin symbols, _ or digits):<br/>
        <label>
            <input type="password" name="passwordreg" value=""/>
        </label>
        <br/>Repeat password*:<br/>
        <label>
            <input type="password" name="passwordagain" value=""/>
        </label>
        <br/>Name*:<br/>
        <label>
            <input type="text" name="firstname" value=""/>
        </label>
        <br/>Lastname*:<br/>
        <label>
            <input type="text" name="lastname" value=""/>
        </label>
        <br/>Phone* (only digits):<br/>
        <label>
            <input type="number" name="phone" value=""/>
        </label>
        <br/>E-mail*:<br/>
        <label>
            <input type="text" name="email" value=""/>
        </label>
        <br/>Address:<br/>
        <label>
            <input type="text" name="address" value=""/>
        </label>
        <div style="color: crimson">${errorRegistrationMessage}</div>
        ${wrongAction}
        ${nullPage}
        <br/>
        <br/>
        <input type="submit" value="Register"/>
    </form>

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>