package com.maria.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.pedido.models.Cliente;
import com.maria.pedido.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
	
	public Cliente findById(long id) {
		return this.clienteRepository.findById(id).get();
	}
	
}
