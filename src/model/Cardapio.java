package model;

import java.util.ArrayList;
import java.util.Collection;

public class Cardapio {
	
	private String nomePizza, ingredientes, preco;
	
	public Cardapio(String nomePizza, String ingredientes, String preco){
		this.nomePizza = nomePizza;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	
	public Cardapio(String nomePizza){
		this.nomePizza = nomePizza;
		this.ingredientes = null;
		this.preco = null;
	}
	
	public String getValuesString(){
		return new StringBuilder().append("'").append(this.nomePizza).append("','")
				.append(this.ingredientes).append("','")
				.append(this.preco).append("'")
				.toString();
	}
	
	public String getPrimaryKey(){
		return this.nomePizza;
	}
	
	public Collection<String> getValuesCollection(){
		Collection<String> fields = new ArrayList<String>();
		
		fields.add(this.nomePizza);
		fields.add(this.ingredientes);
		fields.add(this.preco);
		
		return fields;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ingredientes: ").append(this.ingredientes).append("\n");
		sb.append("Preco: ").append(this.preco);
		return sb.toString();
	}

}
