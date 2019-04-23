package capitulo5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import capitulo2.Usuario;

//Ordenações
public class Exemplo1 {
	
	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Michael", 150);
		Usuario user2 = new Usuario("Douglas", 180);
		Usuario user3 = new Usuario("Moreira", 170);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		System.out.println("-------MODO ANTIGO--------");
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario o1, Usuario o2) {
				return o1.getNome().compareTo(o2.getNome());
			}
		});
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		System.out.println("-------MODO JAVA 8--------");
		
		Comparator<Usuario> comparator = (o1, o2) -> {
			
			Optional<String> print = Optional.ofNullable("Mensagem para imprimir");
			print.ifPresent(System.out::println);
			
			return o1.getNome().compareTo(o2.getNome());
		};
		
		Collections.sort(usuarios, comparator);
		
		usuarios.forEach(u -> System.out.println("-------- Java 8 " + u.getNome()));
		
		//Simplificando mais ainda
		
		usuarios.sort(comparator);
		usuarios.forEach(u -> System.out.println("-------- Java 8 Simplificado no List" + u.getNome()));
		
		//Metodos estaticos nas interfaces
		
		Comparator<Usuario> comparing = Comparator.comparing(Usuario::getNome);
		usuarios.sort(comparing);
		usuarios.forEach(u -> System.out.println("Metodos estaticos nas interfaces -------- Java 8 Simplificado no List: " + u.getNome()));
		
		
		List<String> palavras = Arrays.asList("Casa do codigo","Alura","Caelum");
		palavras.sort(Comparator.naturalOrder());	
		palavras.forEach(p -> System.out.println(p));
		
		//Ordenando por pontos
		
		Comparator<Usuario> compare = Comparator.comparingInt(Usuario::getPontos);
		usuarios.sort(compare);
		usuarios.forEach(u -> System.out.println("Ordenacao por pontos: " + u.getNome() + " pontos: " + u.getPontos()));
	}

}
