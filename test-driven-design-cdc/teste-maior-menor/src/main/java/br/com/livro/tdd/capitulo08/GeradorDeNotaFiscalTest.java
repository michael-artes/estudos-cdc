package br.com.livro.tdd.capitulo08;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class GeradorDeNotaFiscalTest {
	
	@Test
	public void deveInvocarAcoesPosteriores() {
		
		// criando o mock
		AcaoAposGerarNota dao = mock(NotaFiscalDAO.class);
		AcaoAposGerarNota sap = mock(SAP.class);
		
		List<AcaoAposGerarNota> acoes = Arrays.asList(dao, sap);
		
		GeradorDeNotaFiscal gerador = new GeradorDeNotaFiscal(acoes);
		Pedido pedido = new Pedido("Mauricio", 1000, 1);
		NotaFiscal nf = gerador.gera(pedido);
		
		for (AcaoAposGerarNota a : acoes) {
			verify(a).executa(nf);
		}
		
	}
	
	@Test
	public void deveTestarADataAtual(){
		
		// criando o mock
		AcaoAposGerarNota dao = mock(NotaFiscalDAO.class);
		AcaoAposGerarNota sap = mock(SAP.class);
		
		List<AcaoAposGerarNota> acoes = Arrays.asList(dao, sap);
		
		GeradorDeNotaFiscal gerador = new GeradorDeNotaFiscal(acoes);
		Pedido pedido = new Pedido("Mauricio", 1000, 1);
		NotaFiscal nf = gerador.gera(pedido);
		
		for (AcaoAposGerarNota a : acoes) {
			verify(a).executa(nf);
		}
		
		Assert.assertEquals(19, gerador.relogioDoSistema());
		
	}
	
	
	@Test
	public void deveConsultarATabelaParaCalcularValor() {
		
		// mockando uma tabela, que ainda nem existe
		Tabela tabela = Mockito.mock(Tabela.class);
		
		// definindo o futuro comportamento "paraValor",
		// que deve retornar 0.2 caso o valor seja 1000.0
		Mockito.when(tabela.paraValor(1000.0)).thenReturn(0.2);
		
		List<AcaoAposGerarNota> nenhumaAcao = Collections.emptyList();
		
		GeradorDeNotaFiscal gerador = new GeradorDeNotaFiscal(nenhumaAcao, tabela);
		Pedido pedido = new Pedido("Mauricio", 1000, 1);
		NotaFiscal nf = gerador.gera(pedido);
		
		// garantindo que a tabela foi consultada
		Mockito.verify(tabela).paraValor(1000.0);

		assertEquals(1000 * 0.2, nf.getValor(), 0.00001);
	}
	
}



