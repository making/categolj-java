<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="${entry.title}" />
    <c:param name="content">
        <div class="alert alert-block">
            <form:form method="post" name="delete-form" modelAttribute="entry">
                <p>Are you sure to delete &quot;<span class="delete-title">${f:h(entry.title)}</span>&quot; ?</p>
                <form:hidden path="id" id="delete-id" />
                <div class="alert-actions">
                    <input name="submit" type="submit" class="btn-primary btn" value="DELETE" /> 
                    <input name="submit" type="button" class="btn" value="BACK" onclick="history.back()" />
                </div>
            </form:form>
        </div>
    </c:param>
</c:import>
