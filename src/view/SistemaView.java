package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Cardapio;
import model.Cliente;
import model.Pedido;


public class SistemaView {
	
	
	public Cliente recuperarDadosCliente(){
		
		String telefone, nome, endereco;
				
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("TELEFONE DO NOVO CLIENTE: ");
			telefone = reader.readLine();
			System.out.println("NOME DO NOVO CLIENTE: ");
			nome = reader.readLine();
			System.out.println("ENDERECO DO NOVO CLIENTE: ");
			endereco = reader.readLine();
			
			if( telefone != null && nome != null && endereco != null){
				return new Cliente(telefone, nome, endereco);
			}
			
		} catch (IOException e) {
			System.out.println("OCORREU ERRO DE ENTRADA E SAÍDA, TENTE NOVAMENTE \n");
			return recuperarDadosCliente();
		}
		
		return null;
	}
	
	public Cardapio recuperarNovoCardapio(){
		
		String nomePizza, ingredientes, preco;
		
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("NOME DA NOVA PIZZA: ");
			nomePizza = reader.readLine();
			System.out.println("INGREDIENTES DA NOVA PIZZA: ");
			ingredientes = reader.readLine();
			System.out.println("PRECO DA NOVA PIZZA: ");
			preco = reader.readLine();
			
			if ( nomePizza != null && ingredientes != null && preco != null) {
				return new Cardapio(nomePizza, ingredientes, preco);
			}
		} catch (IOException e) {
			System.out.println("OCORREU ERRO DE ENTRADA E SAÍDA, TENTE NOVAMENTE \n");
			return recuperarNovoCardapio();
		}
		
		return null;
	}
	
	public Pedido recuperarDadosNovoPedido(){
		
		
		String telefone, nome_pizza;
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("TELEFONE DO CLIENTE: ");
			telefone = reader.readLine();
			
			
		} catch (IOException e) {
			System.out.println("OCORREU ERRO DE ENTRADA E SAÍDA, TENTE NOVAMENTE \n");
			return recuperarDadosNovoPedido();
		}/*

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("TELEFONE DO CLIENTE: ");
		/*telefone = reader.readLine();

		Class.forName("org.postgresql.Driver").newInstance();

		conexao = DriverManager.getConnection("jdbc:postgresql://localhost/pizzaria", "postgres", "postgres");

		comandoSQL = conexao.createStatement();
		resultado = comandoSQL.executeQuery("SELECT * FROM CLIENTE WHERE TELEFONE = '"+telefone+"'");
		while (resultado.next()) {
			System.out.println("NOME: "+resultado.getString("NOME"));
			System.out.println("ENDERECO: "+resultado.getString("ENDERECO"));
		}
		resultado.close();
		System.out.println("PIZZA: ");
		nome_pizza = reader.readLine();
		comandoSQL = conexao.createStatement();
		resultado = comandoSQL.executeQuery("SELECT * FROM CARDAPIO WHERE NOME_PIZZA= '"+nome_pizza+"'");
		while (resultado.next()) {
			System.out.println("INGREDIENTES: "+resultado.getString("INGREDIENTES"));
			System.out.println("PRECO: "+resultado.getString("PRECO"));
		}
		
		System.out.println("CONFIRMA?[S/N]");
		String opcao = reader.readLine();
		if(opcao.equalsIgnoreCase("S")){
			System.out.println("QUANTIDADE DESEJADA: ");
			int quantidade = Integer.parseInt(reader.readLine());
			comandoSQL.executeUpdate("INSERT INTO PEDIDO VALUES('"+telefone+"', current_timestamp, '"+nome_pizza+"', "+quantidade+")");
			System.out.println("PEDIDO INSERIDO COM SUCESSO!");
			System.in.read();
		}*/
		
		return null;
	}

}
