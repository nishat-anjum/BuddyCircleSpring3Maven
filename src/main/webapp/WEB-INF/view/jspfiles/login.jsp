<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Log In</title>
    <link rel="stylesheet" href="/shproject/resources/css/style.css" type="text/css"/>
    <style>
        table#head {
            width: 80%;
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
    <h2 align="center"><fmt:message key="login.title"/></h2>
</div>
<c:if test="${invalidUser != null}">
    <div>
        <h3 align="center">${invalidUser}</h3>
    </div>
</c:if>
<form:form commandName="loggedUser">
    <table align="center" style="background-color: #1eaabd;">
        <tr>
            <td>Email :</td>
            <td><form:input path="email"/></td>
            <td><label class="required">*</label></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><form:password path="password"/></td>
            <td><label class="required">*</label></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td align="center"><input type="submit" value="Log in"/></td>
        </tr>

    </table>
</form:form>
<table align="center" style="background-color: #1eaabd;">
    <tr>
        <td>
            <c:url value="/home" var="home"/>
            <a class="menulink" href="${home}">Home</a>
        </td>
    </tr>
</table>
</body>
</html>