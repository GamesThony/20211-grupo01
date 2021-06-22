package com.fatec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.model.Cliente;
import com.fatec.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired
	private ClienteService cs;

	@GetMapping(value = {"/clientes", "/cliente/del/", "/cliente/"})
	public ModelAndView consulta() { return consulta(1); }
	
	@GetMapping("/clientes/{pag}")
	public ModelAndView consulta(@PathVariable("pag") int pag) {
		int pagCount = cs.getPageCount();
		if (pag <= 0) return consulta(1);
		if (pag > pagCount && pagCount != 0) return consulta(pagCount);
			
		ModelAndView view = new ModelAndView("clientes/paginaConsulta");
		view.addObject("clientes", cs.getPage(pag));
		view.addObject("pag_atual", pag);
		view.addObject("pag_ant", Math.max(1, pag - 1));
		view.addObject("pag_prox", Math.min(pag + 1, Math.max(1, pagCount)));
		view.addObject("pag_max", Math.max(1, pagCount));
		return view; 
	}
	
	@GetMapping("/cliente/new")
	public ModelAndView cadastro() { return new ModelAndView("clientes/paginaCadastro"); }

	@GetMapping("/cliente/{cpf}")
	public ModelAndView edicao(@PathVariable("cpf") String cpf) {
		Cliente cliente = cs.find(cpf);
		if (cliente == null) return consulta();
		
		ModelAndView view = new ModelAndView("clientes/paginaEdicao");
		view.addObject("cliente", cliente);
		return view; 
	}
	
	@PostMapping("/cliente/new")
	public String cadastro(Cliente cliente) { return cs.save(cliente); }
	
	@PostMapping("/cliente/{cpf}")
	public String edicao(@PathVariable("cpf") String cpf, Cliente cliente) { return cs.update(cpf, cliente); }
	
	@GetMapping("/cliente/del/{cpf}")
	public String remover(@PathVariable("cpf") String cpf) { return cs.delete(cpf); }
}
