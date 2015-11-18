package br.com.design.pattern.capitulo02;

public class PDFWord extends PDF {

	@Override
	protected String buildCorpo() {
		return "Este e o corpo do PDFWord";
	}

}
