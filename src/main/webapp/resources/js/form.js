if (typeof(am) == "undefined") am = {};
if (typeof(am.ik)  == "undefined") am.ik = {};
if (typeof(am.ik.categolj)  == "undefined") am.ik.categolj = {};
if (typeof(am.ik.categolj.entry)  == "undefined") am.ik.categolj.entry = {};


am.ik.categolj.entry.Form = {
    contextRoot : "/",

    insertUploadHtml : function (id) {
        var ext = $("#ext" + id).html();
        var path = $("#path" + id).attr("value");
        var html = "<a href='" + path + "'>";
        var body = $("#wmd-input");
        if (am.ik.categolj.entry.Form.isImage(ext)) {
            html += "<img src='" + path + "' />";
        } else {
            html += path;
        }
        html += "</a>";
        var text = body.text() + "\n" + html;
        body.text(text);
        $("#wmd-preview").trigger("refresh");
    },
    
    isImage : function (ext) {
        return ext == "png" || ext == "jpg" || ext == "gif";
    },

    focusPath : function (id) {
        $("#path" + id).focus();
    },
    
    appendUploadRow : function (obj, uploadDir, file) {
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
                + "</td><td><input type='button' class='btn btn-info' value='INSERT' onclick='am.ik.categolj.entry.Form.insertUploadHtml(" + id
                + ")' /> <input type='button' class='btn btn-danger' value='DELETE' onclick='am.ik.categolj.entry.Form.deleteFile("
                + id + ")' /></td></tr>");
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
    
    search : function (target) {
        var div = $("#search-product-result");
        div.html("<p><img src=\""+ am.ik.categolj.entry.Form.contextRoot +"resources/images/ajax-loader.gif\"></p>");
        $.get(am.ik.categolj.entry.Form.contextRoot + "apa/search/" + target, function(res) {
            if (res.error) {
                div.html("<div class='alert alert-error'><p><strong>Error!</strong> : " + res.error + "</p>" 
                        + "<p>Please check properties whose key starts with <code>categolj.aws</code> in <code>categolj.properties</code></p></div>");
            } else {
                var result = "<table class=\"table table-striped table-bordered table-condensed\"><tr><th>TITLE</th><th>IMG</th><th>DATE</th><th>AUTHOR</th><th>ACTION</th></tr>";
                var products = res.products;
                for (var i = 0; i < products.length; i++) {
                    var product = products[i];
                    result = result + "<td><a href='" + product.link + "'>" + product.title +"</a></td>"
                            + "<td><a href='" + product.imageLink +"'><img width='50' src='" + product.imageLink +"'></a></td>" 
                            + "<td>" + product.publicationDate +"</td>" 
                            + "<td>" + product.authors +"</td>" 
                            + "<td><button class='btn btn-info' onclick='am.ik.categolj.entry.Form.insertProductHtml(" 
                            + JSON.stringify(product) + ")'>INSERT</button></td></tr>";
                }
                result = result + "</table>";
                div.html(result);
            }
        }, "json");
    },
    
    insertProductHtml : function (product) {
        var html = "<a href=\"" + product.link + " \"><img src=\"" + product.imageLink + "\" title=\"" + product.title + "\" alt=\"" + product.title + "\"></a>";
        var body = $("#wmd-input");
        var text = body.text() + "\n" + html;
        body.text(text);
        $("#wmd-preview").trigger("refresh");
    },
    
    init : function(contextRoot) {
        am.ik.categolj.entry.Form.contextRoot = contextRoot;
        
        $.get(contextRoot + "upload/view/1/1", function(res) {
            if (res.res == "ok") {
                var files = res.files;
                var target = $('#upload-result');
                for (i in files) {
                    am.ik.categolj.entry.Form.appendUploadRow(target, res.uploadDir, files[i]);
                }
            } else {
                alert("get files failed");
            }
        }, 'json');
        $("#upload-file").change(function() {
            $(this).upload(contextRoot + "upload/post", function(res) {
                if (res.res == "ok") {
                    am.ik.categolj.entry.Form.appendUploadRow($("#upload-result"), res.uploadDir, res.file);
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
            $("#wmd-preview").slideToggle();
        });
        // autocomplete
        $("#field-category").autocomplete({
            source : contextRoot + "category.json",
            minLength: 2
        });
        // search product
        $("#search-product").click(function() {
            $("#search-product-content").slideToggle();
        });
        $("#search-product-btn").click(function() {
            var term = $("#search-product-term");
            if (term.val().length > 0) {
                var target = $("#search-product-content input[name=searchType]:checked").val() + "/" + term.val();
                am.ik.categolj.entry.Form.search(target);
            }
        });
        $("#search-product-reset-btn").click(function() {
            $("#search-product-term").val("")
            $("#search-product-result").html("");
            $("#search-product-btn").addClass("disabled");
        });
        $("#search-product-term").keyup(function() {
            if(this.value.length > 0) {
                $("#search-product-btn").removeClass("disabled");
            } else {
                $("#search-product-btn").addClass("disabled");
            }
        });
        // markdown
        var converter = new Markdown.Converter(); // not sanitize
        var editor = new Markdown.Editor(converter);
        editor.run();
        $("#wmd-preview").bind("refresh", function() {
            editor.refreshPreview();
        });
    }
}