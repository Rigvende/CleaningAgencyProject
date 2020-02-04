<%@page import="by.patrusova.project.service.LoginService"%>
<jsp:useBean id="user" class="by.patrusova.project.entity.impl.User"/>
<jsp:setProperty property="*" name="obj"/>
<%
    boolean status= false;
    status = LoginService.checkLogin(user);
    if(status) {
        out.println("You are successfully logged in");
        session.setAttribute("session","TRUE");
    }
    else {
        out.print("Sorry, email or password error");
%>
<jsp:include page="../login.jsp">Login</jsp:include>
<%
    }
%>