<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f" %>
<%@ page pageEncoding="UTF-8"%>
<div id="sidebar">
<div id="navi">
<h3>Menu</h3>
<ul id="menu">
    <c:choose>
        <c:when test="${empty loginUser}">
            <li><a href="<c:url value="/" />">top</a></li>
            <li><a
                href="<c:url value="/all" />">all
            categories</a></li>
            <li><a
                href="<c:url value="/login" />">login</a></li>
        </c:when>
        <c:when test="${not empty loginUser}">
            <li><a href="<c:url value="/" />">top</a></li>
            <li><a
                href="<c:url value="/all" />">all
            categories</a></li>
            <li><a
                href="<c:url value="/entry/create" />">create</a></li>
            <li><a
                href="<c:url value="/logout" />">logout</a></li>
        </c:when>
    </c:choose>
</ul>

<h3>Search</h3>
<form action="<c:url value="/search" />" method="get">
    <input name="q" placeholder="Input Keywords">
    <input type="submit" value="search">
</form>


<h3>Recently Posts</h3>
<ul id="recently-posts">
    <c:forEach var="entry" items="${recentEntries}">
        <li class="post"><a
            href="<c:url value="/entry/view/id/${f:h(entry.id)}/titie/${f:h(entry.title)}/" />">${f:h(entry.title)}</a></li>
    </c:forEach>
</ul>

</div>
</div>