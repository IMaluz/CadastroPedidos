package com.maria.pedido.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Produto produto;
	
	private int quantidade;

	private double subTotal;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}


	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", subTotal=" + subTotal
				+ ", pedido=" + "]";
	}
	
	
}
