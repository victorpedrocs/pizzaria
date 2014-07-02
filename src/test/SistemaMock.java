package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

}
