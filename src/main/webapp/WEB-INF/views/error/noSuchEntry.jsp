<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f"%>
<%@ page session="true"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="login" />
    <c:param name="content">
    <h2>No Such Entry!</h2>
    <p>Entry ${f:h(exception.entryId)} is not found.</p>
    </c:param>
</c:import>
