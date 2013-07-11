/**
 * init
 */
jQuery(document).ready(function(){
	reloadMolecules();
	$('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
        	reloadMolecules();
        	loadMolecule(data.result);
        }
    });

	
});

/**
 * Load molecule image
 * @param data
 */
function loadMolecule(data){
	jQuery("ul#molecules").find('li').removeClass("active");
	jQuery("ul#molecules").find('#'+data.id).addClass("active");
	jQuery('img').attr("src", "MoleculeData?id="+data.id).attr("alt", data.name);
}

/**
 * Reload list of molecule infos and refresh nav container
 */
function reloadMolecules() {
	var container = jQuery("ul#molecules");
	container.empty();
	jQuery.ajax({
		url: "MoleculeData",
		dataType: "json",
		success: function(data){
			jQuery.each(data, function(k,v){
				var li = jQuery("<li style='cursor:pointer;' id='"+v.id+"'><a href='#'>"+v.name+"("+v.formula+")</a></li>");
				li.data('data', v);
				li.find('a').click(function(e){
					e.preventDefault();
					e.stopPropagation();
					loadMolecule(v);
				});
				container.append(li);	
			});
			
		}
	});
}