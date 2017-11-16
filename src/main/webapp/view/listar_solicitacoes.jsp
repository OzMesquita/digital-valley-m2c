<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Solicitacao"%>

<div class="row">
	<div
		class=" col-md-6 col-md-offset-3 sem-padding-left sem-padding-right"
		id="formulario_solicitacao">

		<%
			List<Solicitacao> solicitacoes = (List<Solicitacao>) request.getAttribute("solicitacoes");

			usuario = (Usuario) session.getAttribute("usuario");

			if (usuario.getPessoa() instanceof Servidor) {
				if (((Servidor) usuario.getPessoa()).getCargo().equals(EnumCargo.SECRETARIO)
						|| usuario.getNivel().equals(EnumNivel.ADMINISTRADOR)) {
		%>

		<div class="wizard-navigation">
			<ul class="nav nav-pills tab-menu" id="tipo_solicitacao">
				<li class="active  col-md-4 sem-padding-left sem-padding-right"><a
					href="#segunda_chamada" data-toggle="tab" aria-expanded="true">Todas</a></li>
				<li class="col-md-4 sem-padding-left sem-padding-right"
					id="tab-direita"><a href="#recorrecao" data-toggle="tab"
					aria-expanded="false">Buscar Por Aluno</a></li>

				<li class="col-md-4 sem-padding-left sem-padding-right"
					id="tab-direita"><a href="#recorrecao" data-toggle="tab"
					aria-expanded="false">Buscar Por Professor</a></li>
			</ul>
			<div class="moving-tab"
				style="width: 375px; transform: translate3d(0px, 0px, 0px); transition: transform 0s;">
			</div>

			<div class="tab-content">
				<div class="tab-pane active col-md-12" id="todas_solicitacoes">
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title" align="center">Solicitações</h3>
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
											<td><%=solicitacao.getTipoSolicitacao().getValorSolicitacao()%></td>
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

				<div class="tab-pane col-md-12" id="listarPorAluno">
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title" align="center">Solicitações</h3>
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
											<td><%=solicitacao.getTipoSolicitacao().getValorSolicitacao()%></td>
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

				<div class="tab-pane col-md-12" id="ListarPorProfessor">
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title" align="center">Solicitações</h3>
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
											<td><%=solicitacao.getTipoSolicitacao().getValorSolicitacao()%></td>
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
		</div>

		<%
			}
			} else if (usuario.getPessoa() instanceof Aluno) {
		%>

		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title" align="center">Solicitações</h3>
					</div>

					<table class="table table-responsive table-hover" id="dev-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Professor</th>
								<th>Data da Solicitação</th>
								<th>Disciplina</th>
								<th>Tipo da Solicitação</th>

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
								<td><%=solicitacao.getTipoSolicitacao().getValorSolicitacao()%></td>
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
