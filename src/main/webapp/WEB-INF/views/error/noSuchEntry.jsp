<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="login" />
    <c:param name="content">
        <div class="alert alert-error span6">
            <h4 class="alert-heading">No Such Entry!</h4>
            Entry ${f:h(exception.entryId)} is not found.
        </div>
    </c:param>
</c:import>
