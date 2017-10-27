<DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Template</title>
</head>
<body>

	<header>

		<nav class="navbar navbar-default" id="menu">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand text-white" id="brand" href="#">SISTEMA</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav" id="menu-left">
					<!--   <li > <a class="navbar-brand" href=""><span class="glyphicon glyphicon-menu-hamburger" id="menu-show"></span> </a></li> -->
					<li><a href="#" class="text-white">Link <span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">Link</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><img src="img/img-perfil.png" id="img-perfil"></li>
					<li class="dropdown"><a id="perfil" href="#"
						class="dropdown-toggle text-white" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">Perfil<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a class="text-white" id="ver-perfil" href="#">Ver
									Perfil</a></li>
							<li><a class="text-white" id="sair" href="#">Sair</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

	</header>


	<div
		class="container col-md-offset-3 sem-padding-left sem-padding-right"
		id="formulario_solicitacao">
		<div class="wizard-navigation">
			<ul class="nav nav-pills">
				<li style="width: 49%;" class="active"><a href="#aluno"
					data-toggle="tab" aria-expanded="true">Segunda Chamada</a></li>
				<li style="width: 49%;" class=""><a href="#servidor"
					data-toggle="tab" aria-expanded="false">Recorreção</a></li>
			</ul>
			<div class="moving-tab"
				style="width: 375px; transform: translate3d(0px, 0px, 0px); transition: transform 0s;">
			</div>



			<div class="tab-content">
				<div class="tab-pane active" id="Segunda Chamada">
					<div class="row">
						<form>
							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputName" class="margem-esquerda">Aluno:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-10 sem-padding-left">
										<input class="form-control " type="text" name="inputName">
									</div>
									<!--col-md-10-->
								</div>
								<!--form-group col-md-12-->
							</div>
							<!--row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 ">
										<label for="inputMatricula" class="margem-esquerda">Matrícula:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-2 sem-padding-left">
										<input class="form-control" type="text" id="matricula"
											name="inputMatricula">
									</div>
									<!--col-md-2-->
									<div class="col-md-1">
										<label for="inputCurso">Curso:</label>
									</div>
									<!--col-md-1-->
									<div class="col-md-7 ">
										<input class="form-control" type="text" name="inputCurso">
									</div>
									<!--col-md-7-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputProfessor" class="margem-esquerda">Professor:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-10 sem-padding-left">
										<input class="form-control" type="text" name="inputProfessor">
									</div>
									<!--col-md-10-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 sem-padding-right">
										<label for="inputDisciplina" class="margem-esquerda">Disciplina:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-6 sem-padding-left">
										<input class="form-control" type="text" name="inputDisciplina">
									</div>
									<!--col-md-6-->

									<div class="col-md-2 sem-padding-right">
										<label for="inputDataProva">Data da Prova:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-2  sem-padding-left">
										<input class="form-control" type="Data" id="nascimento"
											name="inputDataProva">
									</div>
									<!--col-md-2-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 sem-padding-right">
										<label for="justificativa" class="margem-esquerda">Justificativa:</label>
									</div>
									<div class="col-md-10 sem-padding-left">
										<textarea id="textarea" name="justificativa"></textarea>
									</div>
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row -->
							<div align="center">
								<button type="submit" class="btn btn-primary">Enviar
									Solicitação</button>
							</div>
						</form>
					</div>
				</div>
				<div class="tab-pane" id="recorrecao">
					<div class="tab-pane active" id="Segunda Chamada">
						<div class="row">
							<form>
								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2">
											<label for="inputName" class="margem-esquerda">Aluno:</label>
										</div>
										<!--col-md-2-->
										<div class="col-md-10 sem-padding-left">
											<input class="form-control " type="text" name="inputName">
										</div>
										<!--col-md-10-->
									</div>
									<!--form-group col-md-12-->
								</div>
								<!--row-->

								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2 ">
											<label for="inputMatricula" class="margem-esquerda">Matrícula:</label>
										</div>
										<!--col-md-2-->
										<div class="col-md-2 sem-padding-left">
											<input class="form-control" type="text" id="matricula"
												name="inputMatricula">
										</div>
										<!--col-md-2-->
										<div class="col-md-1">
											<label for="inputCurso">Curso:</label>
										</div>
										<!--col-md-1-->
										<div class="col-md-7 ">
											<input class="form-control" type="text" name="inputCurso">
										</div>
										<!--col-md-7-->
									</div>
									<!-- form-group col-md-12 -->
								</div>
								<!-- row-->

								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2">
											<label for="inputProfessor" class="margem-esquerda">Professor:</label>
										</div>
										<!--col-md-2-->
										<div class="col-md-10 sem-padding-left">
											<input class="form-control" type="text" name="inputProfessor">
										</div>
										<!--col-md-10-->
									</div>
									<!-- form-group col-md-12 -->
								</div>
								<!-- row-->

								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2 sem-padding-right">
											<label for="inputDisciplina" class="margem-esquerda">Disciplina:</label>
										</div>
										<!--col-md-2-->
										<div class="col-md-6 sem-padding-left">
											<input class="form-control" type="text"
												name="inputDisciplina">
										</div>
										<!--col-md-6-->

										<div class="col-md-2 sem-padding-right">
											<label for="inputDataProva">Data da Prova:</label>
										</div>
										<!--col-md-2-->
										<div class="col-md-2  sem-padding-left">
											<input class="form-control" type="Data" id="nascimento"
												name="inputDataProva">
										</div>
										<!--col-md-2-->
									</div>
									<!-- form-group col-md-12 -->
								</div>
								<!-- row-->

								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2 sem-padding-right">
											<label for="justificativa" class="margem-esquerda">Justificativa:</label>
										</div>
										<div class="col-md-10 sem-padding-left">
											<textarea id="textarea" name="justificativa"></textarea>
										</div>
									</div>
									<!-- form-group col-md-12 -->
								</div>
								<!-- row -->
								<div align="center">
									<button type="submit" class="btn btn-primary">Enviar
										Solicitação</button>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>


	</div>
	<!-- container col-md-offset-3" id="formulario_solicitacao -->




	<footer>
		<div id="logos">
			<a href="http://n2s.russas.ufc.br" id="n2s" target="_blank"><img
				id="logo-n2s" src="img/n2s-logo.png" alt="N2S"></a> <a
				href="http://www.campusrussas.ufc.br" id="ufc" target="_blank"><img
				id="logo-ufc" src="img/brasao-ufc.png" alt="UFC"></a>
		</div>
	</footer>

</body>


<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	var perfil = $("#perfil");
	perfil.on("click", function(event) {
		perfil.css('background-color', 'steelblue');
	});
</script>
</html>