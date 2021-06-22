package com.fatec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int idVenda;

	@NotNull
	private int idProduto;
	
	@NotNull
	private String cpfCliente;
	
	@NotNull
	private int qntCompra;
	
	public Venda() { }

	public Venda(
			@NotNull int idVenda, 
			@NotNull int idProduto, 
			@NotNull String cpfCliente, 
			@NotNull int qntCompra) {
		this.idVenda = idVenda;
		this.idProduto = idProduto;
		this.cpfCliente = cpfCliente;
		this.qntCompra = qntCompra;
	}

	public int getIdVenda() { return idVenda; }
	public void setIdVenda(int idVenda) { this.idVenda = idVenda; }

	public int getIdProduto() { return idProduto; }
	public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

	public String getCpfCliente() { return cpfCliente; }
	public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }

	public int getQntCompra() { return qntCompra; }
	public void setQntCompra(int qntCompra) { this.qntCompra = qntCompra; }

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", idProduto=" + idProduto + ", cpfCliente=" + cpfCliente + ", qntCompra=" + qntCompra + "]";
	}
}