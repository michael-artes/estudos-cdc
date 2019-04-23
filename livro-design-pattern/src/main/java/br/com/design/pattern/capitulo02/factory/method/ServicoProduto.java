package br.com.design.pattern.capitulo02.factory.method;

public class ServicoProduto extends ServicoAbastrato<Produto> {

	private DAO<Produto> dao;
	
	
	public ServicoProduto(GeradorArquivo geradorArquivo) {
		super(geradorArquivo);
	}

	@Override
	protected DAO<Produto> getDAO() {
		
		if (dao == null) {
			dao = new  ProdutoDAO();
		}
		
		return dao;
	}
	
	public double getPrecoProduto(int id){
		Produto p = getDAO().byId(id);
		System.out.println(p.toString());
		return p.getPreco();
	}

}
