$(document).ready(function($) {
	$("#buscarDiscProfCurso").click(function() {
		console.log("teste1")
		idProfessor = $("#valueIdProfessor").val()
		idCurso = $("#curso").val() 
		if(idProfessor !== null && idProfessor !== undefined && idCurso !== null && idCurso !== undefined ){
		var element = document.getElementById('formDiscProf');
		element.innerHTML += "<input type=\"hidden\" name=\"idProfessor\" value=\""+ idProfessor +"\">"
		element.innerHTML += "<input type=\"hidden\" name=\"idCurso\" value=\""+ idCurso +"\">"
		$("#conteudo").append("<%= discplinasdisponiveis = FacadeSolicitacoes.buscarDiscPorCursoEProfDif(Integer.valueOf("+idCurso+"), Integer.valueOf("+idProfessor+")); %>")
		
		}
	})
});