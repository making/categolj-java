<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f" %>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="" />
    <c:param name="content">
        <div class="contents edit-form">
        <script
            type="text/javascript">
    wmd_options = {
        output : 'Markdown'
    };
</script> <script
            src='<c:url value="/resources/js/wmd/wmd.js" />'
            type='text/javascript'></script> <script
            src='<c:url value="/resources/js/jquery-1.4.4.min.js" />'
            type='text/javascript'></script> <script
            src='<c:url value="/resources/js/jquery.upload-1.0.2.modified.min.js" />'
            type='text/javascript'></script> <script
            type='text/javascript'>
    var contextPath = '<c:url value="/" />';

    function isImage(ext) {
        return ext == "png" || ext == "jpg" || ext == "gif";
    }

    function appendRow(obj, uploadDir, file) {
        var path = contextPath + uploadDir +"/" + file.fileName;
        var ext = file.ext;
        var id = file.id;
        var img;
        if (isImage(ext)) {
            img = "<a href='" + path + "'><img src='" + path + "' width='50' /></a>";
        } else {
            img = "<a href='" + path + "'>NOT IMG</a>";
        }
        obj
                .append("<tr id='row" + id + "'><td onclick='focusPath("
                        + id
                        + ")' style='cursor:pointer'>"
                        + id
                        + "</td>"
                        + "<td>"
                        + img
                        + "</td><td><input id='path" + id + "' value='" + path + "' /></td><td id='ext" + id + "'>"
                        + ext
                        + "</td><td>"
                        + file.size
                        + "</td><td><input type='button' value='DEL' onclick='deleteFile("
                        + id
                        + ")' /></td>"
                        + "<td><input type='button' value='INS' onclick='insertHtml("
                        + id + ")' /></td></tr>");
    }

    function deleteFile(id) {
        if (confirm("Are you sure to delete?")) {
            $.post(contextPath + "upload/delete/" + id, function(res) {
                if (res.res == "ok") {
                    $("#row" + id).fadeOut();
                } else {
                    alert("delete failed");
                }
            }, 'json');
        }
    }

    function focusPath(id) {
        $("#path" + id).focus();
    }

    function insertHtml(id) {
        var ext = $("#ext" + id).html();
        var path = $("#path" + id).attr("value");
        var html = "<a href='" + path + "'>";
        var body = $("#field-body");
        if (isImage(ext)) {
            html += "<img src='" + path+ "' />";
        } else {
            html += path;
        }
        html += "</a>";
        var text = body.text() + "\n" + html;
        body.text(text);
    }

    // init
    $(function() {
        $.get(contextPath + "upload/view/1/1", function(res) {
            if (res.res == "ok") {
                var files = res.files;
                var target = $('#upload-result');
                for (i in files) {
                    appendRow(target, res.uploadDir, files[i]);
                }
            } else {
                alert("get files failed");
            }
        }, 'json');
        $("#upload-file").change(function() {
            $(this).upload(contextPath + "upload/post", function(res) {
                if (res.res == "ok") {
                    appendRow($("#upload-result"), res.uploadDir, res.file);
                } else {
                    alert("upload failed");
                }
                $("#upload-file").val("");
            }, 'json');
        });
        $("#uploader").click(function() {
            $("#uploader-content").slideToggle();
        });
        $("#preview").click(function() {
            $("#preview-content").slideToggle();
        });
    });
</script>
        <h2><a class="article-title" href="<c:url value="/entry/view/id/${f:h(entry.id)}/title/${f:u(entry.title)}/" />">${f:h(entry.title)}</a></h2>
        <form:form method="POST" name="edit-form" modelAttribute="entry">
            <spring:hasBindErrors name="entry">
                <ul>
                    <c:forEach items="${errors.globalErrors}"
                        var="error">
                        <spring:message code="${error.code}" />
                    </c:forEach>
                </ul>
            </spring:hasBindErrors>
            <ul>
                <li><label for="field-title" class="desc">title</label>
                <div><form:input path="title" id="field-title"
                    cssClass="field text medium"
                    cssErrorClass="field-error" /> <form:errors
                    path="title" cssClass="field-error" /><form:hidden
                    path="id" id="field-title" /></div>
                </li>
                <li><label for="field-category" class="desc">category</label>
                <div><form:input path="category"
                    id="field-category" cssClass="field text medium"
                    cssErrorClass="field-error" /> <form:errors
                    path="category" cssClass="field-error" /></div>
                </li>
                <li><label for="field-body" class="desc">body</label>
                <div><form:textarea path="content" id="field-body"
                    cssClass="textarea" cssErrorClass="field-error" />
                <form:errors path="content" cssClass="field-error" /></div>
                </li>
                <li><label for="field-created-at" class="desc">created_at</label>
                <div><form:input path="createdAt"
                    id="field-created-at" cssClass="field text medium"
                    cssErrorClass="field-error" /> <form:errors
                    path="createdAt" cssClass="field-error" /></div>
                </li>
                <li><label for="field-updated-at" class="desc">updated_at</label>
                <div><form:input path="updatedAt"
                    id="field-updated-at" cssClass="field text medium"
                    cssErrorClass="field-error" /> <form:errors
                    path="updatedAt" cssClass="field-error" /></div>
                </li>
                <li><label for="field-update-date" class="desc">update_date</label>
                <div><form:checkbox path="updateDate"
                    id="field-update-date" cssClass="field checkbox"
                    cssErrorClass="field-error" /> <form:errors
                    path="updateDate" cssClass="field-error" /></div>
                </li>
                <li class="buttons"><input name="submit"
                    type="submit" value="submit" /></li>
            </ul>
        </form:form>
        <hr>
        <h3 id="uploader" style="cursor: pointer">Uploader</h3>
        <div id="uploader-content" style="display: none"><input
            name="file" type="file" id="upload-file" />
        <table id="upload-result">
            <tr>
                <th>ID</th>
                <th>IMG</th>
                <th>PATH</th>
                <th>EXT</th>
                <th>SIZE</th>
                <th>DEL</th>
                <th>INS</th>
            </tr>
        </table>
        </div>
        <hr>
        <h3 id="preview" style="cursor: pointer">Live Preview</h3>
        <div id="preview-content" class="wmd-preview"></div>
        </div>
    </c:param>
</c:import>
