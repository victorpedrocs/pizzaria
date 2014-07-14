package model;

public class Pedido {
	
	private Cliente cliente;
	private Cardapio pizza;
	private Integer quantidade;
	
	public Pedido(Cliente cliente, Cardapio pizza, Integer quantidade) {
		this.cliente = cliente;
		this.pizza = pizza;
		this.quantidade = quantidade;
		
	}
	
	public String getValues(){
		return new StringBuilder().append(" '").append(this.cliente.getPrimaryKey()).append("' , ")
				.append(" current_timestamp, '")
				.append(this.pizza.getPrimaryKey()).append("' , '")
				.append(this.quantidade).append("' ")
				.toString();
	}
	
	

}
