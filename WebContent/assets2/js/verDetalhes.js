$(function() {
	var $btnDetalhes = $(".btn_detalhes");
	$btnDetalhes.on('click', function($btnDetalhes) {
		$.ajax({
			type : "POST",
			url : "/digital-valley-m2c/view/detalhes",
			data : {
				id : $btnDetalhes.currentTarget.attributes[2].nodeValue
			},
			success : function(data) {
				$("#id").html("ID: "+data.id);
				$("#tipoSolicitacao").html("Tipo Solicitação: "+data.tipoSolicitacao);
				$("#dataProva").html("Data da Prova: "+data.dataProva.day+"/"+data.dataProva.month+"/"+data.dataProva.year);
				$("#dataSolicitacao").html("Data da Solicitação: "+data.dataSolicitacao.day+"/"+data.dataSolicitacao.month+"/"+data.dataSolicitacao.year);
				$("#justificativa").html("Justificativa: "+data.justificativa);
				$("#matricula").html("Matricula: "+data.aluno.matricula);
				$("#nome").html("Nome: "+data.aluno.nome);
				$("#siape").html("Siape: "+data.professor.siape);
				$("#nomeProfessor").html("Nome: "+data.professor.nome);
				$("#email").html("Email: "+data.professor.email);
				$("#disciplina").html("Disciplina: "+data.disciplina.nome);
				
				
				
				$("#curso").html("Curso: "+data.aluno.curso.nome);
			}
		});
	});

});