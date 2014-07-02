package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import pizzaria.Sistema;


public class SistemaTest {
	
	Connection conn;
	
	@BeforeClass
	public void setUp() throws SQLException{
		Statement stmt;
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		stmt = conn.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append("create table cliente_teste (telefone varchar primary key, nome varchar, endereco varchar)");
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
		
		Assert.assertEquals(1, rs.next());
		
		conn.close();
	}
	
	@After
	public void afterTest() throws SQLException{
		Statement stmt;
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
		stmt = conn.createStatement();
		
		StringBuilder sql = new StringBuilder();
		sql.append("drop table cliente_teste;");
		
		stmt.executeUpdate(sql.toString());
		conn.close();
		
	}
	
	

}
