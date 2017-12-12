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
				$("#id").html("<b>ID:</b> "+data.id);
				$("#tipoSolicitacao").html("<b>Tipo Solicitação:</b> "+data.tipoSolicitacao);
				$("#dataProva").html("<b>Data da Prova:</b> "+data.dataEHoraProva.date.day+"/"+data.dataEHoraProva.date.month+"/"+data.dataEHoraProva.date.year);
				$("#dataSolicitacao").html("<b>Data da Solicitação:</b> "+data.dataSolicitacao.day+"/"+data.dataSolicitacao.month+"/"+data.dataSolicitacao.year);
				$("#justificativa").html("<b>Justificativa:</b> "+data.justificativa);
				$("#matricula").html("<b>Matricula:</b> "+data.aluno.matricula);
				$("#nome").html("<b>Nome:</b> "+data.aluno.nome);
				$("#siape").html("<b>Siape:</b> "+data.professor.siape);
				$("#nomeProfessor").html("<b>Nome:</b> "+data.professor.nome);
				$("#email").html("<b>Email:</b> "+data.professor.email);
				$("#disciplina").html("<b>Disciplina:</b> "+data.disciplina.nome);
				$("#curso").html("<b>Curso: </b>"+data.aluno.curso.nome);
				$('#gerarPDF').attr({
					   'href': "gerarPDFSolicitacao?id="+data.id
					});
				
			}
		});
	});

});