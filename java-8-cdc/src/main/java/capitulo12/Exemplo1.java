package capitulo12;

import java.lang.reflect.Constructor;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

import capitulo2.Usuario;

public class Exemplo1 {
	
	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Douglas", 30, true);
		Usuario user2 = new Usuario("Michael", 80, true);
		Usuario user3 = new Usuario("Moreira", 170);
		Usuario user4 = new Usuario("Admin", 40);
		Usuario user5 = new Usuario("Noeme", 100);
		Usuario user6 = new Usuario("Noeme", 80);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5, user6);
		
		System.out.println("-------------------------");
		System.out.println("Antes de Ordenar");
		usuarios.forEach(u -> System.out.println(u.getPontos() + " nome=" + u.getNome()));
		
		//Method reference funciona pois o mesmo ja define os tipos Genericos
		//por isso o compilador ja entende qual Target Object sera inferido
		usuarios.sort(Comparator.comparing(Usuario::getPontos)
								.thenComparing(Usuario::getNome));
		
		
		//nao compila precisamos inferir q o "u" e um Usuario
		/*usuarios.sort(Comparator.comparing(u -> u.getPontos())
								  .thenComparing(u -> u.getNome()) );*/
		
		usuarios.sort(Comparator.comparing((Usuario u) -> u.getPontos())
								.thenComparing(u -> u.getNome()) );
		
		//ou
		usuarios.sort(Comparator.<Usuario>comparingInt(u -> u.getPontos())
								.thenComparing(u -> u.getNome()) );
		
		//ou
		Comparator<Usuario> comparator = Comparator.comparing(u -> u.getPontos());
		usuarios.sort(comparator.thenComparing(u -> u.getNome()));
		
		
		usuarios.sort(Comparator.comparing(Usuario::getNome).reversed());
//		usuarios.sort(Comparator.comparing(u -> u.getPontos()).reversed()); Nao compila
		
		
		System.out.println("-------------------------");
		System.out.println("Depois de Ordenar");
		usuarios.forEach(u -> System.out.println(u.getPontos() + " nome=" + u.getNome()));
		
		System.out.println("-------------------------");
		Supplier<String> s = () -> "Executando Supplier";
		PrivilegedAction<String> p = () -> "Executando PrivilegedAction";
		execute(p::run);
		execute(s);
		
//		execute(p); nao compila
		
		
		RelatorioController relatorio = new RelatorioController();
		Role[] annotations = relatorio.getClass().getAnnotationsByType(Role.class);
		List<Role> roles = Arrays.asList(annotations);
		
		System.out.println("-------------------------");
		roles.forEach(r -> System.out.println(r.value()));
		
		
		System.out.println("Reflection: parameter names -------------------------");
		try {
			Constructor<Usuario> construct = Usuario.class.getConstructor(String.class, int.class);
			Class<?>[] parameterTypes = construct.getParameterTypes();
//			
			//compilar -parameter para exibir os nomes corretamente
			Arrays.asList(parameterTypes).forEach(param -> System.out.println(param.getName() + ": " + param.getSimpleName()));
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void execute(Supplier<String> t){
		System.out.println(t.get());
	}

}
