package capitulo3;

public class Exemplo2 {

	private static String cep1 = "78890-000";
	private static String cep2 = "78890-00";
	
	public static void main(String[] args) {
		
		//Modo Antigo
		Validador<String> validarCEP = new Validador<String>() {
			
			@Override
			public boolean valida(String t) {
				return t.matches("[0-9]{5}-[0-9]{3}");
			}
		};
	
		System.out.println("Valida Cep1: " + validarCEP.valida(cep1));
		System.out.println("Valida Cep2: " + validarCEP.valida(cep2));
		
		
		//Modo Java 8
		System.out.println("Modo Java 8");
		Validador<String> validadorCEPJ8 = valor -> valor.matches("[0-9]{5}-[0-9]{3}");

		System.out.println("Valida Cep1: " + validadorCEPJ8.valida(cep1));
		System.out.println("Valida Cep2: " + validadorCEPJ8.valida(cep2));
		
		
	}
	
}
