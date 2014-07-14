package pizzaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static Properties properties = new Properties();
	private static final String url = "jdbc:postgresql://localhost:5432/pizzaria";
	private static Connection connection;
	
	static {
		properties.put("user", "pizzaria");
		properties.put("password", "pizzaria");
		
		try {
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			connection = null;
			System.err.println("Falha de Conexao");
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
	public void closeConnection() throws SQLException{
		connection.close();
	}
	
}
