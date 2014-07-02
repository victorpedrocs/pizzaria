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
import org.junit.Test;
import org.junit.Assert;

import com.sun.crypto.provider.RSACipher;


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
	public void testCadastraCliente() throws SQLException{
		
		try {
			SistemaMock.cadastra_cliente();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Statement stmt;
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		stmt = conn.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from cliente_teste where telefone = '99' and nome = 'VV' and endereco = 'AA'");
		
		ResultSet rs = stmt.executeQuery(sql.toString());
		
		Assert.assertEquals(true, rs.next());
		
		rs.close();
		conn.close();
	}
	
	@Test
	public void testCadastrarPizza() throws SQLException{
		try {
			SistemaMock.cadastra_pizza();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Statement statement;
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		statement = conn.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from CARDAPIO_TESTE where nome_pizza = 'bacon' and ingredientes = 'bacon' and preco = '22'");
		
		ResultSet rs = statement.executeQuery(sql.toString());
		
		Assert.assertEquals(true, rs.next());
		
		rs.close();
		conn.close();
	}
	
	@Test
	public void testPedido() throws SQLException{
		try {
			SistemaMock.cadastrar_pedido();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Statement statement;
		String telefone = "teste", nome_pizza = "teste";
		int quantidade = 2;
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		statement = conn.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM PEDIDO_TESTE WHERE telefone = '"+telefone+"'and nome_pizza = '"+nome_pizza+"' and quantidade = "+quantidade+";");
		
		ResultSet rs = statement.executeQuery(sql.toString());
		
		Assert.assertEquals(true, rs.next());
		
		rs.close();
		conn.close();
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
