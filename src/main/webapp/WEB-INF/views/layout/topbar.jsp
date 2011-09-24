<div class="fill">
    <div class="container">
        <!-- navigation -->
        <ul class="nav">
            <li><a href="<c:url value="/" />">Home</a></li>
            <li><a href="<c:url value="/all" />">All Categories</a></li>
            <c:if test="${empty loginUser}">
            <li><a data-controls-modal="login-modal" data-backdrop="true" data-keyboard="true">Login</a></li>
            </c:if>
        </ul>
        <!-- search form -->
        <form action="<c:url value="/search" />" method="get" class="pull-right">
            <input name="q" class="input-small span3" type="search" placeholder="Search">
        </form>
    </div>
</div>