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
<title>Cadastro de Produtos</title>

<!-- Bootstrap -->
<link
	href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/sticky-footer.css"
	rel="stylesheet">

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

</style>

</head>

<body>


	<div class="container">
	
		<c:if test="${param.error != null}">
		  <div class="alert alert-danger">
		      <p>Invalid username and password.</p>
		  </div>
		</c:if>	
		
		<c:if test="${param.logout != null}">
		  <div class="alert alert-success">
		      <p>You have been logged out successfully.</p>
		  </div>
		</c:if>
				

		<form class="form-signin" action="<c:url value="/login"/>"
			method="post">

			<security:csrfInput />

			<h2 class="form-signin-heading">
				<span class="glyphicon glyphicon-level-up"></span> Favor logar-se
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

			<button class="btn btn-lg btn-primary btn-block" type="submit">Logar</button>

		</form>


	</div>
	<!-- /container -->

	<footer class="footer">
		<div class="container">
			<p class="text-muted">Copyrigth © 2015 - Spring MVC.</p>
		</div>
	</footer>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="${pageContext.servletContext.contextPath}/resources/jquery/jquery-1.12.0.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</body>
</html>