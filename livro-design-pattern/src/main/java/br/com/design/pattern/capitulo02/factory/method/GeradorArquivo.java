package br.com.design.pattern.capitulo02.factory.method;

public class GeradorArquivo {

	public Object gerarArquivo(String nomeArquivo, Object obj){
		System.out.println("Arquivo Gerado com Sucesso!!!");
		System.out.println("Nome Arquivo: " + nomeArquivo);
		System.out.println(obj.toString());
		return obj;
	}
	
}
