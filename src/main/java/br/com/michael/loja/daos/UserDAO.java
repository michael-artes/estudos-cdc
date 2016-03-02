package br.com.michael.loja.daos;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.michael.loja.models.User;

@Repository
public class UserDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager entityManager;

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

}
