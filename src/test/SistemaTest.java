package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cardapio;
import model.CardapioDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Pedido;
import model.PedidoDAO;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import pizzaria.ConnectionFactory;


public class SistemaTest {
	
	static Connection conn;
	
	@BeforeClass
	public static void setUp() throws SQLException{
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");
	}
	
	@Test
	public void testConnectionFactory() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		Assert.assertEquals(false, con.isClosed());
	}
	
	
	@Test 
	public void testInsereCliente() throws SQLException{
		String teste = "teste";
		ClienteDAO clienteDAO = new ClienteDAO(conn);
		clienteDAO.create(new Cliente(teste, teste, teste));
		
		String sql = "SELECT * FROM cliente WHERE telefone = 'teste' AND nome = 'teste' AND endereco = 'teste'";
		Statement statement = conn.createStatement();
		
		ResultSet result = statement.executeQuery(sql);
		assertEquals(true, result.next());
		
	}
	
	
	@Test
	public void testeInsereCardapio() throws SQLException{
		String teste = "teste";
		CardapioDAO cardapioDAO = new CardapioDAO(conn);
		cardapioDAO.create(new Cardapio(teste, teste, teste));
		
		String sql = "SELECT * FROM cardapio WHERE nome_pizza = 'teste' AND ingredientes = 'teste' AND preco = 'teste'";
		Statement statement = conn.createStatement();
		
		ResultSet result = statement.executeQuery(sql);
		assertEquals(true, result.next());
	}
	
	
	@Test
	public void testeInserePedido() throws SQLException {
		String teste = "teste2";
		PedidoDAO pedidoDAO = new PedidoDAO(conn);
		CardapioDAO cardapioDAO = new CardapioDAO(conn);
		ClienteDAO clienteDAO = new ClienteDAO(conn);
		
		Cliente cliente = new Cliente(teste, teste, teste);
		Cardapio cardapio = new Cardapio(teste, teste, teste);
		clienteDAO.create(cliente);
		cardapioDAO.create(cardapio);
		
		pedidoDAO.create(new Pedido(cliente, cardapio, 1));
		
		
		String sql = "SELECT * FROM pedido WHERE telefone = 'teste2' AND nome_pizza = 'teste2' AND quantidade = 1";
		Statement statement = conn.createStatement();
		
		ResultSet result = statement.executeQuery(sql);
		assertEquals(true, result.next());
		
	}
	
	@AfterClass
	public static void afterTest() throws SQLException{
		Statement stmt;
		stmt = conn.createStatement();
		String sqlDropCliente1 = "DELETE FROM cliente WHERE telefone = 'teste' AND nome = 'teste' AND endereco = 'teste'";
		String sqlDropCliente2 = "DELETE FROM cliente WHERE telefone = 'teste2' AND nome = 'teste2' AND endereco = 'teste2'";
		String sqlDropCardapio1 = "DELETE FROM cardapio WHERE nome_pizza = 'teste' AND ingredientes = 'teste' AND preco = 'teste'";
		String sqlDropCardapio2 = "DELETE FROM cardapio WHERE nome_pizza = 'teste2' AND ingredientes = 'teste2' AND preco = 'teste2'";
		String sqlDropPedido = "DELETE FROM pedido WHERE telefone = 'teste2' AND nome_pizza = 'teste2' AND quantidade = 1";
		
		stmt.executeUpdate(sqlDropCardapio1);
		stmt.executeUpdate(sqlDropCliente1);
		stmt.executeUpdate(sqlDropPedido);
		stmt.executeUpdate(sqlDropCardapio2);
		stmt.executeUpdate(sqlDropCliente2);
		
		conn.close();
		
	}
	
	

}
