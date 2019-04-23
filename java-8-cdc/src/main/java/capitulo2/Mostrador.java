package capitulo2;

import java.util.function.Consumer;

public class Mostrador implements Consumer<Usuario> {

	@Override
	public void accept(Usuario u) {
		System.out.println("Java 8: " + u.getNome());
	}

}
