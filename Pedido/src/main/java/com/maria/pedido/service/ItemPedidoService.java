package com.maria.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.pedido.models.ItemPedido;
import com.maria.pedido.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public ItemPedido findById(long id) {
		return this.itemPedidoRepository.findById(id).get();
	}
	
}
