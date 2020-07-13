package com.maria.pedido.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private double valor;
	private int quantidadeEstoque;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantideEstoque) {
		this.quantidadeEstoque = quantideEstoque;
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantideEstoque=" + quantidadeEstoque
				+ "]";
	}
}
