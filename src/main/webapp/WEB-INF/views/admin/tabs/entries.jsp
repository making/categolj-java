<p><a href="<c:url value="/entry/create" />" class="btn primary">CREATE NEW ENTRY</a></p>
<table id="entry-grid"></table>
<div id="entry-pager"></div>
<script>
$(document).ready(function() {
    var grid = $("#entry-grid");
    var lastSelected;
    var sidx = localStorage.getItem("sidx") || "updated-at";
    var sord = localStorage.getItem("sord") || "desc";
    var rows = localStorage.getItem("rows") || 10;
    var page = localStorage.getItem("page") || 1;
    grid.jqGrid({
        url : '<c:url value="/entry/json" />',
        datatype : "json",
        colNames : ["ID", "TITLE", "CATEGORY", "CREATED_AT", "UPDATED_AT"],
        colModel : [ {
            name : "id",
            width : 50
        }, {
            name : "title",
            width : 250
        }, 
        {
            name : "category",
            width : 250,
            sortable : false
        },
        {
            name : "createdAt",
            index : "created-at",
            width : 100
        }, {
            name : "updatedAt",
            index : "updated-at",
            width : 100
        }],
        height : "auto",
        page : page,
        rowNum : rows,
        rowList : [ 10, 20, 30 ],
        pager : "#entry-pager",
        viewrecords : true,
        sortorder : sord,
        sortname : sidx,
        editurl : "entry/update",
        onSelectRow : function(id) {
            if (id && id !== lastSelected) {
                grid.jqGrid('restoreRow', lastSelected);
                lastSelected = id;
            }
            location.href = '<c:url value="/" />' + "entry/edit/id/" + id + "/"
        },
        loadComplete : function() {
            page = parseInt($(".ui-pg-input").val());
            rows = parseInt($(".ui-pg-selbox").val());
            localStorage.setItem("page", page);
            localStorage.setItem("rows", rows);
            localStorage.setItem("sidx", sidx);
            localStorage.setItem("sord", sord)
        },
        onSortCol : function(col, index, order) {
            sidx = col.replace("At", "-at");
            sord = order;
        },
        jsonReader : {
            repeatitems : false
        }
    });
});
</script>