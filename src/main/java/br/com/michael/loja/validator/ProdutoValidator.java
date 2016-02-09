package br.com.michael.loja.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.michael.loja.models.Produto;

public class ProdutoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "field.required.produto.titulo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "field.required.produto.descricao");
		
		Produto p = (Produto) target;
		
		if (p.getPaginas() == null || p.getPaginas() == 0) {
			errors.rejectValue("paginas", "field.required");
		}
		
	}

}
