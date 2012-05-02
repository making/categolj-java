<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/github.css" />">
<style type="text/css">
code, pre {
  padding: 0 3px 2px;
  font-family: Monaco, Andale Mono, Courier New, monospace;
  font-size: 12px;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
}

pre {
    width: 90%;
    margin: 10px 0;
    padding: 3px;
    border: solid 1px #dedede;
    -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
    -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
    box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
    background-color: #fff;
    white-space: pre;
    white-space: pre-wrap;
    word-wrap: break-word;
}
</style>
${param.meta}
<title><c:if test="${not empty param.title}">${f:h(param.title)} - </c:if>
    <spring:message code="categolj.title" /></title>
</head>
<body>
    <section data-role="page" data-title="${f:h(param.title)}"data-add-back-btn="true">
        <header data-role="header" data-position="fixed">
            <h1>
                <spring:message code="categolj.title" />
            </h1>
            <a href="<c:url value="/" />" data-icon="home" data-iconpos="notext" class="ui-btn-right" rel="external">Home</a>
        </header>
        <!-- /header -->
        <div data-role="content" class="content">
        ${param.content}
        </div>
        <!-- /content -->
        <footer data-role="footer">
            <h4>
               [<a href="<c:url value="/?pc=true" />" rel="external">PC ver.</a>]
            </h4>
        </footer>
        <!-- /footer -->
    </section>
    <!-- /page -->
</body>
</html>