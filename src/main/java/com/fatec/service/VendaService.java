package com.fatec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.model.Cliente;
import com.fatec.model.Produto;
import com.fatec.model.Venda;
import com.fatec.repository.ClienteRepository;
import com.fatec.repository.ProdutoRepository;
import com.fatec.repository.VendaRepository;

@Service
public class VendaService implements BaseService<Venda, Integer> {
	@Autowired
	private VendaRepository vr;

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private ClienteRepository cr;

	@Override
	public int getPageCount() { return vr.getPageCount(); }

	@Override
	public List<Venda> getPage(int page) { return vr.getPage((page - 1) * 10); }

	@Override
	public String save(Venda venda) {
		Produto produto = pr.findById(venda.getIdProduto()).get();
		int qntProduto = produto.getQntProduto(), qnt = venda.getQntCompra();
		if (qntProduto - qnt < 0) return "redirect:/venda/new?sell_error";
		
		produto.setQntProduto(qntProduto - qnt);
		pr.save(produto);
		vr.save(venda);
		return "redirect:/vendas/1?saved";
	}

	@Override
	public String delete(Integer id) {
		if (vr.existsById(id)) {
			Venda venda = vr.findById(id).get();
			Optional<Produto> produto_ = pr.findById(venda.getIdProduto());
			if (produto_.isPresent()) {
				Produto produto = produto_.get();
				int qnt = produto.getQntProduto();
				int qntVenda = venda.getQntCompra();
				produto.setQntProduto(qnt + qntVenda);
				pr.save(produto);
			}
			vr.deleteById(id);
		}
		return "redirect:/vendas/1?deleted";
	}

	@Override
	public Venda find(Integer id) { return vr.findById(id).orElse(null); }

	public Iterable<Cliente> findAllClients() { return cr.findAll(); }

	public Iterable<Produto> findAllProducts() { return pr.findAll(); }

	@Override
	public String update(Integer id, Venda venda) {
		Venda antiga = vr.findById(id).get();
		delete(id);
		String result = save(venda);
		if (result == "redirect:/venda/new?sell_error") {
			save(antiga);
			return result;
		}
		return "redirect:/vendas/1?updated";
	}

	public int getNewID() {
		Venda ultimo = vr.getLast();
		return ultimo == null ? 1 : ultimo.getIdVenda() + 1;
	}
}
