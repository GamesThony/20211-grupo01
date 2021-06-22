package com.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.model.Cliente;
import com.fatec.repository.ClienteRepository;

@Service
public class ClienteService implements BaseService<Cliente, String> {
	@Autowired
	private ClienteRepository cr;
	
	@Override
	public int getPageCount() { return cr.getPageCount(); }

	@Override
	public List<Cliente> getPage(int page) { return cr.getPage((page - 1) * 10); }

	@Override
	public String save(Cliente cliente) {
		if (cr.existsById(cliente.getCpfCliente())) return "redirect:/cliente/new?client_error";
		cr.save(cliente);
		return "redirect:/clientes/1?saved";
	}

	@Override
	public String delete(String cpf) {
		if (cr.existsById(cpf)) cr.deleteById(cpf);
		return "redirect:/clientes/1?deleted";
	}

	@Override
	public Cliente find(String cpf) { return cr.findById(cpf).orElse(null); }
	
	@Override
	public String update(String cpf, Cliente cliente) {	
		delete(cpf);
		delete(cliente.getCpfCliente());
		save(cliente);
		return "redirect:/clientes/1?updated";
	}
}
