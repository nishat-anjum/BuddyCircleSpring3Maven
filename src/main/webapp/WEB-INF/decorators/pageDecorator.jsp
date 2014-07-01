<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
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
</head>
<body>
<table id="head">
    <tr>
        <td>
            BuddyCircle.com
        </td>
    </tr>
</table>
<c:url var="searchFriend" value="/search"/>
<form action="${searchFriend}" method="POST">
    <table id="searchTable">
        <td align="right" width="40%"><input type="text" value="" name="searchFriend"/></td>
        <td align="left" width="40%"><input type="submit" value="Search" name="searchButton"/></td>
        </tr>
    </table>
</form>
<table class="userTable">
    <tr>
        <td width="30%">
            <c:if test="${loggedUser.imageFileName != null}">
                <c:url var="photoUrl" value="/user/showProfilePhoto/${loggedUser.userId}"/>
                <img src="${photoUrl}" width="120" height="130" id="pic">&nbsp;
            </c:if>
            <b>${loggedUser.firstName}&nbsp;${loggedUser.lastName}</b>
        </td>
        <td style="text-align: right" width="30%">Logged in as
            <b>${loggedUser.firstName}&nbsp;${loggedUser.lastName}</b>
        </td>
    </tr>
</table>

<table id="menuTable">
    <tr>
        <td class="menutd">
            <c:url var="home" value="/posts"/>
            <a href="${home}" class="menulink">Home</a>
        </td>
        <td class="menutd">
            <c:url var="profile" value="/user/profile"/>
            <a href="${profile}" class="menulink">About</a>
        </td>
        <td class="menutd">
            <c:url var="friendList" value="/friends/friendList"/>
            <a href="${friendList}" class="menulink">Buddy List(${numOfFriends})</a>
        </td>
        <td class="menutd">
            <c:url var="newRequests" value="/friends/newRequests"/>
            <a href="${newRequests}" class="menulink">New Requests(${numOfNewRequest})</a>
        </td>
        <td class="menutd">
            <c:url var="logOut" value="/logOut"/>
            <a href="${logOut}" class="menulink">Log Out</a>
        </td>
    </tr>
</table>
<decorator:body/>
<table id="footer">
    <tr>
        <td> Copyright&nbsp;&copy;&nbsp;all right reserved by BuddyCircle.com</td>
    </tr>
</table>
</body>
</html>