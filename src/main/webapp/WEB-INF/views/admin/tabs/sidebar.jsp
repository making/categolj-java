<form>
  <fieldset>
    <legend>
      Links
    </legend>
    <table class="table table-striped table-bordered table-condensed" id="links">
      <tr>
        <th>
          NAME
        </th>
        <th>
          URL
        </th>
        <th>
          ACTION
        </th>
      </tr>
      <c:forEach items="${links}" var="l">
        <tr>
          <td>
            <input name="id" type="hidden" value="${f:h(l.id)}">
            <input name="name" value="${f:h(l.name)}">
          </td>
          <td>
            <input name="url" value="${f:h(l.url)}">
          </td>
          <td>
            <button class="save-link btn btn-primary">
              SAVE
            </button>
            <button class="delete-link btn btn-danger">
              DELETE
            </button>
          </td>
        </tr>
      </c:forEach>
    </table>
  </fieldset>
</form>
<button class="btn" id="add-link">
  ADD LINK
</button>

<form>
  <fieldset>
    <legend>
      Free Contents
    </legend>
    <div class="control-group">
      <div class="controls">
        <textarea name="contents" class="span8" id="free-contetns">
        </textarea>
      </div>
    </div>
  </fieldset>
</form>

<script type="text/javascript">
$(document).ready(

function() {
    var links = $("#links");
    $("#add-link").click(

    function() {
        var row = $("<tr><td><input name=\"name\"></td><td><input name=\"url\"></td><td><button class=\"save-link btn btn-primary\">SAVE</button> <button class=\"delete-link btn btn-danger\" disabled=\"disabled\">DELETE</button></td></tr>");
        links.append(row);
    });

    var update = function(target, action, callback) {
        var data = target.parent().parent().find("input").serialize();
        $.ajax({url: "${contextRoot}/admin/link/" + action,
                data: data,
                dataType: 'json',
                success: callback,
                error: function(data){
                	console.log("error=", data);
                }});
    };

    $(".save-link").live("click", function(e) {
        e.preventDefault();
        var target = $(e.currentTarget);
        update(
        target, "save", function(data) {
        	//console.log(data);
            if (data.result === "OK") {
                var elm = $("<input name='id' type='hidden' value='" + data.id + "'>");
                var pp = target.parent().parent();
                pp.append(elm);
                pp.find(".delete-link").attr("disabled", false);
                pp.effect('highlight');
            }
        });
        return false;
    });

    $(".delete-link").live("click", function(e) {
        e.preventDefault();
        var target = $(e.currentTarget);
        update(target, "delete", function(data) {
            if (data.result === "OK") {
                var e = pp = target.parent().parent();
                pp.hide();
            }
        });
        return false;
    });
    
});
              </script>
