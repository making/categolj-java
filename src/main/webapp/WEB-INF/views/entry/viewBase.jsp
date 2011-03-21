<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="categolj" uri="/categolj" %>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class='contents'>
<dl class='main-contents'>
    <dt class='article-title'><a
        href='<c:url value="entry/view/id/${param.id}/" />'>${param.title}</a></dt>
    <dd>
    <div class='article-content'>${categolj:markdown(param.content)}</div>
    <div class='edit-menu'>
    <div class='edit'><c:choose>
        <c:when test="${not empty loginUser}">
                 [<a class='edit-link'
                href="<c:url value="/entry/edit/id/${param.id}/" />">edit</a>] [<a
                class='delete-link'
                href="<c:url value="/entry/delete/id/${param.id}/" />">delete</a>]
                </c:when>
    </c:choose></div>
    <div class='no-float'></div>
    </div>
    <div class='date'>
    <p>Created at : <span class='article-created-at'>${param.createdAt}</span>
    &nbsp; Updated at : <span class='article-updated-at'>${param.updatedAt}</span>
    <br />
    Category : <span class='article-category'>${param.categoryLink}</span>
    </p>
    </div>
    </dd>
</dl>
</div>