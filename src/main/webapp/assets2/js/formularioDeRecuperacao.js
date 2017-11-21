$(document).ready(function($) {
	$("#formulario_recuperar_senha_servidor").hide();	
	$("#btnFormAluno").click(function() {
		$("#formulario_recuperar_senha_servidor").hide();
		$("#formulario_recuperar_senha_aluno").show();
		$("#btnFormServidor").removeClass("active");
		$("#btnFormAluno").addClass("active");
	});
	$("#btnFormServidor").click(function() {
		$("#formulario_recuperar_senha_servidor").show();
		$("#formulario_recuperar_senha_aluno").hide();
		$("#btnFormAluno").removeClass("active");
		$("#btnFormServidor").addClass("active");
	});
});