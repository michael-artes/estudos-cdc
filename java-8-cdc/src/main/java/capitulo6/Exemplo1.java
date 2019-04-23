package capitulo6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import capitulo2.Usuario;

public class Exemplo1 {

	public static void main(String[] args) {

		Usuario user1 = new Usuario("Michael", 150);
		Usuario user2 = new Usuario("Douglas", 180);
		Usuario user3 = new Usuario("Moreira", 170);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		//Method Reference
		Consumer<Usuario> tornaModerador = Usuario::tornarModerador;
		usuarios.forEach(tornaModerador);
		
		usuarios.sort(Comparator.comparing(Usuario::getNome));
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		//outra forma
		Function<Usuario, String> byName = Usuario::getNome;
		Comparator<Usuario> comparing = Comparator.comparing(byName);
		
		usuarios.sort(comparing.reversed());
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		//ordenando pelos pontos
		usuarios.sort(Comparator.comparingInt(Usuario::getPontos));
		
		//Ordena pelos pontos caso empate ordena pelo nome
		usuarios.sort(	Comparator.comparingInt(Usuario::getPontos)
								  .thenComparing(Usuario::getNome)	);
		
		
		//Usuarios nullos serao jogados para o final da lista apos a ordenacao -- nullsFirst (primeiros)
		usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome)));
		
		//ordem decrescente
		usuarios.sort(Comparator.comparing(Usuario::getNome).reversed());
		
		
		//Method Reference com argumentos - vai printar o toString da Classe Usuario
		usuarios.forEach(System.out::println);
	}
	
}
