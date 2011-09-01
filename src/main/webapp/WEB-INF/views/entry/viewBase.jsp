<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f"%>
<%@ taglib uri="/categolj" prefix="categolj"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="contents">
    <dl class="main-contents">
        <dt class="article-title">
            <a
                href="<c:url value="/entry/view/id/${f:h(param.id)}/${f:h(param.title)}/" />">${f:h(param.title)}</a>
        </dt>
        <dd>
            <div class="article-content">${categolj:markdown(param.content)}</div>
            <div class="edit-menu">
                <div class="edit">
                    <c:choose>
                        <c:when test="${not empty loginUser}">
                 [<a class="edit-link"
                                href="<c:url value="/entry/edit/id/${f:h(param.id)}/" />">edit</a>] [<a
                                class="delete-link"
                                href="<c:url value="/entry/delete/id/${f:h(param.id)}/" />">delete</a>]
                </c:when>
                    </c:choose>
                </div>
                <div class="no-float"></div>
            </div>
            <div class="date">
                <p>
                    Created at : <span class="article-created-at">${f:h(param.createdAt)}</span>
                    &nbsp; Updated at : <span class="article-updated-at">${f:h(param.updatedAt)}</span>
                    <br> Category : <span class="article-category">${param.categoryLink}</span>
                    <br> <a href="http://twitter.com/share"
                        class="twitter-share-button"
                        data-count="horizontal"
                        data-text="<c:if test="${not empty param.title}">${f:h(param.title)} - </c:if><spring:message code="categolj.title" />"
                        data-via="<spring:message code="categolj.twitter.account" />"
                        data-url="<spring:message code="categolj.url" />/entry/view/id/${f:h(param.id)}/${f:u(param.title)}/">Tweet</a>
                    <script type="text/javascript"
                        src="http://platform.twitter.com/widgets.js"></script>
                </p>
            </div>
        </dd>
    </dl>
</div>