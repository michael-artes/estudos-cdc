package br.com.livro.tdd.capitulo08;

import java.util.Calendar;
import java.util.List;

public class GeradorDeNotaFiscal {
	
	private List<AcaoAposGerarNota> acoes;
	private Relogio relogio;
	private Tabela tabela;
	
	public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoes, Relogio relogio) {
		this.acoes = acoes;
		this.relogio = relogio;
	}
	
	// quebrar o resto do sistema
	public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoes) {
		this(acoes, new RelogioDoSistema());
	}

	public GeradorDeNotaFiscal(List<AcaoAposGerarNota> nenhumaAcao, Tabela tabela) {
		this.acoes = nenhumaAcao;
		this.tabela = tabela;
	}

	public NotaFiscal gera(Pedido pedido) {

		NotaFiscal nf = new NotaFiscal(pedido.getCliente(), pedido.getValorTotal() * tabela.paraValor(pedido.getValorTotal()), Calendar.getInstance());
		
		for (AcaoAposGerarNota a : acoes) {
			a.executa(nf);
		}
		
		return nf;
	}
	
	
	public int relogioDoSistema(){
		return relogio.hoje().get(Calendar.DATE);
	}

}
