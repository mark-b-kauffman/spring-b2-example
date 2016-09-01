<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="couse" type="blackboard.data.course.Course"--%>
<%--@elvariable id="users" type="java.util.List"--%>

<html>
<body>
<pre><u>
Course: ${course.courseId} </u>
<br/>
<u>Users:</u><br/>
<c:forEach items="${users}" var="element">
    <c:out value="${element.userName}"/><br/>
</c:forEach>

<br/>
<u>The single user obtained by loadByCourseAndUserId:</u><br/>

    <c:out value="${user2.userName}"/><br/>

</pre>
</body>
</html>