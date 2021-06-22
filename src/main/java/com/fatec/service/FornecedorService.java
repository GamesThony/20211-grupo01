package com.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.model.Fornecedor;
import com.fatec.repository.FornecedorRepository;

@Service
public class FornecedorService implements BaseService<Fornecedor, String> {
	@Autowired
	private FornecedorRepository fr;

	@Override
	public int getPageCount() { return fr.getPageCount(); }

	@Override
	public List<Fornecedor> getPage(int page) { return fr.getPage((page - 1) * 10); }

	@Override
	public String save(Fornecedor fornecedor) {
		if (fr.existsById(fornecedor.getCnpjFornecedor())) return "redirect:/marca/new?provider_error";
		fr.save(fornecedor);
		return "redirect:/marcas/1?saved";
	}

	@Override
	public String delete(String cnpj) {
		if (fr.existsById(cnpj)) fr.deleteById(cnpj);
		return "redirect:/marcas/1?deleted";
	}

	@Override
	public Fornecedor find(String cnpj) { return fr.findById(cnpj).orElse(null); }

	@Override
	public String update(String cnpj, Fornecedor fornecedor) {
		delete(cnpj);
		delete(fornecedor.getCnpjFornecedor());
		save(fornecedor);
		return "redirect:/marcas/1?updated";
	}
}
