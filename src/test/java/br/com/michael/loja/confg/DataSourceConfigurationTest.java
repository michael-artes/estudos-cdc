package br.com.michael.loja.confg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {
	
	@Bean
	@Profile("dev")
	public DataSource getDataSource() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:postgresql://localhost:5432/spring-mvc-cdc-teste");
		driver.setUsername("postgres");
		driver.setPassword("08101993md");
		return driver;
	}
	
}
