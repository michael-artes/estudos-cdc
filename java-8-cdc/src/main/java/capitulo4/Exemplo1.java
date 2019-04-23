package capitulo4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import capitulo2.Usuario;

public class Exemplo1 {
	
	public static void main(String[] args) {

		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 70);
		
		List<Usuario> users = Arrays.asList(user1, user2, user3);
		
		Consumer<Usuario> mostraMensagem = u -> System.out.println("Antes de Imprimir os nomes");
		Consumer<Usuario> imprimirNome = u -> System.out.println(u.getNome());
		
		users.forEach(mostraMensagem.andThen(imprimirNome));
		
		//Usando o Predicate
		
		List<Usuario> usersAll = new ArrayList<>(users);
		
		System.out.println("\n\nRemove os maiores que 100 pontos");
		usersAll.removeIf( u -> u.getPontos() > 100 ); //Remove os maiores que 100 pontos
		usersAll.forEach(u -> System.out.println(u.getNome()));

		
		Configuration c = new Configuration();
		
		users.forEach(u -> c.setAddResource(u.getNome()));
		
		System.out.println("AddUltimoNome: " + c.getResource());
	}

}
