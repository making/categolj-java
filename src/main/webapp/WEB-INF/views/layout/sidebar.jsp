<h3 class="label">Links</h3>
<ul>
    <c:forEach var="l" items="${links}">
        <li><a href="${f:h(l.url)}">${f:h(l.name)}</a></li>
    </c:forEach>
</ul>

<h3 class="label">Recently Posts</h3>
<ul>
    <c:forEach var="entry" items="${recentEntries}">
        <li class="post"><a
            href="<c:url value="/entry/view/id/${f:h(entry.id)}/titie/${f:h(entry.title)}/" />">${f:h(entry.title)}</a></li>
    </c:forEach>
</ul>
<security:authorize ifAllGranted="ROLE_USER">
    <h3 class="label">Edit</h3>
    <ul>
        <li><a href="<c:url value="/entry/create" />">create</a></li>
        <li><a href="<c:url value="/logout" />">logout</a></li>
    </ul>
</security:authorize>
