package capitulo9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import capitulo2.Usuario;

public class Exemplo1 {

	public static void main(String[] args) {

		Usuario user1 = new Usuario("Douglas", 30, true);
		Usuario user2 = new Usuario("Michael", 80, true);
		Usuario user3 = new Usuario("Moreira", 170);
		Usuario user4 = new Usuario("Moura", 100);
		Usuario user5 = new Usuario("Noeme", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);

		try {
			//manipulando arquivos com stream do Files
			// e Exemplo com o flatMap
			Path dir = Paths.get("/home/mmoreira/git/estudos-cdc/src/main/java");

			Files.list(dir)
			.filter(p -> p.toString().endsWith(".java"))
			.flatMap(p -> lines(p))
			.forEach(System.out::println);

			LongStream mapToLong = Files.list(dir)
					.filter(p -> p.toString().endsWith(".java"))
					.mapToLong(p -> lines(p).count());

			//Convert to Long
			List<Long> lines = Files.list(dir)
					.filter(p -> p.toString().endsWith(".java"))
					.map(p -> lines(p).count())
					.collect(Collectors.toList());

			//Quantidade de linhas por arquivo
			Map<Path, Long> linesPerFile = new HashMap<>();

			Files.list(dir)
			.filter(p -> p.toString().endsWith(".java"))
			.forEach(p ->
			linesPerFile.put(p, lines(p).count()));

			System.out.println(linesPerFile);


			//Criando com outro tipo de colletor
			Map<Path, Long> linhas = Files.list(dir)
					.filter(p -> p.toString().endsWith(".java"))
					.collect(Collectors.toMap(
							p -> p,
							p -> lines(p).count()));

			//outro collector
			Map<Path, List<String>> collect = Files.list(dir)
					.filter(p -> p.toString().endsWith(".java"))
					.collect(Collectors.toMap(
							Function.identity(), 
							p -> lines(p).collect(Collectors.toList())));

			System.out.println(linhas);
			



		} catch (IOException e) {
			e.printStackTrace();
		}

		//Mapear todos os usuários utilizando seu nome como chave fica fácil
		Map<String, Usuario> usuariosMap = usuarios.stream()
				.collect(Collectors.toMap(
						Usuario::getNome, 
						Function.identity()));



		//modo tradicional
		Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
		for(Usuario u: usuarios) {
			if(!pontuacao.containsKey(u.getPontos())) {
				pontuacao.put(u.getPontos(), new ArrayList<>());
			}
			pontuacao.get(u.getPontos()).add(u);
		}
		
		System.out.println(pontuacao);
		System.out.println("-----------JAVA8------------");
		pontuacao.clear();
		
		for (Usuario u : usuarios) {
			pontuacao.computeIfAbsent(u.getPontos(), ArrayList::new)
				.add(u);
		}
		

		System.out.println(pontuacao);
		pontuacao.clear();
		
		//realizando o trabalho anterior com um collector especifico
		pontuacao = usuarios.stream().collect(Collectors.groupingBy(Usuario::getPontos));
		System.out.println("-----------JAVA8 Collectors.groupingBy ------------");
		System.out.println(pontuacao);
		pontuacao.clear();
		
		//particionando que e moderador 
		Map<Boolean, List<Usuario>> collect = usuarios.stream().collect(Collectors.partitioningBy(Usuario::isModerador));
		System.out.println("-----------JAVA8 Collectors.partitioningBy ------------");
		System.out.println(collect);

		
		//particionando que e moderador com nomes
		Map<Boolean, List<String>> collectNomes = usuarios.stream().collect(
				Collectors.partitioningBy(
						Usuario::isModerador, 
						Collectors.mapping(
								Usuario::getNome, 
								Collectors.toList())));
	
		System.out.println("-----------JAVA8 Collectors.mapping ------------");
		System.out.println(collectNomes);
		
		//particionando que e moderador e soma da pontuacao de todos agrupados
		Map<Boolean, Integer> collect2 = usuarios.stream().collect(Collectors.partitioningBy(
				Usuario::isModerador, 
				Collectors.summingInt(Usuario::getPontos)));
		
		System.out.println("-----------JAVA8 Collectors.summingInt ------------");
		System.out.println(collect2);
		
		
		//concatenar nomes
		String nomes = usuarios.stream().map(Usuario::getNome).collect(Collectors.joining(", "));
		System.out.println("-----------JAVA8 Collectors.joining ------------");
		System.out.println(nomes);
		
		
		
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
