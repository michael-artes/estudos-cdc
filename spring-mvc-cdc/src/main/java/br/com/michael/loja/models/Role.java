package br.com.michael.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="role", schema="public")
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 724702767539480675L;

	
	@Id
	private String name;
	
	@Override
	public String getAuthority() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}