<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<!DOCTYPE html>
<html>
<body>
<table class="userTable">
    <tr>
        <td>
            <c:if test="${friend.imageFileName != null}">
                <c:url var="photoUrl" value="/user/showProfilePhoto/${friend.userId}"/>
                <img src="${photoUrl}" width="60" height="70"/>&nbsp;
            </c:if>
            <b>${friend.firstName} &nbsp; ${friend.lastName}</b>
        </td>
        <td>
            <c:url var="about" value="/friends/about/${friend.userId}"/>
            <a class="menulink" href="${about}">About</a>
        </td>
        <td>
            <c:url var="unFriend" value="/friends/unFriend/${friend.userId}"/>
            <a class="menulink" href="${unFriend}">Unfriend</a>
        </td>
    </tr>
</table>

<c:url var="postToFriendUrl" value="/posts/saveFriendsPost/${friend.userId}"/>
<form:form commandName="wallPost" enctype="multipart/form-data" action="${postToFriendUrl}" method="post">
    <table class="wallpostTable">
        <tr>
            <td class="textArea"><form:textarea path="postContent" cols="80"/>
                <input class="submitbutton" type="submit" value="Share"/>
            </td>
        </tr>
        <tr>
            <td class="holler">
                <form:label path="audioContent">Holler</form:label>
                <input type="file" name="holler" id="holler"/>
            </td>
        </tr>

    </table>
</form:form>


<%--***********************************Post and content***************************************--%>

<table class="mainTable">
    <tr>
        <td> ${noPosts}
            <c:forEach var="post" items="${friendsPost}">
                <table class="postDetails">
                    <tr>
                        <td class="userName"><b>${post.postBy.firstName} &nbsp;${post.postBy.lastName}</b></td>
                        <td class="date">${post.date}</td>
                    </tr>
                    <tr>
                        <td class="postContent">${post.postContent}</td>
                        <td class="audio">
                            <c:if test="${post.audioFileName!=''}">
                                <audio controls="controls">
                                    <c:url var="audioUrl" value="/posts/playAudio/${postId.id}"/>
                                    <source src="${audioUrl}" type="audio/ogg"/>
                                </audio>
                            </c:if>
                        </td>
                    </tr>
                </table>

                <c:forEach var="commentList" items="${post.commentList}">
                    <table class="commentTable">
                        <tr>
                            <td class="userName"><b>${commentList.commentBy.firstName} &nbsp; ${commentList.commentBy.lastName}</b></td>
                            <td class="date">${commentList.date}</td>
                        </tr>
                        <tr>
                            <td class="postContent">${commentList.content}</td>
                            <td class="audio">
                                <c:if test="${commentList.audioFileName!=''}">
                                    <audio controls="controls">
                                        <c:url var="audioUrl" value="/comment/playAudio/${commentList.commentId}"/>
                                        <source src="${audioUrl}" type="audio/ogg"/>
                                    </audio>
                                </c:if>
                            </td>
                        </tr>
                        <br/>
                    </table>
                </c:forEach>

                <c:url var="commentUrl" value="/comment/saveCommentContent/${post.postId}"/>
                <form:form commandName="comment" enctype="multipart/form-data" action="${commentUrl}" method="post">
                    <table class="commentArea">
                        <tr>
                            <td class="textArea"><form:textarea path="content"
                                                                cols="70"/>&nbsp;<input type="submit" value="Comment"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="holler">
                                <form:label path="audio">Holler</form:label>
                                <input type="file" name="holler" id="holler"/>
                            </td>
                        </tr>

                    </table>
                </form:form>
                <br/>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>
