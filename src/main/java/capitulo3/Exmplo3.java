package capitulo3;

import java.util.StringJoiner;

public class Exmplo3 {

	public static void main(String[] args) {
		
		//Mostrando o nome gerados
		
		Runnable r = () -> {
			System.out.println("O que sou? Michael");
		};
		
		
		System.out.println(r);
		System.out.println(r.getClass());
		
		//Acessando Variavel
		
		int numero = 5;
		
		new Thread( 
				() -> System.out.println("Imprimir n: " + numero) 
		).start();
		
		
		 StringJoiner sj = new StringJoiner(":", "[", "]");
		 sj.add("George").add("Sally").add("Fred");
		 String desiredString = sj.toString();
		 
		 
		 System.out.println(desiredString);
		 
	}
	
}
