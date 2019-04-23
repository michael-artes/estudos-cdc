package br.com.livro.tdd.capitulo07;


public class DezOuVintePorCento extends RegraDeCalculo {

	@Override
	protected int limite() {
		return 3000;
	}

	@Override
	protected double porcentagemAcimaDoLimite() {
		return 0.8;
	}

	@Override
	protected double porcentagemBase() {
		return 0.9;
	}


}
