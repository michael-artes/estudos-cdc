package br.com.michael.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan(new String[]{"br.com.michael.loja.models"});
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(getAdditionalProperties());
		
		return em;
	}


	@Bean
	private Properties getAdditionalProperties() {
		
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		return properties;
	}
	
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:postgresql://localhost:5432/spring-mvn-cdc");
		driver.setUsername("postgres");
		driver.setPassword("08101993md");
		return driver;
	}
	
	
	@Bean
	public PlatformTransactionManager getTransactionManager(EntityManagerFactory em){
		
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(em);
		
		return manager;
	}

}
