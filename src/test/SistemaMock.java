package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Statement;

public class SistemaMock {
	
	public static void cadastra_cliente() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conexao;
		Statement comandoSQL;
		
		String sql = "INSERT INTO cliente_teste VALUES('99', 'VV', 'AA')";
		Class.forName("org.postgresql.Driver").newInstance();
		conexao = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		comandoSQL = conexao.createStatement();
		comandoSQL.executeUpdate(sql);
	}

	public static void cadastra_pizza() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection conexao;
		Statement comandoSQL;
		
		String nome_pizza = "bacon", ingredientes = "bacon", preco = "22";
		String sql = "INSERT INTO CARDAPIO_TESTE VALUES('"+nome_pizza+"', '"+ingredientes+"', '"+preco+"')";
		Class.forName("org.postgresql.Driver").newInstance();
		
		conexao = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");

		comandoSQL = conexao.createStatement();
		comandoSQL.executeUpdate(sql);
	}
	
	public static void cadastrar_pedido() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection conexao;
		Statement comandoSQL;
		ResultSet resultado;
		
		String telefone = "teste", nome_pizza = "teste";
		int quantidade = 2;
		
		Class.forName("org.postgresql.Driver").newInstance();

		conexao = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");

		comandoSQL = conexao.createStatement();
	
		comandoSQL.executeUpdate("INSERT INTO PEDIDO_TESTE VALUES('"+telefone+"', current_timestamp, '"+nome_pizza+"', "+quantidade+")");
		
	
	}
}
