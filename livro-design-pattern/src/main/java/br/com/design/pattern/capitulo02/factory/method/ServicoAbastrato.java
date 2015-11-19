package br.com.design.pattern.capitulo02.factory.method;

import java.util.List;

public abstract class ServicoAbastrato<T> {
	
	public GeradorArquivo geradorArquivo;
	
	public ServicoAbastrato(GeradorArquivo geradorArquivo) {
		this.geradorArquivo = geradorArquivo;
	}
	
	protected abstract DAO<T> getDAO();

	
	public Object gravarEmArquivo(int id, String nomeArquivo){
		
		T obj = getDAO().byId(id);
		
		List<T> todos = getDAO().listarTodos();
		
		System.out.println("Total Produtos: " + todos.size());
		
		Object o = geradorArquivo.gerarArquivo(nomeArquivo, obj); 
	
		return o;
	}
}
