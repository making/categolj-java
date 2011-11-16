<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
<meta charset="UTF-8">
<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/bootstrap.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/github.css" />">
<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<c:url value="/resources/images/favicon.ico" />">
<link rel="icon" type="image/vnd.microsoft.icon" href="<c:url value="/resources/images/favicon.ico" />">
<link id="rss" rel="alternate" type="application/rss+xml" title="<spring:message code="categolj.title" />" href="<c:url value="/feed" />">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.min.js" />"></script>
${param.meta}
<title><c:if test="${not empty param.title}">${f:h(param.title)} - </c:if><spring:message code="categolj.title" /></title>
</head>
<body>
    <div class="topbar">
        <c:import url="/WEB-INF/views/layout/topbar.jsp" />
    </div>
    <!-- /topbar -->
    <div class="container">
        <div class="content">
            <div class="page-header">
                <h1>
                    <a href="<c:url value="/" />"><spring:message code="categolj.title" /></a>
                         <small><spring:message code="categolj.description" /></small>
                </h1>
            </div>
            <div class="row">
                <div class="articles span10">
                    ${param.content}
                    
                    <div class="pagination">
                        <ul>
                        <c:forEach var="page" items="${pagerLink}"><li>${page}</li></c:forEach>
                        </ul>
                    </div>
                    <!-- /pagination -->
                </div>
                <!-- /articles -->
                
                <div class="sidebar span4">
                    <c:import url="/WEB-INF/views/layout/sidebar.jsp" />
                </div>
                <!-- /sidebar -->
            </div>
        </div>
        <footer>
            <c:import url="/WEB-INF/views/layout/footer.jsp" />
        </footer>
    </div>
    <!-- /container -->
    
    <div id="login-modal" class="modal hide fade">
        <div class="modal-header">
            <a href="#" class="close">&times;</a>
            <h3>Login Form</h3>
        </div>
        <div class="modal-body">
            <form action="<c:url value="/j_spring_security_check" />" method="post">
                <c:import url="/WEB-INF/views/login/fieldset.jsp" />
            </form>
        </div>
        <div class="modal-footer">
        </div>
    </div>
    
    <!-- scripts -->
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-modal.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/highlight.pack.js" />"></script>
    <script type="text/javascript">
    hljs.tabReplace = "    ";
    hljs.initHighlightingOnLoad();
    </script>
    <!-- buttons -->
    <script type="text/javascript"
        src="http://b.st-hatena.com/js/bookmark_button.js"
        charset="utf-8" async="async"></script>
    <script type="text/javascript"
        src="http://platform.twitter.com/widgets.js"></script>
    <script type="text/javascript"
        src="https://apis.google.com/js/plusone.js"></script>
</body>
</html>