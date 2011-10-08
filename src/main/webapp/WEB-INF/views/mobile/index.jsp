<c:import url="/WEB-INF/views/mobile/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" />
    <c:param name="content">
<%--         <form action="<c:url value="/m/search" />" method="get"> --%>
<!--             <input type="search" name="q"> -->
<!--         </form> -->
        <ul data-role="listview" data-inset="true" id="entry-list">
            <c:forEach var="entry" items="${entryList}">
                <li><a href="<c:url value="/m/entry/view/id/" />${entry.id}">${entry.title}</a></li>
            </c:forEach>
        </ul>
        <button data-icon="forward" id="forward-next">NEXT</button>
        <script type="text/javascript">
        $(document).ready(function () {
            var listView = $("#entry-list");
            var next = $("#forward-next");
            var page = 1;
            var count = listView.children().size();
            next.click(function () {
                $.mobile.showPageLoadingMsg();
                page++;
                $.ajax({
                    url : "<c:url value="/entry/json" />?page=" + page + "&rows=" + count + "&sidx=updated-at&sord=desc&_search=false",
                    dataType : "json",
                    success : function (data) {
                        if (data.page == data.total) {
                            next.attr("disabled", "disabled");
                        }
                        var list = data.rows;
                        var appendHtml = "";
                        for (var i = 0; i < list.length; i++) {
                            var e = list[i];
                            appendHtml = appendHtml + '<li><a href="<c:url value="/m/entry/view/id/" />' + e.id + '">' + e.title + '</a></li>';
                        }
                        listView.append(appendHtml).listview("refresh");
                        $.mobile.hidePageLoadingMsg();
                    },
                    error : function (data) {
                        $.mobile.hidePageLoadingMsg();
                    }
                });
            });
        })
        </script>
    </c:param>
</c:import>