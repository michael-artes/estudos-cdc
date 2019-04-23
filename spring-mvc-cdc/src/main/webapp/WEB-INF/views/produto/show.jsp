<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>


<customTags:masterPage title="Detalhes do Produto">

	<jsp:body>
	
	<!-- Begin page content -->
	<div class="container">

		<div class="page-header">
			<h1> <span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span> Detalhes do Produto</h1>
		</div>
		
			<c:if test="${invalidLivroTipo != null}">
				<div class="alert alert-danger">
				  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				  <strong>Error!</strong>
				  	<p> ${invalidLivroTipo} </p>
				</div>			
			</c:if>		


			<div class="row">
				<div class="col-sm-6 col-md-12">
					<div class="thumbnail">
						<img src="<c:url value="/resources/imagens/uploads/prod-${produto.id}-img.png"/>" alt="${produto.titulo}" class="img-responsive img-rounded">
						<div class="caption">
							<h3>${produto.titulo}</h3>
							<p class="text-justify">${produto.descricao}</p>
							<p>
								<strong>Paginas: ${produto.paginas} </strong>
							</p>
	
							<form:form servletRelativeAction="/carrinho/adicionar" method="post">
								
								<security:csrfInput/>
							
								<input type="hidden" name="produtoId" value="${produto.id}">
							
								<div class="list-group text-center">
		
									<c:forEach var="preco" items="${produto.precos}">
									
										<c:if test="${preco.valor != null}">
											<div class="radio-inline">
											  <label>
											    <input type="radio" value="${preco.livroTipo}" name="livroTipo" 
											    	id="${produto.id}-${preco.livroTipo}" ${preco.livroTipo.name() == 'COMBO' ? 'checked' : ''}>
											    	
											    ${preco.livroTipo}  -  <strong> ${preco.valor} </strong>	
											  </label>
											</div>	
										</c:if>
		
		
									</c:forEach>
		
								</div>
		
								<p class="text-center">
									<button class="btn btn-primary" type="submit">Comprar</button>
									<button class="btn btn-default" type="reset">Cancelar Compra</button>
								</p>
								
							</form:form>
						</div>
					</div>
				</div>
			</div>


		<p>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<a href="${spring:mvcUrl('PC#form').build()}" class="btn btn-primary" role="button"> Cadastrar Produto </a>
			</security:authorize>
			<a href="${spring:mvcUrl('PC#list').build()}" class="btn btn-success" role="button">Listagem Produto</a>
			<a href="${spring:mvcUrl('CC#items').build()}" class="btn btn-info" role="button">Carrinho de Compras</a>			
		</p>


	</div>	
	
	</jsp:body>
    
</customTags:masterPage>
