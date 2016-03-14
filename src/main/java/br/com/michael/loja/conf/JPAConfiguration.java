package br.com.michael.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[]{"br.com.michael.loja.models"});
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(getAdditionalProperties());
		
		return em;
	}


	@Bean
	private Properties getAdditionalProperties() {
		
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.format_sql", "false");
		
		return properties;
	}
	
	
	
	@Bean
	@Profile("production")
	public DataSource getDataSourceProd(Environment environment) {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:postgresql://localhost:5432/spring-mvn-cdc");
		driver.setUsername("postgres");
		driver.setPassword("08101993md");
		return driver;
	}
	
	
	@Bean
	@Profile("prod-heroku")
	public DataSource getDataSourceHeroku(Environment environment) throws URISyntaxException {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		URI dbUrl =	new URI(environment.getProperty("DATABASE_URL"));
		driver.setUrl("jdbc:postgresql://"+ dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath());
		driver.setUsername(dbUrl.getUserInfo().split(":")[0]);
		driver.setPassword(dbUrl.getUserInfo().split(":")[1]);
		return driver;
	}	
	
	
	@Bean
	public PlatformTransactionManager getTransactionManager(EntityManagerFactory em){
		
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(em);
		
		return manager;
	}
	
	
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
