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
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/jquery-ui/css/bootstrap/jquery-ui-1.8.16.custom.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/jqgrid/css/ui.jqgrid.css" />">
<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<c:url value="/resources/images/favicon.ico" />">
<link rel="icon" type="image/vnd.microsoft.icon" href="<c:url value="/resources/images/favicon.ico" />">
<link id="rss" rel="alternate" type="application/rss+xml" title="<spring:message code="categolj.title" />" href="<c:url value="/feed" />">
<script type="text/javascript" src="${contextRoot}/resources/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="${contextRoot}/resources/js/mk.js"></script>
<script type="text/javascript">
    mk.ns('categolj').contextRoot = '${contextRoot}';
</script>
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
                <h1>Admin Console</h1>
            </div>
            <div class="row">
                <div class="console span14">
                    ${param.content}
                    
                    <div class="pagination">
                        <ul>
                        <c:forEach var="page" items="${pagerLink}"><li>${page}</li></c:forEach>
                        </ul>
                    </div>
                    <!-- /pagination -->
                </div>
                <!-- /console -->
            </div>
        </div>
        <footer>
            <c:import url="/WEB-INF/views/layout/footer.jsp" />
        </footer>
    </div>
    <!-- /container -->
    
    <!-- scripts -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/js/jquery-ui-1.8.16.custom.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jqgrid/js/i18n/grid.locale-en.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jqgrid/js/jquery.jqGrid.src.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-modal.js" />"></script>
<%--     <script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-tabs.js" />"></script> --%>
    
    
</body>
</html>