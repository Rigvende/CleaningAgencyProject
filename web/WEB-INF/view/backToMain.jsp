<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>

<div style="float: right; padding: 10px; text-align: right;">
    <img src="${pageContext.request.contextPath}/data/line.png" alt="line" width="200">

    <div style="margin-right: 35px">
        <form name="backForm" method="post" action="controller">
            <input type="hidden" name="command" value="backtomain"/>
            ${errorLoginPassMessage}
            ${wrongAction}
            ${nullPage}
            <input type="submit" value="Back to Main Page"/>
        </form>
    </div>
</div>