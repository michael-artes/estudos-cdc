package capitulo9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import capitulo2.Usuario;

public class Exemplo2 {
	
	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Douglas", 30, true);
		Usuario user2 = new Usuario("Michael", 80, true);
		Usuario user3 = new Usuario("Moreira", 170);
		Usuario user4 = new Usuario("Moura", 100);
		Usuario user5 = new Usuario("Noeme", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);
		
		
		List<Usuario> filtradosOrdenados = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
		
		//Executando o pipeline em paralelo - processo anterior com multi thread
		List<Usuario> filtradosOrdenadosMultThread = usuarios.parallelStream()
				.filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
		
		long start = System.currentTimeMillis();
		
		//Testando o processo de parallel
		long sum = LongStream.range(1, 1_000_000_000)
			.parallel()
			.filter(x -> x % 2 == 0)
			.sum();
		
		long finaal = System.currentTimeMillis() -start;
		
		//sooma de todos os pares
		System.out.println(sum);
		
		System.out.println("Milisegundos ---> " + finaal);
	}

}
