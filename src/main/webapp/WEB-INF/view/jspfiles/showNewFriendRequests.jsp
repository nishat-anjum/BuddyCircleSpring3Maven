<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<h3 align="center">Friend Requests List</h3>

<h3 align="center">${noNewRequests}</h3>
<table align="center">
    <c:if test="${not empty newRequests}">
        <c:forEach var="requestingUser" items="${newRequests}">
            <tr>
                <td align="center">
                    <c:url var="userProfile" value="/user/showProfile/${requestingUser.userId}"/>
                    <a class="menulink"
                       href="${userProfile}"> ${requestingUser.firstName}&nbsp;${requestingUser.lastName}</a>
                </td>
                <td align="left">
                    <c:url var="acceptFriend" value="/friends/acceptRequest/${requestingUser.userId}"/>
                    <a class="menulink" href="${acceptFriend}">Accept Friend Request</a>
                </td>
            </tr>
            <br/>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
