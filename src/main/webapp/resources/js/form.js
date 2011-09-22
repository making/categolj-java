if (typeof(am) == "undefined") am = {};
if (typeof(am.ik)  == "undefined") am.ik = {};
if (typeof(am.ik.categolj)  == "undefined") am.ik.categolj = {};
if (typeof(am.ik.categolj.entry)  == "undefined") am.ik.categolj.entry = {};


am.ik.categolj.entry.Form = {
    contextRoot : "/",

    insertHtml : function (id) {
        var ext = $("#ext" + id).html();
        var path = $("#path" + id).attr("value");
        var html = "<a href='" + path + "'>";
        var body = $("#field-content");
        if (am.ik.categolj.entry.Form.isImage(ext)) {
            html += "<img src='" + path + "' />";
        } else {
            html += path;
        }
        html += "</a>";
        var text = body.text() + "\n" + html;
        body.text(text);
    },
    
    isImage : function (ext) {
        return ext == "png" || ext == "jpg" || ext == "gif";
    },

    focusPath : function (id) {
        $("#path" + id).focus();
    },
    
    appendRow : function (obj, uploadDir, file) {
        var path = am.ik.categolj.entry.Form.contextRoot + uploadDir + "/" + file.fileName;
        var ext = file.ext;
        var id = file.id;
        var img;
        if (am.ik.categolj.entry.Form.isImage(ext)) {
            img = "<a href='" + path + "'><img src='" + path
                    + "' width='50' /></a>";
        } else {
            img = "<a href='" + path + "'>NOT IMG</a>";
        }
        obj.append("<tr id='row" + id + "'><td onclick='am.ik.categolj.entry.Form.focusPath(" + id
                + ")' style='cursor:pointer'>" + id + "</td>" + "<td>" + img
                + "</td><td><input id='path" + id + "' value='" + path
                + "' /></td><td id='ext" + id + "'>" + ext + "</td><td>"
                + file.size
                + "</td><td><input type='button' class='btn danger' value='DEL' onclick='am.ik.categolj.entry.Form.deleteFile("
                + id + ")' /></td>"
                + "<td><input type='button' class='btn' value='INS' onclick='am.ik.categolj.entry.Form.insertHtml(" + id
                + ")' /></td></tr>");
    },
    
    deleteFile : function (id) {
        if (confirm("Are you sure to delete?")) {
            $.post(am.ik.categolj.entry.Form.contextRoot + "upload/delete/" + id, function(res) {
                if (res.res == "ok") {
                    $("#row" + id).fadeOut();
                } else {
                    alert("delete failed");
                }
            }, 'json');
        }
    },
    
    init : function(contextRoot) {
        am.ik.categolj.entry.Form.contextRoot = contextRoot;
        
        $.get(contextRoot + "upload/view/1/1", function(res) {
            if (res.res == "ok") {
                var files = res.files;
                var target = $('#upload-result');
                for (i in files) {
                    am.ik.categolj.entry.Form.appendRow(target, res.uploadDir, files[i]);
                }
            } else {
                alert("get files failed");
            }
        }, 'json');
        $("#upload-file").change(function() {
            $(this).upload(contextRoot + "upload/post", function(res) {
                if (res.res == "ok") {
                    am.ik.categolj.entry.Form.appendRow($("#upload-result"), res.uploadDir, res.file);
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
        // autocomplete
        $("#field-category").autocomplete({
            source : contextRoot + "category.json",
            minLength: 2
        });
    }
}