<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<h3 align="center">Buddy List</h3>

<h3 align="center">${emptyFriendList}</h3>
<table>
    <c:if test="${allFriendList!=null}">
        <c:forEach var="friend" items="${allFriendList}">
            <tr>
                <td align="center">
                    <c:url var="friendProfile" value="/friends/showFullProfile/${friend.userId}"/>
                    <a class="menulink" href="${friendProfile}"> ${friend.firstName}&nbsp;${friend.lastName}</a>
                </td>
                <td align="left">
                    <c:url var="unFriend" value="/friends/unFriend/${friend.userId}"/>
                    <a class="menulink" href="${unFriend}">Unfriend</a>
                </td>
            </tr>
            <br/>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
