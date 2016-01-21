package capitulo6;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import capitulo2.Usuario;

public class Exemplo2 {

	public static void main(String[] args) {
		
		
		Usuario michael = new Usuario("Michael - primeiro", 50);
		
		//Forma 1
		Runnable bloco = michael::tornarModerador;
		Runnable bloco2 = () -> michael.tornarModerador();
		bloco.run();
		bloco2.run();
		
		//Forma 2
		
		Consumer<Usuario> user1 = Usuario::tornarModerador;
		Consumer<Usuario> user2 = u -> u.tornarModerador();
		user1.accept(michael);
		user2.accept(michael);
		
		Supplier<Usuario> mmoreira = Usuario::new;
		Usuario usuario = mmoreira.get(); 
		
		Function<String, Usuario> mmoreira2 = Usuario::new;
		Usuario usuario2 = mmoreira2.apply("Michael");
		
		BiFunction<String, Integer, Usuario> douglasB = Usuario::new;
		Usuario douglas = douglasB.apply("Dougkas", 50);
		
		List<Usuario> users = Arrays.asList(michael, usuario, usuario2, douglas);
		users.forEach(System.out::println);
		
	}
}
