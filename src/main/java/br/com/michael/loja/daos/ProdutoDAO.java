package br.com.michael.loja.daos;

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

}
