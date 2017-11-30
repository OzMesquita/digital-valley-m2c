<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Solicitacao"%>

<div class="row">
	<div
		class=" col-md-6 col-md-offset-3 sem-padding-left sem-padding-right"
		id="formulario_solicitacao">

		<%
			ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) request.getAttribute("solicitacoes");

			usuario = (Usuario) session.getAttribute("usuario");

			if (usuario.getPessoa() instanceof Servidor) {
				if (((Servidor) usuario.getPessoa()).getCargo().equals(EnumCargo.SECRETARIO)
						|| usuario.getNivel().equals(EnumNivel.ADMINISTRADOR)) {
		%>
		
		
		<div class="panel panel-primary" id="sem-margin-botton">
			<div class="panel-heading">
				<h2 class="panel-title" align="center">Histórico de
					Solicitações</h2>
			</div>
			
			
		</div>
		<div class="tab-pane active col-md-12" id="todas_solicitacoes">
			<div class="row" id="buscaSolicitacao">
				<div class="col-md-12">
					<div class="row">
					<%if(session.getAttribute(Constantes.getSessionMsg()) != null){ %>
					<div class="alert alert-danger" role="alert">
  						<%=session.getAttribute(Constantes.getSessionMsg()) %>
					</div>
					<%session.setAttribute(Constantes.getSessionMsg(), null); %>
					
				<%} %>
						<div class="col-md-6 espacamentoHorizontal">
							<form class="espacamentoHorizontal" action="listarSolicitacao"
								method="post">
								<div class="col-md-2">
									<label id="labelBuscarSoli" for="buscarSoliMatricula">Aluno:</label>
								</div>
								<div class="input-group col-md-4">
									<input class="form-control matricula" placeholder="MATRÍCULA"
										name="inputMatricula" id="buscarSoliMatricula" type="text">
									<input type="hidden" value="listarPorAluno" name="tipoBusca">
									<div class="input-group-btn">
										<button type="submit" class="btn btn-primary my-btn-primary">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</div>
								</div>

							</form>
						</div>
						<div class="col-md-6 espacamentoHorizontal">
							<form class="espacamentoHorizontal" action="listarSolicitacao"
								method="post">
								<div class="col-md-3">
									<label id="labelBuscarSoli" for="buscarSoliSiape">Professor:</label>
								</div>
								<div class="input-group col-md-4">

									<input class="form-control siape" placeholder="SIAPE"

										name="inputSiape" id="buscarSoliSiape" type="text"> <input
										type="hidden" value="listarPorProfessor" name="tipoBusca">
									<div class="input-group-btn">
										<button type="submit" class="btn btn-primary my-btn-primary">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					
					<div class="panel panel-primary">
						<table class="table table-responsive table-hover" id="dev-table">
							<thead>
								<tr>
									<th>ID</th>
									<th>Nome do Aluno</th>
									<th>Data da Solicitação</th>
									<th>Nome do Professor</th>
									<th>Disciplina</th>
									<th>Tipo da Solicitação</th>
									<th>Detalhes</th>
									<th>Download</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (Solicitacao solicitacao : solicitacoes) {
								%>
								<tr>
									<td><%=solicitacao.getId()%></td>
									<td><%=solicitacao.getAluno().getNome()%></td>
									<td><%=solicitacao.getDataSolicitacao()%></td>
									<td><%=solicitacao.getProfessor().getNome()%></td>
									<td><%=solicitacao.getDisciplina().getNome()%></td>
									<td><%=solicitacao.getTipoSolicitacao()%></td>

									<td>
										<button type="button" class="btn btn-primary btn_detalhes" id="<%=solicitacao.getId()%>"
											data-toggle="modal" data-target="#detalhes">
											<span class="glyphicon glyphicon-option-horizontal  "></span>
										</button>
									</td>

									<td><button type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-save-file"></span>
										</button></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%
		}
		} else if (usuario.getPessoa() instanceof Aluno) {
	%>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title" align="center">Histórico de
						Solicitações</h3>
				</div>
				<table class="table table-responsive table-hover" id="dev-table">
				<thead>
								<tr>
									<th>ID</th>
									<th>Nome do Aluno</th>
									<th>Data da Solicitação</th>
									<th>Nome do Professor</th>
									<th>Disciplina</th>
									<th>Tipo da Solicitação</th>
									<th>Detalhes</th>
									<th>Download</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (Solicitacao solicitacao : solicitacoes) {
								%>
								<tr>
									<td><%=solicitacao.getId()%></td>
									<td><%=solicitacao.getAluno().getNome()%></td>
									<td><%=solicitacao.getDataSolicitacao()%></td>
									<td><%=solicitacao.getProfessor().getNome()%></td>
									<td><%=solicitacao.getDisciplina().getNome()%></td>
									<td><%=solicitacao.getTipoSolicitacao()%></td>
									<td><%=solicitacao.getTipoSolicitacao()%></td>
									<td>
										<button type="button" class="btn btn-primary btn_detalhes" id="<%=solicitacao.getId()%>"
											data-toggle="modal" data-target="#detalhes">
											<span class="glyphicon glyphicon-option-horizontal  "></span>
										</button>
									</td>
									<td><button type="button" class="btn btn-primary">
											<span class="glyphicon glyphicon-save-file"></span>
										</button></td>
								</tr>
								<%
									}
								%>
							</tbody>
				</table>
			</div>
		</div>
	</div>
	<%
		}
	%>
</div>
<!-- Modal -->
<div class="modal fade" id="detalhes" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title" id="exampleModalLabel">Detalhes das
					solicitação</h2>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<h4>Dados da Solicitaçao</h4>
				<p id="id">ID:</p>
				<p id="tipoSolicitacao">Tipo Solicitaçao:</p>
				<p id="dataProva">Data da Prova:</p>
				<p id="dataSolicitacao">Data da Solicitação:</p>
				<p id="justificativa">Justificativa:</p>
				
				<h4>Dados do Aluno</h4>
				<p id="matricula">Matricula:</p>
				<p id="nome">Nome:</p>
				<p id="curso">Curso:</p>
				
				<h4>Dados do Professor</h4>
				<p id="siape">Siape:</p>
				<p id="nomeProfessor">Nome:</p>
				<p id="email">Email:</p>
				
				<h4>Dados da Disciplina</h4>
				<p id="disciplina">Disciplica:</p>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>
<!-- formulario_solicitacao -->


</div>
<script src="<%=Constantes.getAppJsUrl()%>/jquery-3.2.1.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/bootstrap.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/verDetalhes.js"></script>
