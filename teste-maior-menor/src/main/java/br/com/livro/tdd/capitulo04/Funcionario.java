package br.com.livro.tdd.capitulo04;

public class Funcionario {
	private String nome;
	private double salario;
	private Cargo cargo;
	
	
	public Funcionario(String nome, double salario, Cargo cargo) {
		this.nome = nome;
		this.salario = salario;
		this.cargo = cargo;
	}


	public String getNome() {
		return nome;
	}


	public double getSalario() {
		return salario;
	}


	public Cargo getCargo() {
		return cargo;
	}
	
	
}
