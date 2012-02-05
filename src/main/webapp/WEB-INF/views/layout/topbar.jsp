<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <!-- navigation -->
            <ul class="nav">
                <li><a href="<c:url value="/" />">Home</a></li>
                <li><a href="<c:url value="/all" />">All
                        Categories</a></li>
                <c:if test="${empty loginUser}">
                    <li><a data-toggle="modal"
                        data-target="#login-modal" data-backdrop="true"
                        data-keyboard="true">Login</a></li>
                </c:if>
                <c:if test="${not empty loginUser}">
                    <li><a href="<c:url value="/admin/index" />">Admin</a></li>
                    <li><a href="<c:url value="/logout" />">Logout</a></li>
                </c:if>
            </ul>
            <!-- search form -->
            <form action="<c:url value="/search" />" method="get"
                class="pull-right navbar-form form-search">
                <input name="q" class="input-medium search-query" type="search"
                    placeholder="Search">
            </form>
        </div>
    </div>
</div>