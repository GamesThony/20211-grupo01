package com.fatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.model.Venda;
import com.fatec.service.VendaService;

@Controller
public class VendaController {
	@Autowired
	private VendaService vs;
	
	@GetMapping(value = {"/vendas", "/venda/del/", "/venda/"})
	public ModelAndView consulta() { return consulta(1); }
	
	@GetMapping("/vendas/{pag}")
	public ModelAndView consulta(@PathVariable("pag") int pag) {
		int pagCount = vs.getPageCount();
		if (pag <= 0) return consulta(1);
		if (pag > pagCount && pagCount != 0) return consulta(pagCount);
			
		ModelAndView view = new ModelAndView("vendas/paginaConsulta");
		view.addObject("vendas", vs.getPage(pag));
		view.addObject("pag_atual", pag);
		view.addObject("pag_ant", Math.max(1, pag - 1));
		view.addObject("pag_prox", Math.min(pag + 1, Math.max(1, pagCount)));
		view.addObject("pag_max", Math.max(1, pagCount));
		return view; 
	}
	
	@GetMapping("/venda/new")
	public ModelAndView cadastro() { 
		ModelAndView view = new ModelAndView("vendas/paginaCadastro"); 
		view.addObject("id", vs.getNewID());
		view.addObject("clientes", vs.findAllClients());
		view.addObject("produtos", vs.findAllProducts());
		return view;
	}

	@GetMapping("/venda/{id}")
	public ModelAndView edicao(@PathVariable("id") Integer id) {
		Venda venda = vs.find(id);
		if (venda == null) return consulta();
		
		ModelAndView view = new ModelAndView("vendas/paginaEdicao");
		view.addObject("venda", venda);
		view.addObject("clientes", vs.findAllClients());
		view.addObject("produtos", vs.findAllProducts());
		return view; 
	}
	
	@PostMapping("/venda/new")
	public String cadastro(Venda venda) { return vs.save(venda); }
	
	@PostMapping("/venda/{id}")
	public String edicao(@PathVariable("id") Integer id, Venda venda) { return vs.update(id, venda); }
	
	@GetMapping("/venda/del/{id}")
	public String remover(@PathVariable("id") Integer id) { return vs.delete(id); }
}
