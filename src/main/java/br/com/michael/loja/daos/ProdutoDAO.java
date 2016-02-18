package br.com.michael.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

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

}
