var $j = jQuery.noConflict();
(function($) {
  $j.fn.clearForm = function() {
    return this.each(function() {
      var type = this.type, tag = this.tagName.toLowerCase();
      if (tag == 'form')
        return $j(':input',this).clearForm();
      if (type == 'text' || type == 'password' || tag == 'textarea' || tag == 'file')
        this.value = '';
      else if (type == 'checkbox' || type == 'radio')
        this.checked = false;
      else if (tag == 'select')
        this.selectedIndex = -1;
    });
  };
})(jQuery);;

