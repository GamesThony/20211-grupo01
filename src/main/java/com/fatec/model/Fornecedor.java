package com.fatec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class Fornecedor {
	@Id
	@NotNull
	private String cnpjFornecedor;
	
	@NotNull
	private String nomeFornecedor;
	
	public Fornecedor() { }

	public Fornecedor(@NotNull String cnpjFornecedor, @NotNull String nomeFornecedor) {
		this.cnpjFornecedor = cnpjFornecedor;
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getCnpjFornecedor() { return cnpjFornecedor; }
	public void setCnpjFornecedor(String cnpjFornecedor) { this.cnpjFornecedor = cnpjFornecedor; }

	public String getNomeFornecedor() { return nomeFornecedor; }
	public void setNomeFornecedor(String nomeFornecedor) { this.nomeFornecedor = nomeFornecedor; }

	@Override
	public String toString() {
		return "Fornecedor [cnpjFornecedor=" + cnpjFornecedor + ", nomeFornecedor=" + nomeFornecedor + "]";
	}
}