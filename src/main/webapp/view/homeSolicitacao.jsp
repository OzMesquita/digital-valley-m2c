
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


</body>

<script type="text/javascript">
	var perfil = $("#perfil");
	perfil.on("click", function(event) {
		perfil.css('background-color', 'steelblue');
	});
</script>
</html>