package br.com.livro.tdd.capitulo03;

import java.util.HashMap;
import java.util.Map;

public class ConversorDeNumeroRomano {
	
	@SuppressWarnings("serial")
	private static Map<String, Integer> tabela = 
				new HashMap<String, Integer>(){{
					put("I", 1);
					put("V", 5);
					put("X", 10);
					put("L", 50);
					put("C", 100);
					put("D", 500);
					put("M", 1000);
				}};

	public int converte(String numeroEmRomano) {
		
		int acumulador = 0;
		int ultimoVizinhoDaDireita = 0;
		
		for(int i = numeroEmRomano.length() - 1; i >= 0; i--) {
			
			// pega o inteiro referente ao simbolo atual
			int atual = tabela.get(String.valueOf(numeroEmRomano.charAt(i)));
			
			// se o da direita for menor, o multiplicaremos
			// por -1 para torná-lo negativo
			int multiplicador = 1;
			
			if(atual < ultimoVizinhoDaDireita){
				multiplicador = -1;
			} 
			
			acumulador += atual * multiplicador;
			
			// atualiza o vizinho da direita
			ultimoVizinhoDaDireita = atual;
		}
		
		return acumulador;
	}
}
