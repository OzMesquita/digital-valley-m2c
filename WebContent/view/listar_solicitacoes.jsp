<%@page import="model.Professor"%>
<%@page import="util.Facade"%>
<%@page import="dao.DAOFactory"%>
<%@page import="dao.DAOFactoryM2C"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Solicitacao"%>

<%
	int pagina = request.getParameter("pagina") == null ? 1	: Math.abs(Integer.valueOf(request.getParameter("pagina")));
	int fim = pagina * Constantes.getNumberOfRowsPerPage();
	int inicio = fim - Constantes.getNumberOfRowsPerPage();
	double a = DAOFactoryM2C.criarSolicitacaoDAO().buscarQntdDeSolicitacoes();
	double quantidadeDeItensPagProf = 1;
	double quantidadeDeItensPagAlu = 1;
	double b = Constantes.getNumberOfRowsPerPage();
	if(request.getParameter("inputMatricula")!=null){
		System.out.println("matricula= "+request.getParameter("inputMatricula"));
		double al = 0;
		Aluno aluno =DAOFactory.criarAlunoDAO().buscarPorMatricula(request.getParameter("inputMatricula"));
		if (aluno != null){
			if(DAOFactoryM2C.criarSolicitacaoDAO().buscarQntdDeSolicitacoesAlu(aluno.getId()) != 0){
				al =  DAOFactoryM2C.criarSolicitacaoDAO().buscarQntdDeSolicitacoesAlu(DAOFactory.criarAlunoDAO().buscarPorMatricula(request.getParameter("inputMatricula")).getId());	
			}
		}
			
		quantidadeDeItensPagAlu = Math.ceil(al/b);
	}else if(request.getParameter("inputSiape")!=null){
		double p = 0;
		Professor professor =DAOFactory.criarProfessorDAO().buscarPorSiape(request.getParameter("inputSiape"));
		if(professor!= null){
			
		
			p = DAOFactoryM2C.criarSolicitacaoDAO().buscarQntdDeSolicitacoesProf(professor.getId());
		}
		quantidadeDeItensPagProf = Math.ceil(p/b);
	}
	double quantidadeDeItensPaginacao = Math.ceil(a/b);
	
%>

<div class="row">
	<div class="col-md-12">
		<%
			ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) request.getAttribute("solicitacoes");

			usuario = (Usuario) session.getAttribute("usuario");

			if (usuario.getPessoa() instanceof Servidor) {
				if (((Servidor) usuario.getPessoa()).getCargo().equals(EnumCargo.SECRETARIO)
						|| usuario.getNivel().equals(EnumNivel.ADMINISTRADOR)) {
		%>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h2 class="panel-title" align="center">Histórico de
					Solicitações</h2>
			</div>
			<div class="panel-body">
				<div class="row">
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
					<div class="col-md-6">
						<form action="listarSolicitacao" method="post">
							<div class="row">

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

							</div>
						</form>
					</div>
					<div class="col-md-6">
						<form action="listarSolicitacao" method="post">
							<div class="row">

								<input name="pagina" value="1" hidden>
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
							</div>
						</form>
					</div>
				</div>
				<div class="tab-pane active">
					<table class="table table-responsive table-hover">
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
								<td><%=solicitacao.getTipoSolicitacaoToString()%></td>
								<td>
									<button type="button" class="btn btn-primary btn_detalhes"
										id="<%=solicitacao.getId()%>" data-toggle="modal"
										data-target="#detalhes">
										<span class="glyphicon glyphicon-option-horizontal  "></span>
									</button>
								</td>
								<td><a
									href="gerarPDFSolicitacao?id=<%=solicitacao.getId()%>"
									target="_blank" class="btn btn-primary"> <span
										class="glyphicon glyphicon-save-file"></span>
								</a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<div align="left"> <a
								id="link-voltar" href="homeSolicitacao.jsp" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
						<nav aria-label="Page navigation example">
						<ul class="pagination">
							<%if(request.getParameter("inputMatricula") == null && request.getParameter("inputSiape")== null ){
								if (pagina > 1 && quantidadeDeItensPaginacao > 1) {
							%>
							<li class="page-item"><a class="page-link" href="?pagina=<%=pagina-1%>">Anterior</a></li>
							<%
								}
								for (int i = 1; i <= quantidadeDeItensPaginacao; i++) {
							%>
							<li class="page-item <%=i == pagina ? "active" : ""%>"><a
								class="page-link" href="?pagina=<%=i%>"><%=i%></a></li>
							<%
								}
								if (pagina >= 0 && quantidadeDeItensPaginacao >= 1 && pagina < quantidadeDeItensPaginacao) {
							%>
							<li class="page-item"><a class="page-link" href="?pagina=<%=pagina+1%>">Próximo</a></li>
							<%
								}
							}else{
								if(request.getParameter("inputMatricula") != null){
									if (pagina > 1 && quantidadeDeItensPagAlu > 1) {
										%>
										<li class="page-item"><a class="page-link" href="?pagina=<%=pagina-1%>&inputMatricula=<%=request.getParameter("inputMatricula")%>&tipoBusca=<%=request.getParameter("tipoBusca")%>">Anterior</a></li>
										<%
											}
											for (int i = 1; i <= quantidadeDeItensPagAlu; i++) {
										%>
										<li class="page-item <%=i == pagina ? "active" : ""%>"><a
											class="page-link" href="?pagina=<%=i%>&inputMatricula=<%=request.getParameter("inputMatricula")%>&tipoBusca=<%=request.getParameter("tipoBusca")%>"><%=i%></a></li>
										<%
											}
											if (pagina >= 0 && quantidadeDeItensPagAlu >= 1 && pagina < quantidadeDeItensPagAlu) {
										%>
										<li class="page-item"><a class="page-link" href="?pagina=<%=pagina+1%>&inputMatricula=<%=request.getParameter("inputMatricula")%>&tipoBusca=<%=request.getParameter("tipoBusca")%>">Próximo</a></li>
										<%
											}
								}else if(request.getParameter("inputSiape") != null){
										if (pagina > 1 && quantidadeDeItensPagProf > 1) {
											%>
											<li class="page-item"><a class="page-link" href="?pagina=<%=pagina-1%>&inputSiape=<%=request.getParameter("inputSiape")%>&tipoBusca=<%=request.getParameter("tipoBusca")%>">Anterior</a></li>
											<%
										}
												for (int i = 1; i <= quantidadeDeItensPagProf; i++) {
											%>
											<li class="page-item <%=i == pagina ? "active" : ""%>"><a
												class="page-link" href="?pagina=<%=i%>&inputSiape=<%=request.getParameter("inputSiape")%>&tipoBusca=<%=request.getParameter("tipoBusca")%>"><%=i%></a></li>
											<%
											}
												if (pagina >= 0 && quantidadeDeItensPagProf >= 1 && pagina < quantidadeDeItensPagProf) {
											%>
											<li class="page-item"><a class="page-link" href="?pagina=<%=pagina+1%>&inputSiape=<%=request.getParameter("inputSiape")%>&tipoBusca=<%=request.getParameter("tipoBusca")%>">Próximo</a></li>
											<%
												}
										
								}
							}
								%>										
						</ul>
					</nav>
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
				<div class="panel panel-primary" id="sem-margin-botton">
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
								<td>
									<button type="button" class="btn btn-primary btn_detalhes"
										id="<%=solicitacao.getId()%>" data-toggle="modal"
										data-target="#detalhes">
										<span class="glyphicon glyphicon-option-horizontal  "></span>
									</button>
								</td>
								<td><a
									href="gerarPDFSolicitacao?id=<%=solicitacao.getId()%>"
									target="_blank" class="btn btn-primary"> <span
										class="glyphicon glyphicon-save-file"></span>
								</a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>

			</div>
		</div>
		<div id="row-voltar" align="left"><a
					id="link-voltar" href="homeSolicitacao.jsp" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<%
						double al = DAOFactoryM2C.criarSolicitacaoDAO().buscarQntdDeSolicitacoesAlu(usuario.getPessoa().getId());	
						quantidadeDeItensPagAlu = Math.ceil(al/Constantes.getNumberOfRowsPerPage());
						if (pagina > 1 && quantidadeDeItensPagAlu > 1) {
							%>
							<li class="page-item"><a class="page-link" href="?pagina=<%=pagina-1%>">Anterior</a></li>
							<%
								}
								for (int i = 1; i <= quantidadeDeItensPagAlu; i++) {
							%>
							<li class="page-item <%=i == pagina ? "active" : ""%>"><a
								class="page-link" href="?pagina=<%=i%>"><%=i%></a></li>
							<%
								}
								if (pagina >= 0 && quantidadeDeItensPagAlu >= 1 && pagina < quantidadeDeItensPagAlu) {
							%>
							<li class="page-item"><a class="page-link" href="?pagina=<%=pagina+1%>">Próximo</a></li>
							<%
								}
							%>	
				</ul>
			</nav>
		</div>
		<%
			}
		%>
	</div>


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
				<div class="dados-modal">
					<h4 align="center">Dados da Solicitaçao</h4>
					<p id="id">ID:</p>
					<p id="tipoSolicitacao">Tipo Solicitaçao:</p>
					<p id="dataProva">Data da Prova:</p>
					<p id="dataSolicitacao">Data da Solicitação:</p>
					<p id="justificativa">Justificativa:</p>

				</div>
				<div class="dados-modal">
					<h4 align="center">Dados do Aluno</h4>
					<p id="matricula">Matricula:</p>
					<p id="nome">Nome:</p>
					<p id="curso">Curso:</p>

				</div>
				<div class="dados-modal">
					<h4 align="center">Dados do Professor</h4>
					<p id="siape">Siape:</p>
					<p id="nomeProfessor">Nome:</p>
					<p id="email">Email:</p>
				</div>
				<div class="dados-modal">
					<h4 align="center">Dados da Disciplina</h4>
					<p id="disciplina">Disciplina:</p>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
				<a id="gerarPDF" type="button" class="btn btn-primary"
					href="gerarPDFSolicitacao" target="_blankk">Gerar PDF</a>
			</div>
		</div>
	</div>
	<!-- formulario_solicitacao -->


</div>
<script src="<%=Constantes.getAppJsUrl()%>/jquery-3.2.1.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/bootstrap.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/verDetalhes.js"></script>