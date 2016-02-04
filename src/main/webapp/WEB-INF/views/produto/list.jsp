<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Cadastro de Produtos</title>

    <!-- Bootstrap -->
    <link href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/sticky-footer.css" rel="stylesheet">

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
		  <h1>Lista de Produtos</h1>
		</div>
		
		<table class="table table-hover">
			<tr>
				<td>Titulo</td>
				<td>Valores</td>
			</tr>
			
			<c:forEach items="${produtos}" var="p">
				<tr>
					<td>${p.titulo}</td>
				<td>
					<c:forEach items="${p.precos}" var="preco">
						[${preco.livroTipo} - ${preco.valor}]
					</c:forEach>
				</td>
				</tr>
			</c:forEach>
		
		</table>
		
		
		<c:if test="${not empty sucesso}">
			<p class="bg-primary">
				${sucesso}
			</p>
		</c:if>
		
		
    </div>

    <footer class="footer">
      <div class="container">
        <p class="text-muted">Copyrigth © 2015 - Spring MVC</p>
      </div>
    </footer>    
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.servletContext.contextPath}/resources/jquery/jquery-1.12.0.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
  </body>
</html>