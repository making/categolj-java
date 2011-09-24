<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="login" />
    <c:param name="content">
        <div class="alert-message error">
            <p>
                <strong>Error!</strong> : ${f:h(exception.message)}
            </p>
        </div>
    </c:param>
</c:import>
