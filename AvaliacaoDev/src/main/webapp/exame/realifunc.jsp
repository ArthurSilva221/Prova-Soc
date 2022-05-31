<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	
	
	<body class="bg-secondary">	
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
					<s:form action="/filtrarRealiExames.action">
						<div class="input-group">
							<span class="input-group-text">
								<strong><s:text name="label.buscar.por"/></strong>
							</span>	
								<s:select  
									cssClass="form-select" 
									name="filtrar.opcoesCombo" 
									list="listaOpcoesCombo"  
									headerKey=""  
									headerValue="Escolha..." 
									listKey="%{codigo}" 
									listValueKey="%{descricao}"
									value="filtrar.opcoesCombo.codigo"									
								/>
								
								<s:textfield cssClass="form-control" id="nome" name="filtrar.valorBusca"/>
								<button class="btn btn-primary" type="submit"><s:text name="label.pesquisar"/></button>
						</div>
					</s:form>			
				</div>				
			</div>

			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.id.funcionario"/></th>
							<th><s:text name="label.id.exame"/></th>
							<th><s:text name="label.dt.realizacao"/></th>
							<th class="text-end mt-5"><s:text name="label.acao"/></th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="exames" >
							<tr>
								<td>${rowid}</td>
								<td>${nome}</td>
								<td>${data}</td>
								<td class="text-end">
									<s:url action="editarreExames" var="editar">
										<s:param name="exameVo.rowid" value="rowid"></s:param>
									</s:url>

									<a href="${editar}" class="btn btn-warning text-white">
										<s:text name="label.editar"/>
									</a>
									
									<s:url action="remoReExames" var="remover">
										<s:param name="exameVo.rowid" value="rowid"></s:param>
										<s:param name="exameVo.nome" value="nome"></s:param>
									</s:url>
									
									<a href="${remover}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="${remover}">
										<s:text name="label.excluir"/>
									</a>
									
								</td>
							</tr>
						</s:iterator>
					</tbody>
					
					<tfoot class="table-secondary">
						<tr>
							<td colspan="4">
								<s:url action="novorealiExames" var="novo"/>
								
								<a href="${novo}" class="btn btn-success">
									<s:text name="label.novo.reali"/>
								</a>
								
								<s:url action="funcionarioExames" var="crud"/>
								
								<a href="${crud}" class="btn btn-success">
									<s:text name="label.crud.funcionario"/>
								</a>
								
								<s:url action="todosExames" var="crud"/>
								
								<a href="${crud}" class="btn btn-success">
									<s:text name="label.crud.exame"/>
								</a>
							</td>
						</tr>
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>