<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql_rt" uri="http://java.sun.com/jstl/sql_rt" %>
<!DOCTYPE html>
<html>
<body>
<form:form commandName="wallPost" enctype="multipart/form-data">
    <table class="wallpostTable">
        <tr>
            <td class="textArea"><form:textarea path="postContent" cols="70" rows="3"/>&nbsp;
                <input class="submitbutton" type="submit" value="Share"/>&nbsp; what's on your mind???
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
<table class="mainTable">
    <tr>
        <td>${noPosts}
            <c:forEach var="post" items="${posts}">
                <table class="postDetails">
                    <tr>
                        <td class="userName">${post.postBy.firstName}&nbsp;${post.postBy.lastName}</td>
                        <td class="date">${post.date}</td>
                    </tr>
                    <tr>
                        <td class="postContent">${post.postContent}</td>

                        <td class="audio">
                            <c:if test="${post.audioFileName!=''}">
                                <audio controls="controls" class="audioDes">
                                    <c:url var="audioUrl" value="/posts/playAudio/${post.postId}"/>
                                    <source src="${audioUrl}"/>
                                </audio>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <c:url var="edit" value="/posts/edit/${post.postId}"/>
                            <a class="menulink" href="${edit}">Edit</a>
                        </td>
                        </td>
                        <td>
                            <c:url var="delete" value="/posts/deletePost/${post.postId}"/>
                            <a class="menulink" href="${delete}">Delete</a>
                        </td>
                    </tr>
                </table>
                <c:forEach var="commentList" items="${post.commentList}">
                    <table class="commentTable">
                        <tr>
                            <td class="userName"><b>${commentList.commentBy.firstName}&${commentList.commentBy.lastName}</b></td>
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
                        <tr>
                            <td>
                                <c:url var="delete" value="/comment/deleteComment/${commentList.commentId}"/>
                                <a class="menulink" href="${delete}">Delete</a>
                            </td>
                        </tr>
                        <br/>
                    </table>
                </c:forEach>
                <c:url var="commentUrl" value="/comment/saveCommentContent/${post.postId}"/>
                <form:form commandName="comment" enctype="multipart/form-data" action="${commentUrl}" method="post">
                    <table class="commentArea">
                        <tr>
                            <td class="textArea">
                                <form:textarea path="content" cols="70"/>&nbsp;<input class="submitbutton" type="submit"
                                                                                      value="Comment"/>
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
