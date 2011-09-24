<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="${entry.title}" />
    <c:param name="content">
        ${entry.categoryBreadCrumb}
        <c:import url="/WEB-INF/views/entry/viewBase.jsp"
            charEncoding="UTF-8">
            <c:param name="id" value="${entry.id}" />
            <c:param name="title" value="${entry.title}" />
            <c:param name="content" value="${entry.content}" />
            <c:param name="createdAt">
                <fmt:formatDate value="${entry.createdAt}"
                    pattern="yyyy-MM-dd HH:mm:ss" />
            </c:param>
            <c:param name="updatedAt">
                <fmt:formatDate value="${entry.updatedAt}"
                    pattern="yyyy-MM-dd HH:mm:ss" />
            </c:param>
            <c:param name="categoryLink"
                value="${entry.categoryLink}" />
        </c:import>
    </c:param>
</c:import>
