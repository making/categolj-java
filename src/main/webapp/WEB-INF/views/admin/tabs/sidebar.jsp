<form>
<fieldset>
    <legend>Links</legend>
    <table class="zebra-striped" id="links">
        <tr><th>NAME</th><th>URL</th><th>ACTION</th></tr>
        <tr><td><input name="name[]"></td><td><input name="url[]"></td><td><button class="btn primary">SAVE</button><button class="btn danger">DELETE</button></td></tr>
    </table>
</fieldset>
</form>
    <button class="btn" id="addLink">ADD LINK</button>

<form>
<fieldset>
    <legend>Free Contents</legend>    
    <div class="clearfix">
        <div class="input">
            <textarea name="contents" class="span10" id="free-contetns"></textarea>
        </div>
    </div>
</fieldset>
</form>

<script type="text/javascript">
$(document).ready(function () {
    var links = $("#links");
    $("#addLink").click(function () {
        var row = $("<tr><td><input name=\"name[]\"></td><td><input name=\"url[]\"></td><td><button class=\"btn primary\">SAVE</button><button class=\"btn danger\">DELETE</button></td></tr>");
        links.append(row);
    });
});
</script>