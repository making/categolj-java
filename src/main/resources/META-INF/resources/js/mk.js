// namespace
var mk;
if (!mk) {
  mk = {};
}

(function($) {
  var messages = {};

  mk.ns = function(str) {
    var ns = str.split('.');
    var here = window;
    for (var i = 0, l = ns.length; i < l; i++) {
      if (typeof(here[ns[i]]) == 'undefined') here[ns[i]] = {};
      here = here[ns[i]];
    }
    return here;
  };

  mk.loadTemplate = function(url) {

  };

  mk.loadMessage = function(url) {
    $.get(url, function(data) {
      $.extend(true, messages, data);
    });
  };

  mk.msg = function(key) {
    return messages[key];
  };
})(jQuery);


