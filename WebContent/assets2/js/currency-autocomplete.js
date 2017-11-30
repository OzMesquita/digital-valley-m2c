$(function() {
	$("#name-complete").autocomplete({
		source: "prof-complete",
		select: function(event, ui ){
			$("#name-complete").val(ui.item.nome);
		}
	});
});