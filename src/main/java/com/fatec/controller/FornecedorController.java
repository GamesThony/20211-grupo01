package com.fatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.model.Fornecedor;
import com.fatec.service.FornecedorService;

@Controller
public class FornecedorController {
	@Autowired
	private FornecedorService fs;
	
	@GetMapping(value = {"/marcas", "/marca/del/", "/marca/"})
	public ModelAndView consulta() { return consulta(1); }
	
	@GetMapping("/marcas/{pag}")
	public ModelAndView consulta(@PathVariable("pag") int pag) {
		int pagCount = fs.getPageCount();
		if (pag <= 0) return consulta(1);
		if (pag > pagCount && pagCount != 0) return consulta(pagCount);
			
		ModelAndView view = new ModelAndView("fornecedores/paginaConsulta");
		view.addObject("fornecedores", fs.getPage(pag));
		view.addObject("pag_atual", pag);
		view.addObject("pag_ant", Math.max(1, pag - 1));
		view.addObject("pag_prox", Math.min(pag + 1, Math.max(1, pagCount)));
		view.addObject("pag_max", Math.max(1, pagCount));
		return view; 
	}
	
	@GetMapping("/marca/new")
	public ModelAndView cadastro() { return new ModelAndView("fornecedores/paginaCadastro"); }

	@GetMapping("/marca/{cnpj}")
	public ModelAndView edicao(@PathVariable("cnpj") String cnpj) {
		Fornecedor fornecedor = fs.find(cnpj);
		if (fornecedor == null) return consulta();
		
		ModelAndView view = new ModelAndView("fornecedores/paginaEdicao");
		view.addObject("fornecedor", fornecedor);
		return view; 
	}
	
	@PostMapping("/marca/new")
	public String cadastro(Fornecedor fornecedor) { return fs.save(fornecedor); }
	
	@PostMapping("/marca/{cnpj}")
	public String edicao(@PathVariable("cnpj") String cnpj, Fornecedor fornecedor) { return fs.update(cnpj, fornecedor); }
	
	@GetMapping("/marca/del/{cnpj}")
	public String remover(@PathVariable("cnpj") String cnpj) { return fs.delete(cnpj); }
}
