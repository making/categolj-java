<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
    href="<c:url value="/resources/css/main.css" />" />
<link rel='shortcut icon' type='image/vnd.microsoft.icon'
    href='<c:url value="/resources/images/favicon.ico" />' />
<link rel='icon' type='image/vnd.microsoft.icon'
    href='<c:url value="/resources/images/favicon.ico" />' />
<link
    href='<c:url value="/resources/css/github.css" />'
    rel='stylesheet' />
<script
    src='<c:url value="/resources/js/highlight.pack.js" />'
    type='text/javascript'></script>
<script type='text/javascript'>
    hljs.tabReplace = '    ';
    hljs.initHighlightingOnLoad();
</script>
<link id='rss' rel='alternate' type='application/rss+xml' title=''
    href='<c:url value="/feed" />' />
<title>${param.title}</title>
</head>
<body>
<div id='body'>
<div id='header'>
<h1><a href='<c:url value="/" />'>CategoLJ for Java</a></h1>
</div>
<div id='sidebar'><c:import
    url="/WEB-INF/views/layout/sidebar.jsp" /></div>
<div id='main'>
<h2 class='contents-header'><!-- contents-header --></h2>
<div class='contents'>${param.content}</div>
<ul class='pages'>
<c:forEach var="page" items="${pagerLink}">
    <li class='page'>${page}</li>
</c:forEach>
</ul>
</div>
<c:import url="/WEB-INF/views/layout/footer.jsp" />
</div>
</body>
</html>