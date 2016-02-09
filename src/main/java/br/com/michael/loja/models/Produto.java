package br.com.michael.loja.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="produto", schema="public")
@SequenceGenerator(name="prod_seq", sequenceName="prod_id_seq", allocationSize=1)
public class Produto {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="prod_seq")
	private Integer id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String descricao;
	
	@Min(30)
	private Integer paginas;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Calendar lancementoData;
	
	@ElementCollection
	private List<Preco> precos = new ArrayList<Preco>();
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getPaginas() {
		return paginas;
	}
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Preco> getPrecos() {
		return precos;
	}
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	
	public Calendar getLancementoData() {
		return lancementoData;
	}
	public void setLancementoData(Calendar lancementoData) {
		this.lancementoData = lancementoData;
	}
	
	
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}
	
}
