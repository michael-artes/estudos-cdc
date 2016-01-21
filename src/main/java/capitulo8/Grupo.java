package capitulo8;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import capitulo2.Usuario;

public class Grupo {

	private Set<Usuario> usuarios = new HashSet<>();
	
	 
	public void add(Usuario u){
		usuarios.add(u);
	}
	
	public Set<Usuario> getUsuarios(){
		return Collections.unmodifiableSet(usuarios);
	}
	
}
