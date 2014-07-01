<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<h3 align="center">Search Result</h3>

<h3 align="center">${noSearchResult}</h3>

<h3 align="center">${sentMessage}</h3>
<table class="mainTable">
    <c:forEach var="user" items="${searchResult}">
        <tr>
            <li>
                <td align="center">
                        <c:url var="userProfile" value="/user/about/${user.userId}"/>
                    <a class="menulink" href="${userProfile}"> ${user.firstName}&nbsp;${user.lastName}</a>
                <td align="left">
                    <c:url var="friend" value="/friends/sendRequest/${user.userId}"/>
                    <a class="menulink" href="${friend}">Send Friend Request</a>
                </td>
            </li>
        </tr>
        <br/>
    </c:forEach>
</table>
</body>
</html>
