<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<title>Carrinho de Compras</title>
		
		<!-- Bootstrap -->
		<link
			href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
		<link
			href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/sticky-footer.css" rel="stylesheet">
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		    <![endif]-->
		
		
	</head>
	
	<body>
	
	
	    <!-- Begin page content -->
	    <div class="container">
	    
	        <div class="page-header">
				<h1> <span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span> Detalhes do Produto</h1>
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
							<td> <img src="http://localhost:9444/s3/s3Amazon/prod-${carrinhoIten.produto.id}-img.png" class="img-responsive img-rounded" alt="Responsive image"> </td>
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
				<a href="${spring:mvcUrl('PC#form').build()}" class="btn btn-primary" role="button">Cadastrar Produto</a>
				<a href="${spring:mvcUrl('PC#list').build()}" class="btn btn-success" role="button">Listagem Produto</a>
				<a href="<c:url value="/logout" />" class="btn btn-danger" role="button">Logout</a>
			</p>
	    </div>	
	
	    <footer class="footer">
	      <div class="container">
	        <p class="text-muted">Copyrigth © 2015 - Spring MVC.</p>
	      </div>
	    </footer>    
	    
	    
	    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="${pageContext.servletContext.contextPath}/resources/jquery/jquery-1.12.0.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
	
	</body>
	
</html>