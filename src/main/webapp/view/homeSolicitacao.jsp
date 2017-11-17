
<%@page import="model.Aluno"%>
<div class="row">
	<div
		class=" col-md-6 col-md-offset-3 sem-padding-left sem-padding-right"
		id="formulario_solicitacao">
		<div class="wizard-navigation">
			<ul class="nav nav-pills tab-menu" id="tipo_solicitacao">
				<li class="active  col-md-6 sem-padding-left sem-padding-right" id="tab-esquerda"><a
				 href="#segunda_chamada" data-toggle="tab" aria-expanded="true">SEGUNDA
						CHAMADA</a></li>
						
				<li class="col-md-6 sem-padding-left sem-padding-right"
					id="tab-direita"><a href="#recorrecao" data-toggle="tab"
					aria-expanded="false">RECORREÇÃO</a></li>
			</ul>
			<div class="moving-tab"
				style="width: 375px; transform: translate3d(0px, 0px, 0px); transition: transform 0s;">
			</div>

			<%
				Aluno aluno = null;
				if (usuario.getPessoa() instanceof Aluno) {
					aluno = (Aluno) usuario.getPessoa();

				}
			%>


			<div class="tab-content">
				<div class="tab-pane active col-md-12" id="segunda_chamada">
					<div class="row">
						<form action="salvarSolicitacao" >
						<input value="Segunda Chamada" type="hidden" name="tipoS"> 
							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputName">Aluno:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-10 sem-padding-left">
										<input required class="form-control " type="text" name="inputName"
											value="<%=aluno != null ? aluno.getNome() : ""%>"
											<%if (aluno != null) {%> <%="disabled"%> <%}%> disabled="disabled">
									</div>
									<!--col-md-10-->
								</div>
								<!--form-group col-md-12-->
							</div>
							<!--row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputMatricula">Matrícula:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-2 sem-padding-left">
										<input required class="form-control" type="text"
											value="<%=aluno != null ? aluno.getMatricula() : ""%>"
											<%if (aluno != null) {%> <%="disabled"%> <%}%> id="matricula"
											name="inputMatricula"
											placeholder="Digite a Matricula do Aluno">
									</div>
									<!--col-md-2-->
									<div class="col-md-1">
										<label for="inputCurso">Curso:</label>
									</div>
									<!--col-md-1-->
									<div class="col-md-7 ">
										<input required class="form-control" type="text" name="inputCurso"
											value="<%=aluno != null ? aluno.getCurso().getNome() : ""%>"
											<%if (aluno != null) {%> <%="disabled"%> <%}%> disabled="disabled">
									</div>
									<!--col-md-7-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputProfessor">Professor:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-10 sem-padding-left">
										<input required class="form-control" type="text" name="inputProfessor"
											placeholder="Nome do professor">
									</div>
									<!--col-md-10-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 sem-padding-right">												
										<label for="selectDisciplina" class="margem-esquerda control-label">Disciplina:</label>
									</div><!--col-md-2-->
									<div class="col-md-6 sem-padding-left form-group">
										<select id="selectDisciplina">		
											<option value="" disabled="disabled" selected="selected">Selecione uma Disciplina</option>
											<option value="">2</option>
											<option value="">3</option>
											<option value="">4</option>
											<option value="">5</option>
										</select><!--col-md-6-->
									</div><!-- col-md-6 sem-padding-left -->
									
									<div class="col-md-2 sem-padding-right">
										<label for="inputDataProva">Data da Prova:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-2  sem-padding-left">
										<input required class="form-control inputData" 
											id="inputDataProva" placeholder="dd/mm/aaaa" maxlength="10"
											autocomplete="off" type="text" name="inputDataProva">
									</div>
									<!--col-md-2-->
								</div><!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 sem-padding-right">
										<label for="justificativa" class="margem-esquerda">Justificativa:</label>
									</div>
									<div class="col-md-10 sem-padding-left">
										<textarea required id="textarea" name="justificativa" maxlength="280"
											placeholder="Digite sua justificativa da solicitação"></textarea>
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





				<div class="tab-pane col-md-12" id="recorrecao">
					<div class="row">
						<form action="salvarSolicitacao">
						<input value="Recorrecao" type="hidden" name="tipoR">
							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputName">Aluno:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-10 sem-padding-left">
										<input class="form-control " type="text" name="inputName"
											value="<%=aluno != null ? aluno.getNome() : ""%>"
											<%if (aluno != null) {%> <%="disabled"%> <%}%> disabled="disabled">
									</div>
									<!--col-md-10-->
								</div>
								<!--form-group col-md-12-->
							</div>
							<!--row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputMatricula">Matrícula:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-2 sem-padding-left">
										<input class="form-control" type="text"
											value="<%=aluno != null ? aluno.getMatricula() : ""%>"
											<%if (aluno != null) {%> <%="disabled"%> <%}%> id="matricula"
											name="inputMatricula"
											placeholder="Digite a Matricula do Aluno">
									</div>
									<!--col-md-2-->
									<div class="col-md-1">
										<label for="inputCurso">Curso:</label>
									</div>
									<!--col-md-1-->
									<div class="col-md-7 ">
										<input class="form-control" type="text" name="inputCurso"
											value="<%=aluno != null ? aluno.getCurso().getNome() : ""%>"
											<%if (aluno != null) {%> <%="disabled"%> <%}%> disabled="disabled">
									</div>
									<!--col-md-7-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2">
										<label for="inputProfessor">Professor:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-10 sem-padding-left">
										<input class="form-control" type="text" name="inputProfessor"
											placeholder="Nome do professor" >
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
																		<div class="col-md-6 sem-padding-left form-group">
										<select id="selectDisciplina">		
											<option value="" disabled="disabled" selected="selected">Selecione uma Disciplina</option>
											<option value="">2</option>
											<option value="">3</option>
											<option value="">4</option>
											<option value="">5</option>
										</select><!--col-md-6-->
									</div><!-- col-md-6 sem-padding-left -->

									<div class="col-md-2 sem-padding-right">
										<label for="inputDataProva">Data da Prova:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-2  sem-padding-left">
										<input class="form-control inputData" 
											id="inputDataProva" placeholder="dd/mm/aaaa" maxlength="10"
											autocomplete="off" type="text" name="inputDataProva">
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
										<textarea required id="textarea" name="justificativa" maxlength="280"
											placeholder="Digite sua justificativa da solicitação"></textarea>
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

