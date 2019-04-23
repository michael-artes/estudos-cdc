package capitulo11;

public class Customer {
	
	private String nome;
	
	public Customer(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Customer [nome=" + nome + "]";
	}
	
}
