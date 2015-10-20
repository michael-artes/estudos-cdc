package br.com.livro.tdd.capitulo08;

public class SAP implements AcaoAposGerarNota{
	
	private void enviar(NotaFiscal notaFiscal){
		System.out.println("Enviando para o SAP");
	}

	@Override
	public void executa(NotaFiscal nf) {
		enviar(nf);
	}

}
