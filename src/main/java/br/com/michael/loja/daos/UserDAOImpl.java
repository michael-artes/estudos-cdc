package br.com.michael.loja.daos;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.michael.loja.models.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Transactional
	public void salvarUsuario(User user){
		entityManager.persist(user);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String hql = "SELECT u FROM User u WHERE u.login = :login";
		
		User user = entityManager.createQuery(hql, User.class)
					 .setParameter("login", username).getSingleResult();
		
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("O " + username + " não foi encontrado");
		}
		
		return user;
	}
	
	
	@Transactional
	public void salvarUserRole(User user){
		
		Query nativeQuery = entityManager.createNativeQuery("INSERT INTO public.user_role (user_login, roles_name) VALUES (?,?)");
		
		nativeQuery.setParameter(1, user.getLogin());
		nativeQuery.setParameter(2, user.getEnumRoles().toString());
		
		nativeQuery.executeUpdate();
	}

	
	@Transactional
	public void atualizarUsuario(User user) {
		entityManager.merge(user);
	}
	

}
