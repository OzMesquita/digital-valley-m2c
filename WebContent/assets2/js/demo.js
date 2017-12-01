/* 
 * https://www.devbridge.com/sourcery/components/jquery-autocomplete/ 
 *  */

$(function(){
	$('#completeprofessor').keypress(function(){
			$('#completeprofessor').autocomplete({
				minChars: 4,
				serviceUrl : 'prof-complete?nome='+this.value,
				onSelect : function(suggestion) {
					if(suggestion!=null) {			
						$.getJSON('disc-complete?idProfessor='+suggestion.data, function(j){
							if(j !== null){
								var options = '<option value="">Selecione uma Disciplina</option>';	
								for (var i = 0; i < j.length; i++) {
									options += '<option value="' + j[i].data+ '">' + j[i].value + '</option>';
								}	
								$('#complete_disciplinas').html(options).show();
							}else{
								$('#complete_disciplinas').html('<option value="">Selecione uma Disciplina</option>').show();
							}
						});
					} else {
						$('#complete_disciplinas').html('<option value="">Selecione uma Disciplina</option>').show();
					}
				},
			});
	});
});