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
				<h2 class="panel-title" align="center">Histórico de Solicitações</h2>
			</div>
		</div>
		<div class="tab-pane active col-md-12" id="todas_solicitacoes">

			<div class="row" id="buscaSolicitacao">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-6 espacamentoHorizontal">
						<form class="espacamentoHorizontal" action="listarSolicitacao" method="post">
							<div class="col-md-2">
								<label id="labelBuscarSoli" for="buscarSoliMatricula">Aluno:</label>
							</div>
							<div class="input-group col-md-4">
								<input class="form-control" placeholder="MATRÍCULA"
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
						<form class="espacamentoHorizontal" action="listarSolicitacao" method="post">
							<div class="col-md-3">
								<label id="labelBuscarSoli" for="buscarSoliSiape">Professor:</label>
							</div>
							<div class="input-group col-md-4">
								<input class="form-control" placeholder="SIAPE"
									name="inputSiape" id="buscarSoliSiape" type="text">
								<input type="hidden" value="listarPorProfessor" name="tipoBusca">
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
									<td><button type="button" class="btn btn-primary " >
											<span class="glyphicon glyphicon-option-horizontal"></span>
										</button></td>
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
					<h3 class="panel-title" align="center">Histórico de Solicitações</h3>
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
									<td><button type="button" class="btn btn-primary " >
											<span class="glyphicon glyphicon-option-horizontal"></span>
										</button></td>
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
<!-- formulario_solicitacao -->






</div>
<script src="<%=Constantes.getAppJsUrl()%>/jquery-3.2.1.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/bootstrap.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/verDetalhes.js"></script>
