package br.com.design.pattern.capitulo02.factory.method;

import java.util.List;

public interface DAO<T> {

	public T byId(int id);
	
	public void salvar(T obj);
	
	public void excluir(T obj);
	
	public List<T> listarTodos();
	
}
