<%--
  Created by IntelliJ IDEA.
  User: tortoiselala
  Date: 2019-03-27
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <style>
        th, tr, td, table {
            border: 1px solid red;
        }
    </style>
</head>
<body>
<h1>病人列表</h1>
<table>
    <tr>
        <th>挂号编号</th>
        <th>病人名称</th>
        <th>挂号日期时间</th>
        <th>号别种类</th>
    </tr>

    <c:forEach items="${PATIENT_LIST}" var="value" varStatus="idx">
        <tr>
            <td>${value.registrationID}</td>
            <td>${value.patientName}</td>
            <td>${value.date}</td>
            <td>${value.doctor == true ? "专家号" : "普通号"}</td>
        </tr>
    </c:forEach>
</table>
<h1>收入列表</h1>
<table>
    <tr>
        <th>科室名称</th>
        <th>医生编号</th>
        <th>医生姓名</th>
        <th>号别种类</th>
        <th>挂号人数</th>
        <th>收入总额</th>
    </tr>

    <c:forEach items="${INCOME_LIST}" var="value2" varStatus="idx">
        <tr>
            <td>${value2.labName}</td>
            <td>${value2.doctorID}</td>
            <td>${value2.doctorName}</td>
            <td>${value2.type}</td>
            <td>${value2.num}</td>
            <td>${value2.income}</td>
        </tr>
    </c:forEach>
</table>

</body>
