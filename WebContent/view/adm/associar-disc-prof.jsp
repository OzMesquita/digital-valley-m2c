<%@page import="dao.JDBCDisciplinaDAO"%>
<%@page import="dao.DAOFactoryM2C"%>
<%@page import="dao.DAOFactory"%>
<%@page import="model.Disciplina"%>
<div class="conteudo">
<div id="page-wrapper">
<div class="panel panel-primary"  id="sem-margin-botton">		
	<div class="panel-heading">
		<h2 class="panel-title" align="center">Associar Disciplina ao Professor</h2>
	</div>

	<div class="panel-body">
		<div class="tab-content">
	<div class=tab-pane">
	<div class="row">
	<form action="AssociarDisciplinaProfessor" method="POST">
		<div class="row">
			<div class="form-group col-md-12">
				<div class="col-md-2">
					<label for="inputProfessor">Professor:</label>
				</div>
			
				<div class="col-md-10 sem-padding-left">
					<input required class="form-control biginput completeprofessor"
					type="text" name="inputProfessor"
					placeholder="Nome do professor"> <input type="hidden"
					name="valueIdProfessor" class="valueIdProfessor">
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
					<select required="required" name="Curso" >
						<option value="" disabled="disabled" selected="selected">Selecione um Curso</option>
						<option value="software">Engenharia de Software</option>
						<option value="civil">Engenharia Civíl</option>
						<option value="computacao">Ciência da Computação</option>
						<option value="mecanica">Engenharia Mecânica</option>
						<option value="producao">Engenharia de Produção</option> 
					</select>
				</div>
			</div>
		</div>
		
			
		<div class="row">
		<div class="form-group col-md-12">	
			<div class="col-md-2">          
				<table class="table table-condensed">
					<tr>
						<th >Selecionado </th>
						<th >Nome </th>
					</tr>		
						<tbody>
						
						<tr>
							<td> <input type="checkbox" id="Fundamentos de Programação" name="disciplina" value="Fundamentos de Programação"></td>
							
						</tr>
						<tr>
							<td>	<label id="Fundamentos de Programação">Fundamentos de Programação</label>
							</td>
						</tr>
						</tbody>
				</table>
			</div>
		</div>	
		</div>
		<div>
			<input type="submit" value="Salvar" class="btn btn-primary my-btn-primary">
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

<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.mockjax.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/demo.js"></script>