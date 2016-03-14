package br.com.michael.loja.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.michael.loja.enuns.EnumRoles;


@Entity
@Table(name="user", schema="public")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1356254260828187689L;
	
	@Id
	@NotBlank(message="Favor preencher o e-mail")
	@Email(message="O Email está incorreto")
	private String login;
	
	@NotBlank(message="O campo senha é obrigatório")
	private String password;
	
	@NotBlank(message="O campo nome é obrigatório")
	private String name;

	private boolean ativo = false;
	
	@Column(name="data_cad", nullable=false)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date dataCad;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	
	@NotNull(message="Necessário preencher a permissão")
	@Transient
	private EnumRoles enumRoles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.ativo;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public EnumRoles getEnumRoles() {
		return enumRoles;
	}

	public void setEnumRoles(EnumRoles enumRoles) {
		this.enumRoles = enumRoles;
	}
	
}
