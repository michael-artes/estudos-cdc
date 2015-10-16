package br.com.livro.tdd.capitulo07;

import br.com.livro.tdd.capitulo04.Funcionario;

public abstract class RegraDeCalculo {
	
	public double calcula(Funcionario f){
		
		if(f.getSalario() > limite()) {
			return f.getSalario() * porcentagemAcimaDoLimite();
		}
		
		return f.getSalario() * porcentagemBase();
	}
	
	
	protected abstract int limite();
	
	protected abstract double porcentagemAcimaDoLimite();
	
	protected abstract double porcentagemBase();

}
