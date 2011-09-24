<article>
    <h2><a href="<c:url value="/entry/view/id/${f:h(param.id)}/title/${f:h(param.title)}/" />">${f:h(param.title)}</a></h2>
    ${categolj:markdown(param.content)}
    <div class="edit">
        <c:choose>
            <c:when test="${not empty loginUser}">
                <a class="btn primary" href="<c:url value="/entry/edit/id/${f:h(param.id)}/" />">EDIT</a> 
                <a class="btn delete-link" href="<c:url value="/entry/delete/id/${f:h(param.id)}/" />">DELETE</a>
            </c:when>
        </c:choose>
    </div>
    <div class="date">
        <p>
            Created at : <span class="article-created-at">${f:h(param.createdAt)}</span>
            &nbsp; Updated at : <span class="article-updated-at">${f:h(param.updatedAt)}</span>
            <br> Category : <span class="article-category">${param.categoryLink}</span>
            <br> <br> <a
                href="http://b.hatena.ne.jp/entry/<spring:message code="categolj.url" />/entry/view/id/${f:h(param.id)}/title/${f:u(param.title)}/"
                class="hatena-bookmark-button"
                data-hatena-bookmark-title="${f:h(param.title)}"
                data-hatena-bookmark-layout="standard"
                title="このエントリーをはてなブックマークに追加"><img
                src="http://b.st-hatena.com/images/entry-button/button-only.gif"
                alt="このエントリーをはてなブックマークに追加" width="20"
                height="20" style="border: none;" />
            </a>   
            <a href="http://twitter.com/share"
                class="twitter-share-button"
                data-count="horizontal"
                data-text="<c:if test="${not empty param.title}">${f:h(param.title)} - </c:if><spring:message code="categolj.title" />"
                data-via="<spring:message code="categolj.twitter.account" />"
                data-url="<spring:message code="categolj.url" />/entry/view/id/${f:h(param.id)}/title/${f:u(param.title)}/">Tweet</a>
            <g:plusone size="medium"
                href="<spring:message code="categolj.url" />/entry/view/id/${f:h(param.id)}/title/${f:u(param.title)}/"></g:plusone>
        </p>
    </div>
</article>