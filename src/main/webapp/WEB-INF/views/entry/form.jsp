<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="" />
    <c:param name="content">
        <div class="contents edit-form">
            <script type="text/javascript">
                                                    wmd_options = {
                                                        output : 'Markdown'
                                                    };
                                                </script>
            <script src='<c:url value="/resources/js/wmd/wmd.js" />'
                type='text/javascript'></script>
            <script
                src='<c:url value="/resources/js/jquery-1.4.4.min.js" />'
                type='text/javascript'></script>
            <script
                src='<c:url value="/resources/js/jquery.upload-1.0.2.modified.min.js" />'
                type='text/javascript'></script>
            <script src='<c:url value="/resources/js/form.js" />'
                type='text/javascript'></script>
            <script type='text/javascript'></script>
            <h2>
                <a class="article-title"
                    href="<c:url value="/entry/view/id/${f:h(entry.id)}/title/${f:u(entry.title)}/" />">${f:h(entry.title)}</a>
            </h2>
            <form:form method="POST" name="edit-form"
                modelAttribute="entry">
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
                        <div>
                            <form:input path="title" id="field-title"
                                cssClass="field text medium"
                                cssErrorClass="field-error" />
                            <form:errors path="title"
                                cssClass="field-error" />
                            <form:hidden path="id" id="field-title" />
                        </div></li>
                    <li><label for="field-category" class="desc">category</label>
                        <div>
                            <form:input path="category"
                                id="field-category"
                                cssClass="field text medium"
                                cssErrorClass="field-error" />
                            <form:errors path="category"
                                cssClass="field-error" />
                        </div></li>
                    <li><label for="field-body" class="desc">body</label>
                        <div>
                            <form:textarea path="content"
                                id="field-body" cssClass="textarea"
                                cssErrorClass="field-error" />
                            <form:errors path="content"
                                cssClass="field-error" />
                        </div></li>
                    <li><label for="field-created-at" class="desc">created_at</label>
                        <div>
                            <form:input path="createdAt"
                                id="field-created-at"
                                cssClass="field text medium"
                                cssErrorClass="field-error" />
                            <form:errors path="createdAt"
                                cssClass="field-error" />
                        </div></li>
                    <li><label for="field-updated-at" class="desc">updated_at</label>
                        <div>
                            <form:input path="updatedAt"
                                id="field-updated-at"
                                cssClass="field text medium"
                                cssErrorClass="field-error" />
                            <form:errors path="updatedAt"
                                cssClass="field-error" />
                        </div></li>
                    <li><label for="field-update-date" class="desc">update_date</label>
                        <div>
                            <form:checkbox path="updateDate"
                                id="field-update-date"
                                cssClass="field checkbox"
                                cssErrorClass="field-error" />
                            <form:errors path="updateDate"
                                cssClass="field-error" />
                        </div></li>
                    <li class="buttons"><input name="submit"
                        type="submit" value="submit" />
                    </li>
                </ul>
            </form:form>
            <hr>
            <h3 id="uploader" style="cursor: pointer">Uploader</h3>
            <div id="uploader-content" style="display: none">
                <input name="file" type="file" id="upload-file" />
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
