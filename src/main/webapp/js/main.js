jQuery(document).ready(function(){
	reloadMolecules();
	$('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
        	console.log("uploaded",data);
        	reloadMolecules();
        }
    });
//	jQuery("ul#molecules li").on("click",function(){
//		loadMolecule($(this).data('data'));
//	});
	
});

function loadMolecule(data){
	jQuery('img').attr("src", "MoleculeData?id="+data.id).attr("alt", data.name);
}

function reloadMolecules() {
	var container = jQuery("ul#molecules");
	container.empty();
	jQuery.ajax({
		url: "MoleculeData",
		dataType: "json",
		success: function(data){
			console.log(data);
			jQuery.each(data, function(k,v){
				var li = jQuery("<li id='"+v.id+"'>"+v.name+"</li>");
				li.data('data', v);
				li.click(function(){
					loadMolecule(v);
				});
				container.append(li);	
			});
			
		}
	});
}