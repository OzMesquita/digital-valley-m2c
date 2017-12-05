
<%@page import="model.Aluno"%>


<div class="row">
	<div id="page-wrapper">



		<div
			class=" col-md-6 col-md-offset-3 sem-padding-left sem-padding-right"
			id="formulario_solicitacao">



			<div class="wizard-navigation">
				<ul class="nav nav-pills tab-menu" id="tipo_solicitacao">
					<li class="active  col-md-6 sem-padding-left sem-padding-right"
						id="tab-esquerda"><a href="#segunda_chamada"
						data-toggle="tab" aria-expanded="true">SEGUNDA CHAMADA</a></li>

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

				<%
					if (session.getAttribute(Constantes.getSessionMsg()) != null) {
				%>
				<div class="alert alert-danger" role="alert">
					<%=session.getAttribute(Constantes.getSessionMsg())%>
				</div>
				<%
					session.setAttribute(Constantes.getSessionMsg(), null);
				%>

				<%
					}
				%>


				<div class="tab-content">
					<div class="tab-pane active col-md-12" id="segunda_chamada">
						<div class="row">
							<form action="salvarSolicitacao">
								<input value="Segunda Chamada" type="hidden" name="tipoS">
								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2">
											<label for="inputName">Aluno:</label>
										</div>
										<!--col-md-2-->
										<div class="col-md-10 sem-padding-left">
											<input required class="form-control " type="text"
												name="inputName"
												value="<%=aluno != null ? aluno.getNome() : ""%>"
												<%if (aluno != null) {%> <%="readonly"%> <%}%> readonly>
										</div>
										<!--col-md-10-->
									</div>
									<!--form-group col-md-12-->
								</div>
								<!--form-group col-md-12-->
						</div>
						<!--row-->

						<div class="row">
							<div class="form-group col-md-12 sem-padding-left">
								<div class="col-md-2">
									<label for="inputMatricula">Matrícula:</label>
								</div>
								<!--col-md-2-->
								<div class="col-md-2 sem-padding-left">
									<input required class="form-control matricula" type="text"
										value="<%=aluno != null ? aluno.getMatricula() : ""%>"
										id="matricula" name="matricula" placeholder="Matricula"
										<%if (aluno != null) {%> <%="readonly"%> <%}%>>
								</div>
								<!--col-md-2-->
								<div class="col-md-1">
									<label for="inputCurso">Curso:</label>
								</div>
								<!--col-md-1-->
								<div class="col-md-7 sem-padding-right">
									<input required class="form-control" type="text"
										name="inputCurso"
										value="<%=aluno != null ? aluno.getCurso().getNome() : ""%>"
										<%if (aluno != null) {%> <%="readonly"%> <%}%> readonly>
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
										<input required class="form-control biginput completeprofessor" type="text"
											name="inputProfessor"
											placeholder="Nome do professor">
										<input type="hidden" name="valueIdProfessor" class="valueIdProfessor"> 
									</div>
									<!--col-md-10-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 sem-padding-right">
										<label for="selectDisciplina"
											class="margem-esquerda control-label">Disciplina:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-6 sem-padding-left form-group">
										<select name="inputDisciplina" class="complete_disciplinas">
											<option value="" disabled="disabled" selected="selected">Selecione
												uma Disciplina</option>
										</select>
										<!--col-md-6-->
									</div>
									<!-- col-md-6 sem-padding-left -->

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
										<textarea required id="textarea" name="justificativa"
											maxlength="280"
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
												<%if (aluno != null) {%> <%="readonly"%> <%}%> readonly>
										</div>
										<!--col-md-10-->
									</div>
									<!--form-group col-md-12-->
								</div>

								<!--form-group col-md-12-->
						</div>
						<!--row-->

						<div class="row">
							<div class="form-group col-md-12 sem-padding-left">
								<div class="col-md-2">
									<label for="inputMatricula">Matrícula:</label>
								</div>
								<!--col-md-2-->
								<div class="col-md-2 sem-padding-left">
									<input class="form-control matricula" type="text"
										value="<%=aluno != null ? aluno.getMatricula() : ""%>"
										id="matricula" name="matricula" placeholder="Matricula"
										<%if (aluno != null) {%> <%="readonly"%> <%}%>>
								</div>
								<!--col-md-2-->
								<div class="col-md-1">
									<label for="inputCurso">Curso:</label>
								</div>
								<!--col-md-1-->
								<div class="col-md-7 sem-padding-right">
									<input class="form-control" type="text" name="inputCurso"
										value="<%=aluno != null ? aluno.getCurso().getNome() : ""%>"
										<%if (aluno != null) {%> <%="readonly"%> <%}%> readonly>
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
										<input required class="form-control biginput completeprofessor" type="text"
											name="inputProfessor"
											placeholder="Nome do professor">
										<input type="hidden" name="valueIdProfessor" class="valueIdProfessor"> 
									</div>
									<!--col-md-10-->
								</div>
								<!-- form-group col-md-12 -->
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12 ">

									<div class="col-md-2 sem-padding-right ">
										<label for="inputDisciplina" class="margem-esquerda">Disciplina:</label>
									</div>
									<!--col-md-4-->
									<div class="col-md-10 sem-padding-left form-group">
										<select name="inputDisciplina" class="complete_disciplinas">
											<option value="" disabled="disabled" selected="selected">Selecione
												uma Disciplina</option>
										</select>

									</div>
									<!--col-md-8 sem-padding-left form-group-->
								</div>
							</div>
							<!-- row-->

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 ">
										<label for="inputDataProva">Data da Entrega do
											Resultado:</label>
									</div>
									<!--col-md-4-->
									<div class="col-md-2  sem-padding-left ">
										<input class="form-control inputData" id="inputDataProva"
											placeholder="dd/mm/aaaa" maxlength="10" autocomplete="off"
											type="text" name="inputDataProva">
									</div>
									<!--col-md-4-->
									<div class="col-md-1 sem-padding-right ">
										<label for="inputDataProva">Data da Prova:</label>
									</div>
									<!--col-md-2-->
									<div class="col-md-2 sem-padding-left ">
										<input class="form-control inputData" id="inputDataProva"
											placeholder="dd/mm/aaaa" maxlength="10" autocomplete="off"
											type="text" name="inputDataProva">
									</div>
									<!--col-md-2-->
									<div class="col-md-2 sem-padding-right">
										<label for="inputHoraProva">Horário da Prova:</label>
									</div>
									<div class="col-md-2 sem-padding-left ">
										<input class="form-control inputHoraProva" id="inputHoraProva"
											placeholder="hh:mm" maxlength="4" autocomplete="off"
											type="text" name="inputHoraProva">
									</div>
									<!--col-md-2-->

								</div>
								<!--col-md-6-->
							</div>

							<div class="row">
								<div class="form-group col-md-12">
									<div class="col-md-2 sem-padding-right ">
										<label for="justificativa" class="margem-esquerda">Justificativa:</label>
									</div>
									<div class="col-md-10 sem-padding-left sem-padding-right">
										<textarea required id="textarea" name="justificativa"
											maxlength="280"
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

						</div>
						<!-- col-md-6 -->


					</div>
					<!-- form-group col-md-12 -->
				</div>
				<!-- row-->



			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>

<script src="<%=Constantes.getAppJsUrl()%>/jquery-3.2.1.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.mockjax.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/demo.js"></script>
