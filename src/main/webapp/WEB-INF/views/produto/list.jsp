<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>


<customTags:masterPage title="Listagem de Produtos">

	<jsp:body>
	
	    <!-- Begin page content -->
	    <div class="container">
			<div class="page-header">
			  <h1>Lista de Produtos</h1>
			  
			  <%-- <p>
			  	<security:authorize access="isAuthenticated()">
				  	<security:authentication property="principal" var="user"/> 
				  	<span class="glyphicon glyphicon-user"></span> <spring:message code="users.welcome" arguments="${user.name}"/>
			  	</security:authorize>
			  </p> --%>
			  
				<%--
				Poderia ser usado tbm dessa form 
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li>
					<a href="${spring:mvcUrl('PC#form').build()}">Cadastrar novo produto</a>
					</li>
				</security:authorize> --%>
			</div>
			
			<div class="table-responsive">
			
				<table class="table table-hover">
					<tr>
						<th> <span class="glyphicon glyphicon-level-up"></span> </th>
						<td> <spring:message code="produto.titulo"/> </td>
						<td> <spring:message code="produto.valores"/> </td>
					</tr>
					
					<c:forEach items="${produtos}" var="produto">
						<tr>
							<td> <img src="<c:url value="/resources/imagens/uploads/prod-${produto.id}-img.png"/>" class="img-responsive img-rounded" alt="Responsive image" style="width: 50%;"> </td>
							<td> 
								
								<a href="${spring:mvcUrl('PC#show').arg(0,produto.id).build()}">
									<span class="glyphicon glyphicon-erase" aria-hidden="true"></span>
								</a>
							
								<p>
									${produto.titulo}
								</p>
								
								<hr/>
								
								<p>
									<i>Paginas: ${produto.paginas}</i>
								</p>
								
							</td>
						<td style="width:25%">
							<c:forEach items="${produto.precos}" var="preco">
								<c:if test="${preco.valor != null}">
									<p>
										[${preco.livroTipo} - ${preco.valor}]
									</p>
								</c:if>
							</c:forEach>
						</td>
						</tr>
					</c:forEach>
				
				</table>
				
			</div>
			
			
			
			<c:if test="${not empty sucesso}">
				<div class="alert alert-success">
				  <strong>Success!</strong> ${sucesso}
				</div>
			</c:if>
			
			<p>
				<a href="${spring:mvcUrl('PC#form').build()}" class="btn btn-primary" role="button"> Cadastrar Produto </a>
				<a href="${spring:mvcUrl('CC#items').build()}" class="btn btn-info" role="button"> Carrinho de Compra </a>
				
			</p>
			
	    </div>
	    
	</jsp:body>
    


</customTags:masterPage>
