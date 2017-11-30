/*jslint  browser: true, white: true, plusplus: true */
/*global $, countries */

$(function() {
	$('#autocompletemy').autocomplete({
		serviceUrl : 'prof-complete',
		onSelect : function(suggestion) {
			alert('You selected: ' + suggestion.value + ', '
							+ suggestion.data);
				}
	});
});

$(function(){
		$('#completeprofessor').autocomplete({
			serviceUrl : 'prof-complete',
			onSelect : function(suggestion) {
				if(suggestion) {			
					$.getJSON('disc-complete?idProfessor='+suggestion.data, function(j){
						var options = '<option value="">Selecione uma Disciplina</option>';	
						for (var i = 0; i < j.length; i++) {
							options += '<option value="' + j[i].value+ '">' + j[i].value + '</option>';
						}	
						$('#complete_disciplinas').html(options).show();
					});
				} else {
					$('#complete_disciplinas').html('<option value="">Selecione uma Disciplina</option>');
				}
			},
		});
		
});