<%@page import="model.Disciplina"%>
<div class="conteudo">
<div id="page-wrapper">
<div class="wizard-navigation">
	<div class="page-header"><h2>Associar Disciplina ao Professor</h2></div>
	<div class="tab-content">
	<div class=tab-pane">
	<div class="row">
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
					<select required="required" name="inputCurso" class="">
						<option value="" disabled="disabled" selected="selected">Selecione um Curso</option>
					</select>
				</div>
			</div>
		</div>
			<!-- Tabela de Turmas -->
			<!-- Direitos autorais reservados a WenYi em http://bootstrap-table.wenzhixin.net.cn/ -->
		<div class="row">
		<div class="form-group col-md-12">	
			<div class="table-responsive">          
				<table class="table table-condensed">
					<tr>
						<th >Selecionados</th>
						<th >Nome </th>
					</tr>		
						<tbody>
						<tr>
							<td><div><input type="checkbox" id="1"><label></label></div></td>
							<td>Programação Orientada a Gambiarra</td>
						</tr>
						</tbody>
				</table>
			</div>
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
	src="<%=Constantes.getAppJsUrl()%>/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.mockjax.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/jquery.autocomplete.js"></script>
<script type="text/javascript"
	src="<%=Constantes.getAppJsUrl()%>/demo.js"></script>