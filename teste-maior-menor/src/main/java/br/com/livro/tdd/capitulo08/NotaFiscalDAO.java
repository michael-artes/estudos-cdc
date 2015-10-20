package br.com.livro.tdd.capitulo08;

public class NotaFiscalDAO implements AcaoAposGerarNota{
	
	private void persiste(NotaFiscal notaFiscal){
		System.out.println("Persistindo a Nota");
	}

	@Override
	public void executa(NotaFiscal nf) {
		persiste(nf);
	}

}
