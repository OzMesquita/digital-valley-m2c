$(function() {
	var $btnDetalhes = $(".btn_detalhes");
	$btnDetalhes.on('click', function() {
		$.ajax({
			type : "POST",
			url : "/digital-valley-m2c/view/detalhes",
			data : {
				id : $btnDetalhes.attr('id')
			},
			success : function(data) {
				$("id").text(data.id);
			}
		});
	});

});