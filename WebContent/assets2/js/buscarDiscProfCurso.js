$(document).ready(function() {
                $('#buscarDiscProfCurso').click(function() {
                	var e =	document.getElementById("valueIdProfessor");
                    var idProfessor = e.value;
                    var a = document.getElementById("curso");
                    var idCurso= a.selectedIndex;
                    var data = {idProfessor:idProfessor, idCurso:idCurso};
                    if(true){
                    	$.post('BuscaDiscProfCurso',data,function(response){
	                    	var resposta = response;
	                    	$("#tabela").empty();
	                    	$.each(resposta, function(i, item){
	                    		var variavel = "<tr><td><input type='checkbox' id='"+item.id+"' name='disciplina' value='"
	                    		+item.nome+"'</td><td>"+item.nome+"</td></tr>";
	                    		$("#tabela").append(variavel);	                    		
	                    	});
	                    	
	                    });
                	}
                });
            });
