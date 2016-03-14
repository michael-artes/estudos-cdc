<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Login</title>

    <!-- Bootstrap -->
    <link href="<spring:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<spring:url value="/resources/bootstrap-3.3.6-dist/sticky-footer.css"/>" rel="stylesheet">
	<link href="<spring:url value="/resources/datapicker/css/datepicker.css"/>" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<style type="text/css">

body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.datepicker {
	z-index: 1060;
}

</style>

</head>

<body>


	<div class="container">
	
		<c:if test="${param.error != null}">
		  <div class="alert alert-danger">
		      <p>Invalid username and password.</p>
		  </div>
		</c:if>	
		
		<c:if test="${userCreate == true}">
		  <div class="alert alert-warning">
		  		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      	<strong>Info!</strong>
		      	<p>Usuario criado com sucesso!</p>
		  </div>
		</c:if>		
		
		<c:if test="${userAtivado == true}">
		  <div class="alert alert-success">
		  		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      	<strong>Info!</strong>
		      	<p>Usuario ativado com sucesso!</p>
		  </div>
		</c:if>				
		
      	<spring:hasBindErrors name="user">
			<c:forEach items="${errors.allErrors}" var="error">
					<div class="alert alert-danger">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>Error!</strong>
					  	<p><spring:message text="${error.defaultMessage}"/> </p>
					</div>			
			</c:forEach>      
      	</spring:hasBindErrors>		
		

		<form class="form-signin" action="<c:url value="/login"/>" method="post">

			<security:csrfInput />

			<h2 class="form-signin-heading">
				<span class="glyphicon glyphicon-eye-open"></span> Acessar Conta!
			</h2>

			<label for="login" class="sr-only">Email:</label> 
			<input type="email" id="username" name="loginUser" class="form-control" placeholder="Email..." required autofocus> 
			<label for="password" class="sr-only">Senha:</label> 
			<input type="password" id="password" name="senhaUser" class="form-control" placeholder="Senha..." required>

			<div class="checkbox">
				<label> 
					<input type="checkbox" value="remember-me">
					Lembrar-me
				</label>
			</div>

			<button class="btn btn-lg btn-primary btn-block" type="submit">
				<span class="glyphicon glyphicon-user"></span> Logar
			</button>
			
			<hr> <br>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-success btn-block" data-toggle="modal" data-target="#cadUser">
			  <span class="glyphicon glyphicon-plus-sign"></span> Cadastrar usuário
			</button>
		</form>
		
		
		<!-- Modal -->
		<div class="modal fade" id="cadUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel"> <span class="glyphicon glyphicon-user"></span> Cadastrar usuário </h4>
		      </div>
		      
			<form:form method="post" servletRelativeAction="/user/create">
			
			  <security:csrfInput/>
			
		      <div class="modal-body">
	
			  <div class="form-group">
			    <label for="name">Nome</label>
			    <input type="text" name="name" class="form-control" placeholder="Nome..." required="required">
			  </div>
			  
			  <div class="form-group">
			    <label for="login">Email</label>
			    <input type="email" name="login" class="form-control" placeholder="email..." required="required">
			  </div>
			  
			  <div class="form-group">
			    <label for="password">Senha</label>
			    <input type="password" name="password" class="form-control" placeholder="senha..." required="required">
			  </div>
			  
			  <div class="form-group">
			    <label for="dataCad">Data Cadastro</label>
			    <input type="text" name="dataCad" class="form-control datapicker" placeholder="Data..." required="required">
			  </div>	
			  
			  <div class="form-group">
			    <label for="enumRoles">Tipo Usuario</label>
				<select class="form-control" name="enumRoles" required="required">
				  <option value="-1"> ---- Selecione ---- </option>
				  <option value="ROLE_COMPRADOR">COMPRADOR</option>
				</select>			    
			  </div>			  		  				  				  	
			  
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		        <button type="submit" class="btn btn-primary">Salvar</button>
		      </div>
		      
			</form:form>
		    </div>
		  </div>
		</div>		


	</div>
	<!-- /container -->

	<footer class="footer">
		<div class="container">
			<p class="text-muted">Copyrigth © 2016 - Spring MVC.</p>
		</div>
	</footer>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<spring:url value="/resources/jquery/jquery-1.12.0.min.js"/>"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<spring:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"/>"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<spring:url value="/resources/datapicker/js/bootstrap-datepicker.js"/>"></script>
    
    
    <script type="text/javascript">
    
	    $(function(){
	    	$(".datapicker").datepicker();
	    });    
    
    </script>
    
        
</body>
</html>