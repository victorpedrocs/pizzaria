package test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

import pizzaria.ConnectionFactory;


public class SistemaTest {
	
	static Connection conn;
	
	@BeforeClass
	public static void setUp() throws SQLException{
		Statement stmt;
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		stmt = conn.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append("create table cliente_teste (telefone varchar primary key, nome varchar, endereco varchar); ");
		sql.append("create table cardapio_teste (nome_pizza varchar primary key, ingredientes varchar, preco varchar); ");
		sql.append("create table pedido_teste (telefone varchar,"); 
			sql.append("data_hora timestamp, ");
			sql.append("nome_pizza varchar, ");
			sql.append("quantidade int, ");
			sql.append("constraint pk_teste primary key (telefone, data_hora, nome_pizza), "); 
			sql.append("constraint fk_cliente_teste foreign key (telefone) references cliente_teste (telefone), "); 
			sql.append("constraint fk_cardapio_teste foreign key (nome_pizza) references cardapio_teste(nome_pizza)); ");
		stmt.executeUpdate(sql.toString());
		
		
		sql = new StringBuilder("INSERT INTO cliente_teste VALUES('teste', 'teste', 'teste'); ");
		String nome_pizza = "teste", ingredientes = "teste", preco = "teste";
		sql.append("INSERT INTO CARDAPIO_TESTE VALUES('")
			.append(nome_pizza).append("', '")
			.append(ingredientes).append("', '")
			.append(preco).append("')");
		
		stmt.executeUpdate(sql.toString());
		conn.close();
	}
	
	@Test
	public void testConnectionFactory() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		Assert.assertEquals(false, con.isClosed());
	}
	
	@AfterClass
	public static void afterTest() throws SQLException{
		Statement stmt;
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		stmt = conn.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append("drop table if exists pedido_teste; ")
			.append("drop table if exists cliente_teste; ")
			.append("drop table if exists cardapio_teste; ");
		
		
		stmt.executeUpdate(sql.toString());
		conn.close();
		
	}
	
	

}
