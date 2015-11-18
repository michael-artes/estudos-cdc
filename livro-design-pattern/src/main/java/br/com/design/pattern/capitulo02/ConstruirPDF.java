package br.com.design.pattern.capitulo02;

public class ConstruirPDF {
	
	public void printPDF(PDF pdf){
		
		System.out.println("------- Cabecalho: " + pdf.buidCabecalho());
		System.out.println("------- Corpo: " + pdf.buildCorpo());
		System.out.println("------- Rodape: " + pdf.buildRodape());
		
	}
	
}
