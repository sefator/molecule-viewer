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
        },
        fail: function(){
        	jQuery('.span10 > img').before(jQuery('<div class="alert alert-error"><button type="button" class="close" data-dismiss="alert">&times;</button><strong>Warning!</strong> File upload failed!</div>'));
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
	jQuery.ajax({
		url: "MoleculeData",
		dataType: "json",
		cache:false,
		success: function(data){
			//empty navigation container
			container.empty();
			//add all molecules to sidebar
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
			// select first molecule if no other is selected
			if (jQuery("ul#molecules").find('li.active').size()===0){
				jQuery("ul#molecules").find('li:first > a').click();
			}
			
		}
	});
}
