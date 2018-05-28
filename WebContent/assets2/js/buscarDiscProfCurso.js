$(document).ready(function() {
                $('#buscarDiscProfCurso').click(function() {
                	var e =	document.getElementById("valueIdProfessor");
                    var idProfessor = e.value;
                    var a = document.getElementById("curso");
                    var idCurso= a.selectedIndex;
                    var data = {idProfessor:idProfessor, idCurso:idCurso};
                    if(data.idProfessor !== "" && idCurso !== ""){
                    	$.post('BuscaDiscProfCurso',data,function(response){
	                    	var resposta = response;
	                    	var informacoes = "<input type='hidden' name='idProfessor' value='"+idProfessor+"'";	                    	
	                    	$("#tabela").empty();
	                    	$.each(resposta, function(i, item){
	                    		var variavel = "<tr><td><input type='checkbox' id='"+item.id+"' name='disciplina' value='"
	                    		+item.id+"'</td><td>"+item.nome+"</td></tr>"+"<input type='hidden' name='idProfessor' value='"+idProfessor+"'";
	                    		$("#tabela").append(variavel);	                    		
	                    	});
	                    	
	                    });
                	}
                });
            });
