<%@ page language="java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Mail response to user</title>
</head>
<body>
<form name="mailForm" method="POST" action="controller">
    <input type="hidden" name="command" value="mail" />
    <table>
        <tr>
            <td>Send to:< /td>
            <td><label>
                <input type ="text" name="to"/>
            </label></td>
        </tr>
        <tr>
            <td>Subject:</td>
            <td><label>
                <input type ="text" name="subject"/>
            </label></td>
        </tr>
    </table>
    <hr/>
    <label>
        <textarea name="body" rows="5" cols="45"></textarea>
    </label>
    <br/><br/>
    <input type="submit" value="Send message"/>
</form>
</body>
</html>