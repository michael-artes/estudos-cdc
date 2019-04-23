package br.com.design.pattern.capitulo02;

import org.junit.Assert;
import org.junit.Test;

public class ConstruirPDFTest {

	
	@Test
	public void testBuildPDFs(){
		
		new ConstruirPDF().printPDF(new PDFWord());
		new ConstruirPDF().printPDF(new PDFExcel());

		Assert.assertTrue(true);
	}
	
}
