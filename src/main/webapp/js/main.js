jQuery(document).ready(function(){
	reloadMolecules();
	$('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
        	reloadMolecules();
        }
    });
});

function reloadMolecules() {
	var container = jQuery("molecules");
	container.empty();
	jQuery.ajax({
		url: "MoleculeData",
		dataType: "json",
		success: function(data){
			var li = jQuery("<li id='"+data.id+"'>"+data.filename+"</li>");
			li.data('data', data);
			container.append(li);
		}
	});
}