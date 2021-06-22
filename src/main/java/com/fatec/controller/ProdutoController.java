package com.fatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.model.Produto;
import com.fatec.service.ProdutoService;

@Controller
public class ProdutoController {
	@Autowired
	private ProdutoService ps;
	
	@GetMapping(value = {"/produtos", "/produto/del/", "/produto/"})
	public ModelAndView consulta() { return consulta(1); }
	
	@GetMapping("/produtos/{pag}")
	public ModelAndView consulta(@PathVariable("pag") int pag) {
		int pagCount = ps.getPageCount();
		if (pag <= 0) return consulta(1);
		if (pag > pagCount && pagCount != 0) return consulta(pagCount);
			
		ModelAndView view = new ModelAndView("produtos/paginaConsulta");
		view.addObject("produtos", ps.getPage(pag));
		view.addObject("pag_atual", pag);
		view.addObject("pag_ant", Math.max(1, pag - 1));
		view.addObject("pag_prox", Math.min(pag + 1, Math.max(1, pagCount)));
		view.addObject("pag_max", Math.max(1, pagCount));
		return view; 
	}
	
	@GetMapping("/produto/new")
	public ModelAndView cadastro() { 
		ModelAndView view = new ModelAndView("produtos/paginaCadastro"); 
		view.addObject("id", ps.getNewID());
		view.addObject("fornecedores", ps.findAllProviders());
		return view;
	}

	@GetMapping("/produto/{id}")
	public ModelAndView edicao(@PathVariable("id") Integer id) {
		Produto produto = ps.find(id);
		if (produto == null) return consulta();
		
		ModelAndView view = new ModelAndView("produtos/paginaEdicao");
		view.addObject("produto", produto);
		view.addObject("fornecedores", ps.findAllProviders());
		return view; 
	}
	
	@PostMapping("/produto/new")
	public String cadastro(Produto produto) { return ps.save(produto); }
	
	@PostMapping("/produto/{id}")
	public String edicao(@PathVariable("id") Integer id, Produto produto) { return ps.update(id, produto); }
	
	@GetMapping("/produto/del/{id}")
	public String remover(@PathVariable("id") Integer id) { return ps.delete(id); }
}
