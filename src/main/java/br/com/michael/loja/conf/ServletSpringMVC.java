/**
 * 
 */
package br.com.michael.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author mmoreira
 *
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{};
	}

	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new OpenEntityManagerInViewFilter()};
	}
	
	
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "production");
		
	}

}
