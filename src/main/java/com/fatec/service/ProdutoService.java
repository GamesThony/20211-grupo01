package com.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.model.Fornecedor;
import com.fatec.model.Produto;
import com.fatec.repository.FornecedorRepository;
import com.fatec.repository.ProdutoRepository;

@Service
public class ProdutoService implements BaseService<Produto, Integer> {
	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private FornecedorRepository fr;
	
	@Override
	public int getPageCount() { return pr.getPageCount(); }

	@Override
	public List<Produto> getPage(int page) { return pr.getPage((page - 1) * 10); }

	@Override
	public String save(Produto produto) {
		pr.save(produto);
		return "redirect:/produtos/1?saved";
	}

	@Override
	public String delete(Integer id) {
		if (pr.existsById(id)) pr.deleteById(id);
		return "redirect:/produtos/1?deleted";
	}

	@Override
	public Produto find(Integer id) { return pr.findById(id).orElse(null); }

	public Iterable<Fornecedor> findAllProviders() { return fr.findAll(); }
	
	@Override
	public String update(Integer id, Produto produto) {
		delete(produto.getIdProduto());
		produto.setIdProduto(id);	
		save(produto);
		return "redirect:/produtos/1?updated";
	}

	public int getNewID() {
		Produto ultimo = pr.getLast();
		return ultimo == null ? 1 : ultimo.getIdProduto() + 1;
	}
}
