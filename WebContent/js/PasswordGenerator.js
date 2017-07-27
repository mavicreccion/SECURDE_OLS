/* source: https://codepen.io/anon/pen/YxKbvJ */

// Generate a password string
function randString(id){
  var dataSet = $(id).attr('data-character-set').split(',');  
  var possible = '';
  if($.inArray('a-z', dataSet) >= 0){
    possible += 'abcdefghijklmnopqrstuvwxyz';
  }
  if($.inArray('A-Z', dataSet) >= 0){
    possible += 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
  }
  if($.inArray('0-9', dataSet) >= 0){
    possible += '0123456789';
  }
  if($.inArray('#', dataSet) >= 0){
    possible += '![]{}()%&*$#^<>~@|';
  }
  var text = '';
  for(var i=0; i < $(id).attr('data-size'); i++) {
    text += possible.charAt(Math.floor(Math.random() * possible.length));
  }
  return text;
}


// Create a new password on page load
$('#auto-pass').each(function(){
  $(this).val(randString($(this)));
});

// Create a new password
$(".getNewPass").click(function(){
  var field = $('#auto-pass');
  field.val(randString(field));
});

/*
// Auto Select Pass On Focus
$('input[rel="gp"]').on("click", function () {
   $(this).select();
});
*/

/* HOW TO APPLY IN HTML :
 	<div class="form-group">
	  <label class="text-muted">Using all of the character sets:</label>
	  <div class="input-group">
	    <input type="text" class="form-control" rel="gp" data-size="6" data-character-set="a-z,A-Z,0-9,#">
	    <span class="input-group-btn"><button type="button" class="btn btn-default getNewPass"><span class="fa fa-refresh"></span></button></span>
	  </div>
	</div>
 */

