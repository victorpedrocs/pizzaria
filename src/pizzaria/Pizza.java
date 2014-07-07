package pizzaria;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;

public class Pizza {
	
	String nome;
	Collection<String> ingredientes;
	Double preco;
	
	public Pizza(String nome, Collection<String> ingredientes, Double preco){
		if( nome != null && !nome.isEmpty() )
			this.nome = nome;
		if( ingredientes != null && ingredientes.size() != 0 )
			this.ingredientes = ingredientes;
		if( preco != null && preco != 0.0)
			this.preco = preco;
	}
	
	public Boolean isValid(){
		if (this.nome != null && this.ingredientes != null && this.preco != null)
			return true;
		else return false;
	}
	
	public static Boolean inserirPizza( Pizza pizza ){
		if( pizza != null && pizza.isValid()){
			Connection connection;
			Statement statement;
			
			
			
		}
		
		return false;
	}

}
