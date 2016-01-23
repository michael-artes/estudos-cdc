package capitulo8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import capitulo2.Usuario;

public class Exemplo1 {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Douglas", 30);
		Usuario user2 = new Usuario("Michael", 80);
		Usuario user3 = new Usuario("Moreira", 170);
		Usuario user4 = new Usuario("Moura", 130);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4);	
		
		//Forma simples de ordenação
		usuarios.sort(Comparator.comparing(Usuario::getNome));
		
		//Forma de se ordenar um stream
		usuarios.stream()
				.filter( u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
		
		
		//Pegando o primeiro item
		//Problema irá retornar uma lista com todos elementos mas irá retornar o primeiro
		//e se nao tiver nenhum podera retornar uma exception
		 usuarios.stream()
		 		.filter(u -> u.getPontos() > 100)
		 		.peek(System.out::println)
				.collect(Collectors.toList())
				.get(0);
		
		//Optional Stream findAny
		//Este método retorna o primeiro item encontrado depois da filtragem
		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.peek(System.out::println)
			.findAny();
		
		//devolve um optional
		usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.peek(System.out::println)
				.findFirst();
		
		usuarios.stream()
				.sorted(Comparator.comparing(Usuario::getNome))
				.peek(System.out::println)
				.findAny();
		
		//Retorna um Optional
		//Usuario com maior pontuacao
		usuarios.stream()
				.max(Comparator.comparing(Usuario::getPontos));
		
		//Soma a pontuacao de todos usuarios
		usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.sum();
		
		
		//Operacoes com reducao
		int valorInicial = 0;
		IntBinaryOperator operator = (a, b) -> a + b;
		
		int valor = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.reduce(valorInicial, operator);
		
		System.out.println("Valor utilizando reducao: " + valor);
		
		//Simplificando ainda mais
		usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.reduce(0, Integer::sum);
		
		//Multiplicando
		usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.reduce(1, (a, b) -> a * b);
		
		//Caso em que nao necessita invocar o map ou mapToInt
		usuarios.stream()
				.reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);
		
		//itera usuarios com o iterator do stream
		usuarios.stream()
				.iterator().forEachRemaining(System.out::println);
		
		//momentos em que utilizamos o predicate mais nao precisamos de filtragem
		//ira informar se dentro da lista de usuarios existem um moderador - retorna boolean
		usuarios.stream()
				.anyMatch(Usuario::isModerador);
		
		
		//Há modificações também na API antiga para trabalhar com Streams infinitos.
		//Você pode ver que a classe java.util.Random já devolve Stream s infinitos,
		//através de métodos como Random.ints()
		
		//Streams infinitos
		Random random = new Random(0);
		Supplier<Integer> s = () -> random.nextInt();
		Stream<Integer> generate = Stream.generate(s);
		
		
		IntStream generate2 = IntStream.generate(() -> random.nextInt());
		generate2.limit(100).boxed().collect(Collectors.toList());
		
		
		//Numeros fibonacci
		IntStream.generate(new ExampleFibonacci())
				.limit(100)
				.forEach(System.out::println);
		
		
		//pega o primeiro maior que 100
		int maiorQue100 = IntStream
				.generate(new ExampleFibonacci())
				.filter(f -> f > 100)
				.findFirst()
				.getAsInt();
		
		System.out.println(maiorQue100);
		
		System.out.println("-----------------------------------------------");
		
		IntStream.iterate(0, x -> x + 1)
				.limit(10)
				.forEach(System.out::println);
		
		
		//manipulando arquivos com stream do Files
		// e Exemplo com o flatMap
		try {
			Files.list(Paths.get("/home/mmoreira/git/estudos-cdc/src/main/java"))
				.filter(p -> p.toString().endsWith(".java"))
				.flatMap(p -> lines(p))
				.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		//flatMap encadeado
		try {
			
			IntStream chars = Files.list(Paths.get("/home/mmoreira/git/estudos-cdc/src/main/java"))
					.filter(p -> p.toString().endsWith(".java"))
					.flatMap(p -> lines(p))
					.flatMapToInt((String j) -> j.chars());
			
			chars.forEach(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		
		
		//Mais exemplos com flatMap
		Grupo english = new Grupo();
		english.add(user1);
		english.add(user2);
			
		
		Grupo spain = new Grupo();
		spain.add(user3);
		
		
		List<Grupo> grupos = Arrays.asList(english, spain);
		
		grupos.stream()
				.flatMap(g -> g.getUsuarios().stream())
				.distinct()
				.forEach(System.out::println);
		
	}

	
	
	
	private static Stream<String> lines(Path p) {
		
		try {
			return Files.lines(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Stream.generate(() -> "");
	}
}
