package com.fatec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int idProduto;
	
	@NotNull
	private String cnpjFornecedor;

	@NotNull
	private String nomeProduto;

	@NotNull
	private float precoProduto;

	@NotNull
	private int qntProduto;

	public Produto() {}
	
	public Produto(
			@NotNull int idProduto, 
			@NotNull String cnpjFornecedor, 
			@NotNull String nomeProduto, 
			@NotNull float precoProduto, 
			@NotNull int qntProduto) {
		this.idProduto = idProduto;
		this.cnpjFornecedor = cnpjFornecedor;
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
		this.qntProduto = qntProduto;
	}

	public int getIdProduto() { return idProduto; }
	public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

	public String getCnpjFornecedor() { return cnpjFornecedor; }
	public void setCnpjFornecedor(String cnpjFornecedor) { this.cnpjFornecedor = cnpjFornecedor; }

	public String getNomeProduto() { return nomeProduto; }
	public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

	public float getPrecoProduto() { return precoProduto; }
	public void setPrecoProduto(float precoProduto) { this.precoProduto = precoProduto; }

	public int getQntProduto() { return qntProduto; }
	public void setQntProduto(int qntProduto) { this.qntProduto = qntProduto; }

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", cnpjFornecedor=" + cnpjFornecedor + ", nomeProduto=" + nomeProduto + ", precoProduto=" + precoProduto + ", qntProduto=" + qntProduto + "]";
	}
}