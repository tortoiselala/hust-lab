<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div style="text-align: center;">
    <form action="<c:url value="/login.do"/>" method="post">
        <p>username: <label>
            <input type="text" name="username"/>
        </label></p>
        <p>password: <label>
            <input type="password" name="password"/>
        </label></p>
        <p>doctor : <label>
            <input type="checkbox" name="type"/>
        </label></p>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
