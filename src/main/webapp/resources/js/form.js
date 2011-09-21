function isImage(ext) {
    return ext == "png" || ext == "jpg" || ext == "gif";
}

function appendRow(obj, uploadDir, file) {
    var path = "../../" + uploadDir + "/" + file.fileName;
    var ext = file.ext;
    var id = file.id;
    var img;
    if (isImage(ext)) {
        img = "<a href='" + path + "'><img src='" + path
                + "' width='50' /></a>";
    } else {
        img = "<a href='" + path + "'>NOT IMG</a>";
    }
    obj.append("<tr id='row" + id + "'><td onclick='focusPath(" + id
            + ")' style='cursor:pointer'>" + id + "</td>" + "<td>" + img
            + "</td><td><input id='path" + id + "' value='" + path
            + "' /></td><td id='ext" + id + "'>" + ext + "</td><td>"
            + file.size
            + "</td><td><input type='button' value='DEL' onclick='deleteFile("
            + id + ")' /></td>"
            + "<td><input type='button' value='INS' onclick='insertHtml(" + id
            + ")' /></td></tr>");
}

function deleteFile(id) {
    if (confirm("Are you sure to delete?")) {
        $.post("../../" + "upload/delete/" + id, function(res) {
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
        html += "<img src='" + path + "' />";
    } else {
        html += path;
    }
    html += "</a>";
    var text = body.text() + "\n" + html;
    body.text(text);
}

// init
$(function() {
    $.get("../../" + "upload/view/1/1", function(res) {
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
        $(this).upload("../../" + "upload/post", function(res) {
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
})