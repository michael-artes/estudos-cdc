package br.com.design.pattern.capitulo02.factory.method;

import java.util.Arrays;
import java.util.List;

public class ProdutoDAO implements DAO<Produto> {

	@Override
	public Produto byId(int id) {
		return new Produto(id);
	}

	@Override
	public void salvar(Produto obj) {
		System.out.println("Salvo com sucesso: " + obj.toString());
	}

	@Override
	public void excluir(Produto obj) {
		System.out.println("Excluido com sucesso: " + obj.toString());
	}

	@Override
	public List<Produto> listarTodos() {
		return Arrays.asList(
					new Produto(5), 
					new Produto(6), 
					new Produto(7)
					);
	}

}
