package br.com.design.pattern.capitulo01;

public class ContaEstacionamento {
	
	private Veiculo veiculo;
	private int inicio, fim;
	
	
	public ContaEstacionamento(int inicio) {
		this.inicio = inicio;
	}
	
	
	public double valorConta(){
		
		int atual = (fim == 0) ? 1 : fim;
		int periodo = inicio - atual;
		
		
		if (veiculo instanceof Passeio) {
			
			if ( periodo < (12 * veiculo.getHora()) ) {
				return 2.0 * Math.ceil(periodo / veiculo.getHora());
			} else if (periodo > (12 * veiculo.getHora()) && periodo < (15 * veiculo.getDia()) ) {
				return 26.0 * Math.ceil(periodo / veiculo.getDia());
			} else {
				return 300.0 * Math.ceil(periodo / veiculo.getMes());
			}
			
			
		} else if (veiculo instanceof Carga) {
			// regra para outros tipos de veiculos
		}
		
		return 0;
	}
	
	

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
}
