package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;

public class CardapioDAO {
	
	private Connection connection;
	
	public CardapioDAO( Connection con ) {
		this.connection = con;
	}
	
	public Cardapio retrieve(Cardapio cardapio){
		Statement statement;
		ResultSet result;
		
		try {
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT nome_pizza, ingredientes, preco FROM cardapio WHERE 1=1 ");
			
			Collection<String> cardapioValues = cardapio.getValuesCollection();
			Iterator<String> iterator = cardapioValues.iterator();
			String nomePizza = iterator.next();
			String ingredientes = iterator.next();
			String preco = iterator.next();
			
			if (nomePizza != null && nomePizza != "") {
				sql.append("AND nome_pizza = '").append(nomePizza).append("'");
			}
			if (ingredientes != null && ingredientes != "") {
				sql.append("AND ingredientes = '").append(ingredientes).append("'");
			}
			if (preco != null && preco != "") {
				sql.append("AND preco = '").append(preco).append("'");
			}
			
			result = statement.executeQuery(sql.toString());
			if (result.next()) {
				return new Cardapio(result.getString("NOME_PIZZA"), result.getString("INGREDIENTES"), result.getString("PRECO"));
			}
			else return null;
			
			
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}
	}
	
	public boolean create(Cardapio cardapio){
		
		Statement statement;
		
		try {
			
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cardapio VALUES(")
				.append(cardapio.getValuesString())
				.append(");");
			
			statement.executeUpdate(sql.toString());
			statement.close();
			return true;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage())); e.printStackTrace();
			return false;
		}
	}
}
