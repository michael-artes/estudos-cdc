<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>


<customTags:masterPage title="Home">

	<jsp:body>
	
	<!-- Begin page content -->
	<div class="container">


		<div class="page-header">
			<h1>
				<span class="glyphicon glyphicon-home"></span> 
				Seja bem vindo ao  books shopping!
			</h1>
		</div>
		<p class="lead">Informas que temos promoções de livros. Aproveita e faça sua compra!</p>
		<p align="center">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<a href="<c:url value="/produto/cadastro"/>" class="btn btn-primary" role="button"> Cadastrar Produto </a>
			</security:authorize> 
			<a href="<c:url value="/produto/listagem"/>" class="btn btn-success" role="button">Listagem Produto </a> 
			<a href="<c:url value="/carrinho/carrinho"/>" class="btn btn-info" role="button">Carrinho de Compras</a>
		</p>

	</div>
	
	</jsp:body>
    
</customTags:masterPage>
