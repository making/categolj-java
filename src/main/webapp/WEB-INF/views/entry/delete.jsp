<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="${entry.title}" />
    <c:param name="content">
        <div class="alert-message block-message">
            <form:form method="post" name="delete-form" modelAttribute="entry">
                <p>Are you sure to delete &quot;<span class="delete-title">${f:h(entry.title)}</span>&quot; ?</p>
                <form:hidden path="id" id="delete-id" />
                <div class="alert-actions">
                    <input name="submit" type="submit" class="primary btn" value="DELETE" /> 
                    <input name="submit" type="button" class="btn" value="BACK" onclick="history.back()" />
                </div>
            </form:form>
        </div>
    </c:param>
</c:import>
