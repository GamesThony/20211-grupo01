package com.fatec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class Cliente {
	@Id
	@NotNull
	private String cpfCliente;
	
	@NotNull
	private String nomeCliente;
	
	@NotNull
	private String cepCliente;
	
	public Cliente() { }

	public Cliente(
			@NotNull String cpfCliente, 
			@NotNull String nomeCliente, 
			@NotNull String cepCliente) {
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
		this.cepCliente = cepCliente;
	}

	public String getCpfCliente() { return cpfCliente; }
	public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }

	public String getNomeCliente() { return nomeCliente; }
	public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

	public String getCepCliente() { return cepCliente; }
	public void setCepCliente(String cepCliente) { this.cepCliente = cepCliente; }

	@Override
	public String toString() { 
		return "Cliente [cpfCliente=" + cpfCliente + ", nomeCliente=" + nomeCliente + ", cepCliente=" + cepCliente + "]"; 
	}
}