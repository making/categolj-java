<c:import url="/WEB-INF/views/admin/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="" />
    <c:param name="meta">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/jquery-ui/css/cupertino/jquery-ui-1.8.16.custom.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/pagedown/pagedown.css" />">
    
    </c:param>
    <c:param name="content">
        <script src='<c:url value="/resources/js/pagedown/Markdown.Converter.js" />' type='text/javascript'></script>
        <script src='<c:url value="/resources/js/pagedown/Markdown.Editor.js" />' type='text/javascript'></script>
        <script src='<c:url value="/resources/js/jquery-ui/js/jquery-ui-1.8.16.custom.min.js" />' type='text/javascript'></script>
        <script src='<c:url value="/resources/js/jquery.upload-1.0.2.modified.min.js" />' type='text/javascript'></script>
        <script src='<c:url value="/resources/js/form.js" />' type='text/javascript'></script>
        <script type="text/javascript">
            $(document).ready(function() {
                am.ik.categolj.entry.Form.init('<c:url value="/" />');
            });
        </script>
        <form:form method="post" modelAttribute="entry">
            <fieldset>
                <legend>
                <a href="<c:url value="/entry/view/id/${f:h(entry.id)}/title/${f:u(entry.title)}/" />">${f:h(entry.title)}</a>
                </legend>
                <spring:hasBindErrors name="entry">
                    <script type="text/javascript">
                        $(document).ready(function() {
                            var errorDiv = $("div.clearfix>div.input>.error").parent().parent().addClass("error");
                        });
                    </script>
                </spring:hasBindErrors>
                
                <div class="clearfix">
                    <label for="field-title">Title : </label>
                    <div class="input">
                        <form:input path="title" id="field-title" cssClass="span10" cssErrorClass="error span7" />
                        <form:errors path="title" cssClass="error help-inline inline" element="span" />
                        <form:hidden path="id" />
                    </div>
                </div>
                <div class="clearfix">
                    <label for="field-category">Category : </label>
                    <div class="input">
                        <form:input path="category" id="field-category" cssClass="span10" cssErrorClass="error span7" />
                        <form:errors path="category" cssClass="error help-inline inline" element="span" />
                    </div>
                </div>
                <div class="clearfix">
                    <label for="wmd-input">Content : </label>
                    <div class="input wmd-panel">
                        <div id="wmd-button-bar" class="wmd-button-bar"></div>
                        <form:textarea path="content" id="wmd-input" cssClass="wmd-input span10" cssErrorClass="wmd-input error span7" />
                        <form:errors path="content" cssClass="error help-inline inline" element="span" />
                    </div>
                </div>
                <div class="clearfix">
                    <label for="field-createdAt">createdAt : </label>
                    <div class="input">
                        <form:input path="createdAt" id="field-createdAt" cssClass="" cssErrorClass="error" />
                        <form:errors path="createdAt" cssClass="error help-inline inline" element="span" />
                    </div>
                </div>
                <div class="clearfix">
                    <label for="field-updatedAt">updatedAt : </label>
                    <div class="input">
                        <form:input path="updatedAt" id="field-updatedAt" cssClass="" cssErrorClass="error" />
                        <form:errors path="updatedAt" cssClass="error help-inline inline" element="span" />
                    </div>
                </div>
                <div class="clearfix">
                    <label for="field-updateDate">updateDate : </label>
                    <div class="input">
                        <form:checkbox path="updateDate" id="field-updateDate" cssClass="" cssErrorClass="error" />
                        <form:errors path="updateDate" cssClass="error help-inline inline" element="span" />
                    </div>
                </div>
                <div class="clearfix">
                    <div class="input">
                         <input type="submit" class="btn primary" value="SUBMIT"> 
                         <input type="reset" class="btn" value="RESET">
                     </div>
                </div>
            </fieldset>
        </form:form>
        <hr>
        <h3 id="search-product" style="cursor: pointer">Amazon Product Search</h3>
        <div id="search-product-content" style="min-height: 150px;display: none;">
            <input id="search-product-term" class="span3" type="search" placeholder="Title or Keyword"> 
            <input type="radio" id="search-product-title" name="searchType" value="title" checked="checked"> <label style="float: none;" for="search-product-title">title</label>
            <input type="radio" id="search-product-keyword" name="searchType" value="keyword"> <label style="float: none;" for="search-product-keyword">keyword</label>
            <input id="search-product-btn" class="btn primary disabled" type="button" value="SEARCH"> 
            <input id="search-product-reset-btn" class="btn" type="reset" value="RESET"> 
            <div id="search-product-result" style="margin-top: 10px;"></div>
        </div>
        <hr>
        <h3 id="uploader" style="cursor: pointer">Uploader</h3>
        <div id="uploader-content" style="display: none">
            <input name="file" type="file" id="upload-file" />
            <table id="upload-result" class="zebra-striped">
                <tr>
                    <th>ID</th>
                    <th>IMG</th>
                    <th>PATH</th>
                    <th>EXT</th>
                    <th>SIZE</th>
                    <th>ACTION</th>
                </tr>
            </table>
        </div>
        <hr>
        <h3 id="preview" style="cursor: pointer">Live Preview</h3>
        <div id="wmd-preview" class="wmd-preview"></div>
    </c:param>
</c:import>
