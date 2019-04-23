<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>


<customTags:masterPage title="Carrinho">

	<jsp:body>

	    <!-- Begin page content -->
	    <div class="container">
	    
	        <div class="page-header">
				<h1> <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Carrinho de Compras</h1>
			</div>
			
			
			<c:if test="${invalidQuantidade != null}">
				<div class="alert alert-danger">
				  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				  <strong>Error!</strong>
				  	<p> ${invalidQuantidade} </p>
				</div>			
			</c:if>
			
			<table class="table table-striped">

				<thead>
					<tr>
						<th> <span class="glyphicon glyphicon-level-up"></span> </th>
						<th> Item </th>
						<th> Preço </th>
						<th> Quantidade </th>
						<th> Total </th>
						<th> </th>
					</tr>
				</thead>
				
				<tbody>
				
					<c:forEach items="${sessionScope.carrinho.list}" var="carrinhoIten">
					
						<tr>
							<%-- <td> <img src="http://localhost:9444/s3/s3Amazon/prod-${carrinhoIten.produto.id}-img.png" class="img-responsive img-rounded" alt="Responsive image"> </td> --%>
							
							<td> <img src="<c:url value="/resources/imagens/uploads/prod-${carrinhoIten.produto.id}-img.png"/>" class="img-responsive img-rounded" alt="Responsive image"> </td>
							<td> ${carrinhoIten.produto.titulo}-${carrinhoIten.livroTipo} </td>
							<td> ${carrinhoIten.preco} </td>
							<td style="width: 20%"> 
							
								<form:form servletRelativeAction="/carrinho/atualizar" method="post">
								
									<security:csrfInput/>

									<div class="row">
									  <div class="col-lg-12">
									    <div class="input-group">
									      <input type="hidden" name="idProduto" value="${carrinhoIten.produto.id}">
									      <input type="number" class="form-control" name="qtdProdutoId" value="${sessionScope.carrinho.getQuantidade(carrinhoIten)}">
									      <span class="input-group-btn">
									        <button class="btn btn-warning" type="submit"> <span class="glyphicon glyphicon glyphicon-pencil"></span> </button>
									      </span>
									    </div><!-- /input-group -->
									  </div><!-- /.col-lg-12 -->									
									</div>
									
								</form:form>							
								
								
							</td>
							
							<td> ${sessionScope.carrinho.getTotal(carrinhoIten)} </td>
							<td> 
							
								<form:form action="${spring:mvcUrl('CC#excluir').arg(0,carrinhoIten.produto.id).arg(1,carrinhoIten.livroTipo).build()}" method="post">
								
									<security:csrfInput/>
									<button type="submit" class="btn btn-danger">Deletar</button>
									
								</form:form>
							
							</td>
						</tr>
					
					</c:forEach>
					
				</tbody>
				
				<tfoot>
					
					<tr class="warning">
					
						<th colspan="4"> 
							
							<form action="${spring:mvcUrl('PC#finalizarCompra').build()}" method="post">
								<security:csrfInput/>
								<button type="submit" class="btn btn-warning">
									<span class="glyphicon glyphicon-download"></span> Comprar
								</button> 
							</form>
							
						</th>
						<th colspan="2"> Total: ${sessionScope.carrinho.total} </th>
					
					</tr>
				
				</tfoot>
				
			</table>		
			
			<p>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${spring:mvcUrl('PC#form').build()}" class="btn btn-primary" role="button"> Cadastrar Produto </a>
				</security:authorize>			
				<a href="${spring:mvcUrl('PC#list').build()}" class="btn btn-success" role="button">Listagem Produto</a>
			</p>
	    </div>	
	    
	</jsp:body>
    
</customTags:masterPage>
