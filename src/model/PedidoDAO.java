package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PedidoDAO {
	
	private Connection connection;
	
	public PedidoDAO(Connection con){
		this.connection = con;
	}
	
	public static Pedido retrieve(Pedido cardapio){
		return null;
	}
	
	public boolean create(Pedido pedido){
		Statement statement;
		
		try {
			statement = this.connection.createStatement();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO pedido VALUES(")
				.append(pedido.getValues())
				.append(")");
			
			statement.executeUpdate(sql.toString());
			statement.close();
			return true;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return false;
		}
	}

}
