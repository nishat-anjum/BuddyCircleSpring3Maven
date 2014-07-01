<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>

<!DOCTYPE html>
<html>
<head>
    <title>BuddyCircle.com</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css"/>
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css"/>
    <script>
        $(function () {
            $("#datepicker").datepicker({
                changeMonth:true,
                changeYear:true
            });
        });
    </script>
    <style>
        table#head {
            width: 70%;
            margin: auto;
            height: 100px;
            font-size: 30pt;
            font-family: sans-serif;
            font-weight: bold;
            font-style: italic;
            text-align: center;
            background-image: url("/resources/img/images.jpeg");
            color: #0e0b44;
        }
    </style>
</head>
<body>
<table id="head">
    <tr>
        <td>
            BuddyCircle.com
        </td>
    </tr>
</table>
<div>
    <h2 align="center"><fmt:message key="registration.title"/></h2>
</div>
<div>
    <h3 align="center">${registrationSuccess}</h3>
</div>
<form:form commandName="user">
    <table align="center" style="background-color: #1eaabd;">
        <tr>
            <td>First Name :</td>
            <td><form:input path="firstName"/></td>
            <td><label class="required">*</label></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Last Name :</td>
            <td><form:input path="lastName"/></td>
            <td><label class="required">*</label></td>
            <td><form:errors path="lastName" cssClass="error"/></td>

        </tr>
        <tr>
            <td>email :</td>
            <td><form:input path="email"/></td>
            <td><label class="required">*</label></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td>I am :</td>
            <td>
                <form:radiobutton path="gender" value="Male"/>
                    <%--@declare id="gender"--%><label for="gender">Male<label>
                    <form:radiobutton path="gender" value="Female"/>
                <label for="gender">Female</label>
            </td>

        </tr>
        <tr>
            <td>Date of birth</td>
            <td><form:input path="dateOfBirth" id="datepicker"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password"/></td>
            <td><label class="required">*</label></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Confirm Password :</td>
            <td><form:password path="confPassword"/></td>
            <td><label class="required">*</label></td>
            <td><form:errors path="confPassword" cssClass="error"/></td>
        </tr>
        <tr align="center">
            <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
<br/>
<table align="center" style="background-color: #1eaabd;">
    <tr>
        <td>
            <c:url value="/login" var="userLogin"/>
            <a class="menulink" href="${userLogin}">Log In</a>
        </td>
    </tr>
</table>
</body>
</html>