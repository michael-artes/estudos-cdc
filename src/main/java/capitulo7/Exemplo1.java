package capitulo7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import capitulo2.Usuario;

public class Exemplo1 {

	public static void main(String[] args) {

		Usuario user1 = new Usuario("Douglas", 150);
		Usuario user2 = new Usuario("Michael", 80);
		Usuario user3 = new Usuario("Moreira", 170);
		Usuario user4 = new Usuario("Moura", 130);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4);
		
		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		
		usuarios.subList(0, 3).forEach(u -> {
			u.tornarModerador();
			System.out.println("Tornou usuario " + u.getNome() + " como moderador..");
		});
		
		//Removendo usuarios com mais de 100 pontos
		Stream<Usuario> stream = usuarios.stream();
		stream.filter(u -> u.getPontos() > 100).forEach(u -> System.out.println("Usuario com mais de 100 pontos: " + u.getNome()));
		
		usuarios.sort(Comparator.comparing(Usuario::getNome));
		usuarios.forEach(u -> System.out.println("Lista antiga: " + u.getNome()));
		
		usuarios.forEach(Teste::testeTeste);
		
		
		//Salvando em uma listar usuarios maiores q 100 pontos
		List<Usuario> maiores100Pts = new ArrayList<>(0);
		usuarios.stream().filter(u -> u.getPontos() > 100).forEach(maiores100Pts::add);
		
		System.out.println("Salvando em uma listar usuarios maiores q 100 pontos");
		maiores100Pts.forEach(Teste::testeTeste);
		
		
		
		//Modo dificil de obter uma listar de usuarios maiores de 100 pontos
		Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
		BiConsumer<ArrayList<Usuario>, Usuario> accumulator = ArrayList::add;
		BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combiner = ArrayList::addAll;
		
		List<Usuario> maior100pts = usuarios.stream().collect(supplier, accumulator, combiner);
		
		//outra forma simplificada
		maior100pts = usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toList()); //O Collectors converte para qualquer lista
		
		Set<Usuario> setMaior100Pts = usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toSet());
		Set<Usuario> qualquerImplementacao = usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toCollection(HashSet::new));
		
		//Gerando uma lista somente com os pontos dos usuarios
		List<Integer> listPontos = usuarios.stream().map(Usuario::getPontos).collect(Collectors.toList());
		listPontos.forEach(System.out::println);
		
		
		IntStream toInt = usuarios.stream().mapToInt(Usuario::getPontos);
		System.err.println(toInt.average().getAsDouble());
		
		
		//obter a pontuação media dos usuarios
		double pontuacaoMedia = usuarios.stream().mapToDouble(Usuario::getPontos).average().orElse(0.0);
		System.out.println("Pontuacao Media: " + pontuacaoMedia);
		
		//usuario com a maior quantidade de pontos
		Optional<Usuario> maxPontos = usuarios.stream().max(Comparator.comparing(Usuario::getPontos));
		
		//Nome do usuario com maior quantidade de pontos
		Optional<String> maxNome = usuarios
				.stream()
				.max(Comparator.comparingInt(Usuario::getPontos))
				.map(Usuario::getNome);
	}

}
