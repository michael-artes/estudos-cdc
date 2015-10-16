package br.com.livro.tdd.capitulo04;

public class CalculadoraDeSalario {
	
	public double calculaSalario(Funcionario funcionario) {
		
		switch (funcionario.getCargo()) {
			case DBA:
				
				if(funcionario.getSalario() < 2500) {
					return funcionario.getSalario() * 0.85;
				}
				
				return funcionario.getSalario() * 0.75;
				
			case DESENVOLVEDOR:
				
					if(funcionario.getSalario() > 3000) {
						return funcionario.getSalario() * 0.8;
					}
					
					return funcionario.getSalario() * 0.9;
				
			case TESTADOR:
				
				break;
	
			default: throw new RuntimeException("Funcionario invalido");
		}
		
		return 0;
	}
	
}
