package br.com.michael.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="produto", schema="public")
@SequenceGenerator(name="prod_seq", sequenceName="prod_id_seq", allocationSize=1)
public class Produto {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="prod_seq")
	private Integer id;
	
	@NotBlank(message="O campo titulo é obrigatório")
	private String titulo;
	
	@NotBlank(message="O campo descrição é obrigatório")
	private String descricao;
	
	@Min(value=30, message="Campo paginas quantidade minima 30")
	private int paginas;
	
	@Column(name="lancamento_data")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date lancamentoData;
	
	
	@ElementCollection(fetch=FetchType.LAZY)
	private List<Preco> precos = new ArrayList<Preco>();
	
	@Transient
	private String sumarioPath;
	
	
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
	
	
	public String getSumarioPath() {
		return sumarioPath;
	}
	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}
	
	
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}

	
	public BigDecimal priceFor(LivroTipo livroTipo) {
		return precos
				.stream()
				.filter(preco -> preco.getLivroTipo().equals(livroTipo))
				.findFirst().get().getValor();
	}
	
	public Date getLancamentoData() {
		return lancamentoData;
	}
	public void setLancamentoData(Date lancamentoData) {
		this.lancamentoData = lancamentoData;
	}
	
	
}
