<c:import url="/WEB-INF/views/mobile/layout.jsp" charEncoding="UTF-8">
    <c:param name="title">${entry.title}</c:param>
    <c:param name="meta">
    </c:param>
    <c:param name="content">
        <h3>${f:h(entry.title)}</h3>
        <p>
        ${categolj:markdown(entry.content)}
        </p>
        <hr>
        <p>
            Created at : ${f:h(entry.createdAt)}
            &nbsp; Updated at : ${f:h(entry.updatedAt)}
        </p>
    </c:param>
</c:import>