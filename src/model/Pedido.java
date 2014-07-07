package model;

import java.util.Date;

import pizzaria.Pizza;

public class Pedido {
	
	private Cliente cliente;
	private Pizza pizza;
	private Date date;
	private Integer quantidade;
	
	public Pedido(Cliente cliente, Pizza pizza, Integer quantidade) {
		this.cliente = cliente;
		this.pizza = pizza;
		this.quantidade = quantidade;
		this.date = new Date();
	}

}
