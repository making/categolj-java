<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="CategoLJ" />
    <c:param name="content">
        <h2 id="contents-header" class="contents-header">All
        Categories</h2>
        <ul>
            <c:forEach var="categoryLink" items="${categoryLinkSet}">
                <li class="category">${categoryLink}</li>
            </c:forEach>
        </ul>
    </c:param>
</c:import>