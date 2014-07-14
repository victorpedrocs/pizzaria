package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;

public class ClienteDAO {
	
	private Connection connection;
	
	public ClienteDAO(Connection con){
		this.connection = con;
	}
	
	public Cliente retrieve(Cliente cliente){
		Statement statement;
		ResultSet result;
		
		try {
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT telefone, nome, endereco FROM cliente WHERE 1=1 ");
			
			Collection<String> clienteValues = cliente.getValuesCollection();
			Iterator<String> iterator = clienteValues.iterator();
			String telefone = iterator.next();
			String nome = iterator.next();
			String endereco = iterator.next();
			
			if (telefone != null && telefone != "") {
				sql.append("AND telefone = '").append(telefone).append("'");
			}
			if (nome != null && nome != "") {
				sql.append("AND nome = '").append(nome).append("'");
			}
			if (endereco != null && endereco != "") {
				sql.append("AND endereco = '").append(endereco).append("'");
			}
			
			result = statement.executeQuery(sql.toString());
//			statement.close();
			if (result.next()) {
				return new Cliente(result.getString("TELEFONE"), result.getString("NOME"), result.getString("ENDERECO"));
			}
			
			else return null;		
			
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}
	}
	
	public boolean create(Cliente cliente){
		
		Statement statement;
		
		try {
			
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cliente VALUES(")
				.append(cliente.getValuesString())
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
