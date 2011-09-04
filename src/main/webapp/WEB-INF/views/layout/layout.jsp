<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
    href="<c:url value="/resources/css/main.css" />" />
<link rel="shortcut icon" type="image/vnd.microsoft.icon"
    href="<c:url value="/resources/images/favicon.ico" />" />
<link rel="icon" type="image/vnd.microsoft.icon"
    href="<c:url value="/resources/images/favicon.ico" />" />
<link href="<c:url value="/resources/css/github.css" />"
    rel="stylesheet" />
<link id="rss" rel="alternate" type="application/rss+xml"
    title="<spring:message code="categolj.title" />"
    href="<c:url value="/feed" />" />
<title><c:if test="${not empty param.title}">${f:h(param.title)} - </c:if>
    <spring:message code="categolj.title" /></title>
</head>
<body>
    <div id="body">
        <div id="header">
            <h1>
                <a href="<c:url value="/" />"><spring:message
                        code="categolj.title" /> </a>
            </h1>
        </div>
        <div id="sidebar">
            <c:import url="/WEB-INF/views/layout/sidebar.jsp" />
        </div>
        <div id="main">
            <h2 class="contents-header">
                <!-- contents-header -->
            </h2>
            <div class="contents">${param.content}</div>
            <ul class="pages">
                <c:forEach var="page" items="${pagerLink}">
                    <li class="page">${page}</li>
                </c:forEach>
            </ul>
        </div>
        <c:import url="/WEB-INF/views/layout/footer.jsp" />
    </div>
    <script src="<c:url value="/resources/js/highlight.pack.js" />"
        type="text/javascript"></script>
    <script type="text/javascript">
                    hljs.tabReplace = "    ";
                    hljs.initHighlightingOnLoad();
                </script>
    <script type="text/javascript"
        src="http://b.st-hatena.com/js/bookmark_button.js"
        charset="utf-8" async="async"></script>
    <script type="text/javascript"
        src="http://platform.twitter.com/widgets.js"></script>
    <script type="text/javascript"
        src="https://apis.google.com/js/plusone.js"></script>
</body>
</html>