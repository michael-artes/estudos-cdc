package br.com.livro.tdd.capitulo04;

import br.com.livro.tdd.capitulo07.DezOuVintePorCento;
import br.com.livro.tdd.capitulo07.QuinzeOuVinteCincoPorCento;
import br.com.livro.tdd.capitulo07.RegraDeCalculo;

public enum Cargo {
	
	DESENVOLVEDOR( new DezOuVintePorCento() ),
	DBA( new QuinzeOuVinteCincoPorCento() ),
	TESTADOR( new QuinzeOuVinteCincoPorCento() );
	
	
	private RegraDeCalculo regraDeCalculo;
	
	private Cargo(RegraDeCalculo regraDeCalculo) {
		this.regraDeCalculo = regraDeCalculo;
	}

	
	public RegraDeCalculo getRegraDeCalculo() {
		return regraDeCalculo;
	}
	
}
