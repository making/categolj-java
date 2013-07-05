mk.ns('categolj.entry');


(function() {
  categolj.entry.Form = function() {
    this.preview = $('#wmd-preview');
  };

  categolj.entry.Form.prototype.initialize = function() {
    var self = this;
    //
    $.get(categolj.contextRoot + '/upload/view/1/1', function(res) {
      if (res.res == 'ok') {
        var files = res.files;
        var target = $('#upload-result');
        for (var i in files) {
          appendUploadRow(target, res.uploadDir, files[i]);
        }
      } else {
        alert('get files failed');
      }
    }, 'json');

    // upload
    var uploadFile = $('#upload-file');
    uploadFile.change(function() {
      $(this).upload(categolj.contextRoot + '/upload/post', function(res) {
        if (res.res == 'ok') {
          appendUploadRow($('#upload-result'), res.uploadDir, res.file);
        } else {
          alert('upload failed');
        }
        uploadFile.val('');
      }, 'json');
    });
    $('#uploader').click(function() {
      $('#uploader-content').slideToggle();
    });
    $('[style="cursor:pointer"]').live('click', function() {
      var id = $(this).parent().data('row-id');
      focusPath(id);
    });
    $('input[value=INSERT]').live('click', function() {
      var id = $(this).parent().parent().data('row-id');
      self.insertUploadHtml(id);
    });
    $('input[value=DELETE]').live('click', function() {
      var id = $(this).parent().parent().data('row-id');
      self.deleteFile(id);
    });

    $('#preview').click(function() {
      self.preview.slideToggle();
    });
    
    $('textarea').autogrow();

    // autocomplete
    $('#field-category').autocomplete({
      source: categolj.contextRoot + '/category.json',
      minLength: 2
    });

    // search product
    var searchProductBtn = $('#search-product-btn'),
            term = $('#search-product-term');

    $('#search-product').click(function() {
      $('#search-product-content').slideToggle();
    });
    searchProductBtn.click(function() {
      if (term.val().length > 0) {
        var target = $('#search-product-content input[name=searchType]:checked').val() + '/' + term.val();
        search(target);
      }
    });
    $('#search-product-reset-btn').click(function() {
      term.val('');
      $('#search-product-result').html('');
      searchProductBtn.addClass('disabled');
    });
    $('td > button').live('click', function() {
      var product = $(this).data('product');
      self.insertProductHtml(product);
    });
    term.keyup(function() {
      if (this.value.length > 0) {
        searchProductBtn.removeClass('disabled');
      } else {
        searchProductBtn.addClass('disabled');
      }
    });

    // markdown
    var converter = new Markdown.Converter(); // not sanitize
    var editor = new Markdown.Editor(converter);
    editor.run();
    this.preview.bind('refresh', function() {
      editor.refreshPreview();
    });
  };

  categolj.entry.Form.prototype.insertUploadHtml = function(id) {
    var ext = $('#ext' + id).html();
    var path = $('#path' + id).attr('value');
    var html = "<a href='" + path + "'>";
    var body = $('#wmd-input');
    if (isImage(ext)) {
      html += "<img src='" + path + "' />";
    } else {
      html += path;
    }
    html += '</a>';
    var text = body.text() + '\n' + html;
    body.text(text);
    this.preview.trigger('refresh');
  };

  categolj.entry.Form.prototype.insertProductHtml = function(product) {
    var html = '<a href=\"' + product.link + ' \"><img src=\"' + product.imageLink + '\" title=\"' + product.title + '\" alt=\"' + product.title + '\"></a>';
    var body = $('#wmd-input');
    var text = body.text() + '\n' + html;
    body.text(text);
    this.preview.trigger('refresh');
  };

  categolj.entry.Form.prototype.deleteFile = function(id) {
    if (confirm('Are you sure to delete?')) {
      $.post(categolj.contextRoot + '/upload/delete/' + id, function(res) {
        if (res.res == 'ok') {
          $('#row' + id).fadeOut();
        } else {
          alert('delete failed');
        }
      }, 'json');
    }
  };

  var isImage = function(ext) {
    return ext == 'png' || ext == 'jpg' || ext == 'gif';
  };

  var focusPath = function(id) {
    $('#path' + id).focus();
  };

  var appendUploadRow = function(obj, uploadDir, file) {
    var path = categolj.contextRoot + '/' + uploadDir + '/' + file.fileName;
    var ext = file.ext;
    var id = file.id;
    var img;
    if (isImage(ext)) {
      img = "<a href='" + path + "'><img src='" + path + "' width='50' /></a>";
    } else {
      img = "<a href='" + path + "'>NOT IMG</a>";
    }
    obj.append("<tr id='row" + id + "' data-row-id='" + id + "'><td style='cursor:pointer'>" + id + '</td>' + '<td>' + img + "</td><td><input id='path" + id + "' value='" + path + "' /></td><td id='ext" + id + "'>" + ext + '</td><td>' + file.size + "</td><td><input type='button' class='btn btn-info' value='INSERT' /> <input type='button' class='btn btn-danger' value='DELETE' /></td></tr>");
  };


  var search = function(target) {
    var div = $('#search-product-result');
    div.html('<p><img src=\"' + categolj.contextRoot + '/resources/images/ajax-loader.gif\"></p>');
    $.get(categolj.contextRoot + '/apa/search/' + target, function(res) {
      if (res.error) {
        div.html("<div class='alert alert-error'><p><strong>Error!</strong> : " + res.error + '</p>' + '<p>Please check properties whose key starts with <code>categolj.aws</code> in <code>categolj.properties</code></p></div>');
      } else {
        var result = '<table class=\"table table-striped table-bordered table-condensed\"><tr><th>TITLE</th><th>IMG</th><th>DATE</th><th>AUTHOR</th><th>ACTION</th></tr>';
        var products = res.products;
        for (var i = 0; i < products.length; i++) {
          var product = products[i];
          result = result + "<td><a href='" + product.link + "'>" + product.title + '</a></td>' + "<td><a href='" + product.imageLink + "'><img width='50' src='" + product.imageLink + "'></a></td>" + '<td>' + product.publicationDate + '</td>' + '<td>' + product.authors + '</td>' + "<td><button class='btn btn-info' data-product='" + JSON.stringify(product) + "'>INSERT</button></td></tr>";
        }
        result = result + '</table>';
        div.html(result);
      }
    }, 'json');
  };

})();


