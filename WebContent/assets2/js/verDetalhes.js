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
				$("#id").html("ID: "+data.id);
				$("#tipoSolicitacao").html("Tipo Solicitação: "+data.tipoSolicitacao);
				$("#dataProva").html("Data da Prova: "+data.dataProva.day+"/"+data.dataProva.month+"/"+data.dataProva.year);
				$("#dataSolicitacao").html("Data da Solicitação: "+data.dataSolicitacao.day+"/"+data.dataSolicitacao.month+"/"+data.dataSolicitacao.year);
				console.log(data.dataSolicitacao);
				$("#matricula").html("Matricula: "+data.aluno.matricula);
			}
		});
	});

});