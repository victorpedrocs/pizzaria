package model;

import java.util.ArrayList;
import java.util.Collection;

public class Cliente {

	private String telefone, nome, endereco;
	
	public Cliente(String telefone, String nome, String endereco){
		this.telefone = telefone;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public Cliente(String telefone){
		this.telefone = telefone;
		this.nome = null;
		this.endereco = null;
	}
	
	public String getValuesString(){
		return new StringBuilder().append("'").append(this.telefone).append("','")
				.append(this.nome).append("','")
				.append(this.endereco).append("'")
				.toString();
	}
	
	public String getPrimaryKey(){
		return this.telefone;
	}
	
	public String getEndereco(){
		return this.endereco;
	}
	
	public Collection<String> getValuesCollection(){
		Collection<String> fields = new ArrayList<String>();
		fields.add(this.telefone);
		fields.add(this.nome);
		fields.add(this.endereco);
		
		return fields;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: ").append(this.nome).append("\n");
		sb.append("Endereco: ").append(this.endereco);
		return sb.toString();
	}
	
}
