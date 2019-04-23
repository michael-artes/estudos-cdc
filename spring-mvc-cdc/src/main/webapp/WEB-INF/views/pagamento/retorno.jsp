<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>


<customTags:masterPage title="Pagamento Retorno">

	<jsp:body>
	
		<!-- Begin page content -->
		<div class="container">
	
			<div class="page-header">
				<h1> <span class="glyphicon glyphicon-eur" aria-hidden="true"></span> Pagamento ${sucesso == true ? 'Com Sucesso' : 'Com Erro'} </h1>
			</div>
			
			<c:choose>
				<c:when test="${sucesso}">
					<!-- <img src="http://localhost:9444/s3/s3Amazon/200-payment-sucesso.png" class="img-responsive img-thumbnail" alt="Sucesso"> -->
					<img src="<c:url value="/resources/imagens/200-payment-sucesso.png"/>" class="img-responsive img-thumbnail" alt="Sucesso">
				</c:when>
				<c:otherwise>
					<img src="<c:url value="/resources/imagens/400-bad-request.png"/>" class="img-responsive img-thumbnail" alt="Sucesso">
				</c:otherwise>
			</c:choose>
			
			<br>
			
			<p>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${spring:mvcUrl('PC#form').build()}" class="btn btn-primary" role="button"> Cadastrar Produto </a>
				</security:authorize> 
				<a href="${spring:mvcUrl('PC#list').build()}" class="btn btn-success" role="button">Listagem Produto </a> 
				<a href="${spring:mvcUrl('CC#items').build()}" class="btn btn-info" role="button">Carrinho de Compras</a>
				
			</p>		
	
		</div>	
	    
	</jsp:body>
    
</customTags:masterPage>
