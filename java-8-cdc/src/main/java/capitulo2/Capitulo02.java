package capitulo2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Capitulo02 {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Michael", 150);
		Usuario user2 = new Usuario("Douglas", 180);
		Usuario user3 = new Usuario("Moreira", 170);
		
		List<Usuario> list = Arrays.asList(user1, user2, user3);
		
		
		//Forma antiga
		for (Usuario usuario : list) {
			System.out.println(usuario.getNome()); 
		}
		
		//O mostrador pode ser criado com classes anonimas
		list.forEach(new Mostrador());
		
		//Com classe anonima
		list.forEach(new Consumer<Usuario>() {
			@Override
			public void accept(Usuario t) {
				System.out.println(t.getNome());
			}
		});
		
		
		//Simplificando ainda mais
		list.forEach(u -> System.out.println(u.getNome()));
		
		//Torna usuarios moderadores
		list.forEach(u -> u.tornarModerador());
		
	}
	
}
