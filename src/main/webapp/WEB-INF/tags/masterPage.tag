<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<%@attribute name="title" required="true" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${title}</title>

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
  
      <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Book Shopping</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.servletContext.contextPath}/">Home</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
	            <a href="${pageContext.servletContext.contextPath}/logout">
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal" var="user" />
						<span class="glyphicon glyphicon-user"></span>
						<spring:message code="users.welcome" arguments="${user.name}" />
					</security:authorize>
					| Logout
	           	</a>
           	</li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
  
  	
<%--   	<span class="glyphicon glyphicon-th-list"></span>
  	<p>
  		<a href="<c:url value="/produto/listagem?locale=pt"/>">
		Português
		</a>
  	</p>
  	
  	<span class="glyphicon glyphicon-th-list"></span>
  	<p>
  		<a href="<c:url value="/produto/listagem?locale=en_US"/>">
		Ingles
		</a>
  	</p> --%>
  

	<jsp:doBody/>
	
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