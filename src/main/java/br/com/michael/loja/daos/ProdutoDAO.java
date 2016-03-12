package br.com.michael.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.michael.loja.models.LivroTipo;
import br.com.michael.loja.models.Produto;

@Repository
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Produto produto) {
		entityManager.persist(produto);
	}
	
	public Produto findById(Integer id){
		return entityManager.find(Produto.class, id);
	}

	public List<Produto> list() {
		return entityManager.createQuery("select distinct(p) from Produto p join fetch p.precos", Produto.class)
							.getResultList();
	}
	
	
	public BigDecimal somaPrecosPorTipo(LivroTipo tipo){
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(
					  "SELECT sum(preco.valor) FROM Produto p "
					+ "JOIN p.precos preco WHERE preco.livroTipo = :livroTipo", 
					BigDecimal.class);
		
		query.setParameter("livroTipo", tipo);
		
		return query.getSingleResult();
	}

}
