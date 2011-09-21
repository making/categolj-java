<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f" %>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
