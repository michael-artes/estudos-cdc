package capitulo3;

public class Exemplo1 {
	
	public static void main(String[] args) {
		
		
		//Modo antigo
		Runnable r = new Runnable() {
			
			long inicial = System.currentTimeMillis();
			long timeFinal;
			
			@Override
			public void run() {

				for (int i = 0; i <= 1000; i++) {
					System.out.println("-"+i+"-");
				}
				
				timeFinal = System.currentTimeMillis();
				
				
				System.out.println("Tempo de execucao " + (timeFinal - inicial) + " milisegundos");
 			}
		};
		
		new Thread(r).start();
		
		
		System.out.println("-------------");
		System.out.println("Modo Java 8");
		System.out.println("-------------");
		
		//Modo Java 8
		Runnable r2 = () -> {
			
			long inicial = System.currentTimeMillis();
			long timeFinal;
			
			for (int i = 0; i <= 1000; i++) {
				System.out.println("-"+i+"-");
			}
			
			timeFinal = System.currentTimeMillis();
			
			
			System.out.println("Tempo de execucao " + (timeFinal - inicial) + " milisegundos");
			
		};
		
		new Thread(r2).start();
	}

}
