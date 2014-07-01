<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<c:url var="editInfo" value="/user/updateInfo"/>
<form:form commandName="loggedUser" enctype="multipart/form-data" action="${editInfo}" method="POST">
    <table width="70%" align="center">
        <tr>
            <td align="center">About</td>
        </tr>
        <tr>
            <td align="center">First Name :</td>
            <td align="left"><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td align="center">Last Name :</td>
            <td align="left"><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td align="center">email :</td>
            <td align="left"><form:input path="email"/></td>

        </tr>
        <tr>
            <td align="center">I am :</td>
            <td align="left"><form:select path="gender">
                <form:option value="Male"/>
                <form:option value="Female"/>
            </form:select></td>

        </tr>
        <tr>
            <td align="center">Date of birth</td>
            <td align="left"><form:input path="dateOfBirth" id="datepicker"/></td>
        </tr>
        <tr>
            <td align="center">Address:</td>
            <td align="left"><form:textarea path="address" cols="30"/></td>
        </tr>
        <tr>
            <td align="center">Profession :</td>
            <td align="left"><form:textarea path="profession" cols="30"/></td>
        </tr>
        <tr>
            <td align="center">Phone No :</td>
            <td align="left"><form:input path="phoneNo"/></td>
        </tr>
        <tr>
            <td align="center">About Me :</td>
            <td align="left"><form:textarea path="aboutMe" cols="30"/></td>
        </tr>
        <tr>
            <td align="center">
                <form:label path="image">Profile Photo</form:label>
                <input type="file" name="imageContent" id="image"/>
            </td align="center">
        </tr>
        <tr align="center">
            <td colspan="2"><input type="submit" value="Update"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>

