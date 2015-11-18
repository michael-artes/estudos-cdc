package br.com.design.pattern.capitulo02;

public abstract class PDF {
	
	
	public String buidCabecalho(){
		return "Este e o cabecalho do PDF";
	}
	
	protected abstract String buildCorpo();
	
	
	public String buildRodape(){
		return "Este e o rodape do PDF";
	}

}
