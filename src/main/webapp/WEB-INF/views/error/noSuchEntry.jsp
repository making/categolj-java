<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="login" />
    <c:param name="content">
        <div class="alert-message error">
            <p>
                <strong>No Such Entry!</strong> : Entry ${f:h(exception.entryId)} is not found.
            </p>
        </div>
    </c:param>
</c:import>
