<%@page import="java.util.ArrayList"%>
<%@page import="util.FacadeSolicitacoes"%>
<%@page import="java.util.List"%>
<%@page import="dao.JDBCDisciplinaDAO"%>
<%@page import="dao.DAOFactoryM2C"%>
<%@page import="dao.DAOFactory"%>
<%@page import="model.Disciplina"%>
<div class="conteudo">
		<%List<Disciplina> discplinasdisponiveis = new ArrayList<Disciplina>();%>
	<div id="page-wrapper">
		<div class="panel panel-primary" id="sem-margin-botton">
			<div class="panel-heading">
				<h2 class="panel-title" align="center">Associar Disciplina ao
					Professor</h2>
			</div>

			


			<div class="panel-body">
				<div class="tab-content">
					<div class=tab-pane">
						<div class="row">
							
								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2">
											<label for="inputProfessor">Professor:</label>
										</div>

										<div class="col-md-10 sem-padding-left">
											<input required
												class="form-control biginput completeprofessor" type="text" name="inputProfessor" placeholder="Nome do professor" value="<%=request.getParameter("inputProfessor")!=null ? request.getParameter("inputProfessor") : "" %>"   >
											<input type="hidden" name="valueIdProfessor" class="valueIdProfessor"  id="valueIdProfessor"  >
										</div>
									</div>
								</div>
								<!-- Seleção de Curso-->
								<div class="row">
									<div class="form-group col-md-12">
										<div class="col-md-2 ">
											<label for="selectCurso">Curso:</label>
										</div>


										<div class="col-md-10 sem-padding-left">

											<select required="required" id ="curso" name="curso">
												<option value="" disabled="disabled" selected="selected">Selecione
													um Curso</option>
												<option value="1">Ciência da Computação</option>
												<option value="2">Engenharia Civíl</option>
												<option value="3">Engenharia de Produção</option>
												<option value="4">Engenharia de Software</option>
												<option value="5">Engenharia Mecânica</option>
											</select>
											
											<button type="submit" id="buscarDiscProfCurso" class="btn btn-primary btn_disc_prof_curso"
												id="valueIdProfessor" data-toggle="button" onclick="buscarDiscProfCurso" >
											 
											<span class="glyphicon glyphicon-option-horizontal  "></span>
											</button>
											 </div>
							
						</div>
					</div>
				</div>

				<form action="AssociarDiscProf" method="post" id="formDiscProf" >
					
					<div class="row">
						<div class="form-group col-md-12">
							<div class="col-md-4">
								<table id="divTabela" class="table table-condensed">
									<tr>
										<th>Selecionado</th>
										<th>Nome</th>
									</tr>
									<tbody id="tabela">
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-md-8">
						<input type="submit" value="Salvar" class="btn btn-primary my-btn-primary">					
					</div>
					</form>
			</div>
		</div>
	</div>
	
</div>
</div>
</div>
</div>
</div>


<script src="<%=Constantes.getAppJsUrl()%>/jquery-3.2.1.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/bootstrap.min.js"></script>
<script src="<%=Constantes.getAppJsUrl()%>/buscarDiscProfCurso.js"></script>

<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.mockjax.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/demo.js"></script>