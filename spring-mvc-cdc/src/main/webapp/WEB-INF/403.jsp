<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags" %>


<customTags:masterPage title="403 - Acesso Negado!">

	<jsp:body>
	
		<!-- Begin page content -->
		<div class="container">
	
			<div class="page-header">
				<h1> <span class="glyphicon glyphicon-alert" aria-hidden="true"></span> Erro! acesso não autorizado </h1>
			</div>
			
			<img src="<c:url value="/resources/imagens/403-page.png"/>" class="img-responsive img-thumbnail" alt="Sucesso">
	
			<br><br>
			
			<p>
				<a href="<c:url value="/produto/listagem"/>" class="btn btn-success" role="button">Listagem Produto </a> 
			</p>
	
		</div>
	    
	</jsp:body>
    
</customTags:masterPage>
