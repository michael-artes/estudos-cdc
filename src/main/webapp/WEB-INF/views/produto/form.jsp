<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
        <h1>Cadastro de Produtos</h1>
      </div>
      
      	<spring:hasBindErrors name="produto">
			<c:forEach items="${errors.allErrors}" var="error">
					<div class="alert alert-danger">
					  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>Error!</strong>
					  	<p><spring:message code="${error.code}" text="${error.defaultMessage}"/> <br/> </p>
					</div>			
			</c:forEach>      
      	</spring:hasBindErrors>
      	
      
		<form:form method="post" action="${spring:mvcUrl('PC#save').build()}" commandName="produto" enctype="multipart/form-data">
		
		  <div class="form-group">
		    <label for="titulo">Titulo</label>
		    <form:input path="titulo" cssClass="form-control" placeholder="Titulo..."/>
		    <!-- <input type="text" class="form-control" name="titulo" id="titulo" placeholder="Titulo..."> -->
		    <%-- <form:errors path="titulo"/> FUNCIONA SEM A TAG HASBINDERRORS POREM PRECISA ESTAR DENTRO DE FORM:FORM--%>
		  </div>
		  
		  <div class="form-group">
		    <label for="descricao">Descrição</label>
		    <form:textarea path="descricao" cssClass="form-control" rows="3" cols="4" placeholder="Descrição..."/>
		  </div>
		  
		  <div class="form-group">
		    <label for="paginas">Qtd. Paginas</label>
		    <form:input path="paginas" cssClass="form-control" placeholder="Páginas......"/>
		  </div>
		  
		  <div class="form-group">
			<label for="lancementoData">Data de lançamento</label>
			<form:input path="lancementoData" type="date" cssClass="form-control" placeholder="Data de lançamento......"/>
			<form:errors path="lancementoData"/>
		  </div>
		  
		  <div class="form-group">
		    <label for="sumario">Anexar Arquivo</label>
		    <input type="file" name="sumario">
		    <p class="help-block">Favor enviar arquivos para upload!</p>
		    <form:errors path="sumarioPath"/>
		  </div>  
		  
			<c:forEach items="${tipos}" var="livroTipo" varStatus="status">
			
			  <div class="form-group">
			    <label for="preco_${livroTipo}">${livroTipo}</label>
			    
			    <div class="row">
				    <div class="col-md-4">
					    <div class="input-group">
					    	 <div class="input-group-addon">R$</div>
						     <form:input path="precos[${status.index}].valor" cssClass="form-control" id="preco_${livroTipo}"/>
						     <form:hidden path="precos[${status.index}].livroTipo" cssClass="form-control" value="${livroTipo}"/>
					    </div>
				    </div>
			    </div>
			    
			  </div>
			  
			</c:forEach>
			
		  <button type="submit" class="btn btn-info">Enviar</button>
		  
		</form:form>
		
		
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