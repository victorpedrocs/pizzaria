package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pizzaria.ConnectionFactory;
import model.Cardapio;
import model.CardapioDAO;
import model.Cliente;
import model.ClienteDAO;
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

			// Recuperar Cliente do banco			
			System.out.println("TELEFONE DO CLIENTE: ");
			telefone = reader.readLine();
			ClienteDAO clienteDAO = new ClienteDAO(ConnectionFactory.getConnection());
			Cliente cliente = clienteDAO.retrieve(new Cliente(telefone));
			System.out.println(cliente.toString());

			// Recuperar Pizza do banco
			System.out.println("PIZZA: ");
			nome_pizza = reader.readLine();
			CardapioDAO cardapioDAO = new CardapioDAO(ConnectionFactory.getConnection());
			Cardapio cardapio = cardapioDAO.retrieve(new Cardapio(nome_pizza));
			System.out.println(cardapio.toString());
			
			
			System.out.println("CONFIRMA?[S/N]");
			String opcao = reader.readLine();
			
			if(opcao.equalsIgnoreCase("S")){
				System.out.println("QUANTIDADE DESEJADA: ");
				int quantidade = Integer.parseInt(reader.readLine());
				Pedido pedido = new Pedido(cliente, cardapio, quantidade);
				return pedido;
			}
			
			
		} catch (IOException e) {
			System.out.println("OCORREU ERRO DE ENTRADA E SAÍDA, TENTE NOVAMENTE \n");
			return recuperarDadosNovoPedido();
		}
		
		return null;
	}

}
