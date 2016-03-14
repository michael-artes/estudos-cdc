<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>


<customTags:masterPage title="Cadastro de Produtos">

	<jsp:body>
	
    <!-- Begin page content -->
    <div class="container">
    
      <div class="page-header">
        <h1>Cadastro de Produtos</h1>
      </div>
      
      	<spring:hasBindErrors name="produto">
			<c:forEach items="${errors.allErrors}" var="error">
					<div class="alert alert-danger">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>Error!</strong>
					  	<p><spring:message text="${error.defaultMessage}"/> </p>
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
		  
		  <div class="form-group col-xs-3">
		    <label for="paginas">Qtd. Paginas</label>
		    <form:input path="paginas" type="number" cssClass="form-control" placeholder="Páginas......" />
		  </div>
		  
		  <div class="form-group col-xs-4">
			<label for="lancementoData">Data de lançamento</label>
			<form:input path="lancamentoData" type="text" cssClass="form-control datapicker" placeholder="Data de lançamento......"/>
		  </div>
		  
		  <div class="form-group">
		    <label for="sumario">Anexar Arquivo</label>
		    <input type="file" name="sumario">
		    <p class="help-block">Favor enviar arquivos para upload!</p>
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
			
			
			<%-- 
				MODO NORMAL
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			
			--%>
			

			<%-- 
				FORMA MAIS SIMPLES
			 --%>

			<security:csrfInput/>
			
			
			<%-- 
			
				OUTR FORMA SERIA ANOTAR A CLASSE SecurityConfiguration COM @EnableWebMvcSecurity
				
				E USAR EM TODOS OS FORMS O FORM DO SPRING
				
				EX: <FORM:FORM> </FORM:FORM>
				
				COM ISS NAO SERIA PRECISO FICAR PASSANDO O CSRF TOKEN
			 --%>
			
			<p>
			  <button type="submit" class="btn btn-primary">Enviar</button>
			  <a href="${spring:mvcUrl('PC#list').build()}" class="btn btn-success" role="button">Listagem Produto</a>
			</p>
		  
		</form:form>
		
		
    </div>	    
	    
	</jsp:body>
    
</customTags:masterPage>
