package br.com.michael.loja.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.michael.loja.daos.UserDAO;
import br.com.michael.loja.models.User;

@Service
public class UserService {
	
	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Autowired
	private HttpServletRequest request;
	
	
	private BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
	
	public void processaInfosUser(User user){
		
		String senha = user.getPassword();
		
		user.setPassword(cript.encode(user.getPassword()));
		
		try {
			
			userDAO.salvarUsuario(user);
			userDAO.salvarUserRole(user);
		} catch (Exception e) {
			LOGGER.error("Não foi possível salvar o usuário teste.", e);
			return;
		}
		
		
		StringBuilder sb = builderCorpoEmail(user, senha);
		
		sendMail(user.getLogin(), sb);
		
	}

	private StringBuilder builderCorpoEmail(User user, String senha) {
		
		
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		
		LOGGER.info("URL-COMPLET: " + url);
		LOGGER.info("URI: " + url);
		
		String compleUri = url.replace(uri, "") + request.getContextPath();
		
		LOGGER.info("URL-TO-EMAIL: " + url);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("	<title>novo contato</title>");
		sb.append("</head>");
		
		sb.append("<body>");
		
		sb.append("	<p>");
		sb.append("		Novo usuario criado.");
		sb.append("		<br>");
		sb.append("		Acesse o link para ativar o seu usuário:");
		sb.append("		<hr> <br>");
		sb.append("		"+ compleUri +"/user/active?email=" + user.getLogin());
		sb.append("		<br>");
		sb.append("		Sua senha é: " + senha);
		sb.append("		<br>");
		sb.append("		Agradecemos o seu contato!");
		sb.append("	</p>");
		
		sb.append("</body>");
		sb.append("</html>");
		
		
		return sb;
	}
	
	private void sendMail(String mail, StringBuilder corpoEmail){
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper help = new MimeMessageHelper(message, true);
			help.setFrom("mmoreira.dg@gmail.com");
			help.setTo(mail);
			help.setSubject("Novo cadastro de usuário");
			help.setText(corpoEmail.toString(), true);
			mailSender.send(message);
			
		} catch (MessagingException e) {
			LOGGER.error("Email invalid!", e);
		}
		
		
		LOGGER.info("Email enviado com sucesso!");
	}	
	
	
	public User getUSerByEmail(String email){
		return (User) userDAO.loadUserByUsername(email);
	}
	
	public void atualizarUsuario(User user){
		userDAO.atualizarUsuario(user);
	}

}
