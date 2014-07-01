<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<!DOCTYPE html>
<html>
<body>
<table class="mainTable">
    <c:if test="${loggedUser!=user}">
        <c:if test="${user.imageFileName != null}">
            <tr>
                <td style="text-align: left" width="30%">
                    <c:url var="photoUrl" value="/user/showProfilePhoto/${user.userId}"/>
                    <img src="${photoUrl}" width="100" height="120"/>&nbsp;
                </td>
                   <td>${user.firstName}&nbsp;${user.lastName}</td>
            </tr>
        </c:if>
    </c:if>
    <tr>
        <th>Name :</th>
        <td class="profile">
            ${user.firstName}&nbsp;${user.lastName}
        </td>
    </tr>
    <tr>
        <th>Gender :</th>
        <td class="profile">${user.gender}</td>
    </tr>
    <tr>
        <th>Date Of Birth :</th>
        <td class="profile">${user.dateOfBirth}</td>
    </tr>
    <tr>
        <th>Address :</th>
        <td class="profile">${user.address}</td>
    </tr>
    <tr>
        <th>Profession :</th>
        <td class="profile">${user.profession}</td>
    </tr>
    <tr>
        <th>email :</th>
        <td class="profile">${user.email}</td>
    </tr>
    <tr>
        <th>Phone No :</th>
        <td class="profile">${user.phoneNo}</td>
    </tr>
    <tr>
        <th>About Me :</th>
        <td>${user.aboutMe}</td>
    </tr>
    <c:if test="${loggedUser==user}">
        <tr>
            <td class="profile" align="center">
                <c:url var="editProfile" value="/user/editProfile"/>
                <a class="menulink" href="${editProfile}">Edit Information</a>
            </td>
        </tr>
    </c:if>
    <c:if test="${sendFriendRequest!=null}">
        <tr>
            <td align="center">
                <c:url var="friend" value="/friends/sendRequest/${user.userId}"/>
                <a class="menulink" href="${friend}">Send Friend Request</a>
            </td>
        </tr>
    </c:if>
</table>
</body>
</html>
