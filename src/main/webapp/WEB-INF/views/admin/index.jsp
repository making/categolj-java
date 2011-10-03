<c:import url="/WEB-INF/views/admin/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" />
    <c:param name="content">
        <ul class="tabs">
            <li class="active"><a href="#home">Home</a></li>
            <li><a href="#entries">Entries</a></li>
            <li><a href="#users">Users</a></li>
            <li><a href="#sidebar">Side Bar</a></li>
            <li><a href="#settings">Settings</a></li>
        </ul>

        <div class="pill-content">
            <div class="active" id="home">
                <p>Hello World!</p>
            </div>
            <div id="entries">
                <c:import url="/WEB-INF/views/admin/tabs/entries.jsp" />
            </div>
            <div id="users">
                <c:import url="/WEB-INF/views/admin/tabs/users.jsp" />
            </div>
            <div id="sidebar">
                <c:import url="/WEB-INF/views/admin/tabs/sidebar.jsp" />
            </div>
            <div id="settings">
                <c:import url="/WEB-INF/views/admin/tabs/settings.jsp" />
            </div>
        </div>
        <script>
          $(function () {
            $('.tabs').tabs()
          })
        </script>
    </c:param>
</c:import>