package br.com.design.pattern.capitulo01;

public class Veiculo {

	private int hora;
	private int dia;
	private int mes;
	
	private double valorDiaria;
	
	
	public Veiculo(int hora, int dia, int mes, double valorDiaria) {
		this.hora = hora;
		this.dia = dia;
		this.mes = mes;
		this.valorDiaria = valorDiaria;
	}


	
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public double getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
}
