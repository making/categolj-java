<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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