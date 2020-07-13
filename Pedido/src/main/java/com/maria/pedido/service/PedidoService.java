package com.maria.pedido.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.pedido.models.ItemPedido;
import com.maria.pedido.models.Pedido;
import com.maria.pedido.models.Produto;
import com.maria.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoService produtoService;
	
	public List<Pedido> findAll(){
		return this.pedidoRepository.findAll();
	}
	
	public Pedido findById(long id) {
		return this.pedidoRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Pedido pedido) {
		this.updateProdutoEstoque(pedido);
		pedido = this.setSubTotal(pedido);
		pedido = this.setTotal(pedido);
		this.pedidoRepository.save(pedido);
	}
	
	public void cancelById(long id) {
		Pedido pedido = this.findById(id);
		pedido.setCancelado(true);
		this.pedidoRepository.save(pedido);
	}
	
	public void updateProdutoEstoque(Pedido pedido) {
		List<ItemPedido> itens = pedido.getItensPedido();
		itens.forEach(item -> {
			Produto produto = produtoService.findById(item.getProduto().getId());
			produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
			this.produtoService.saveOrUpdate(produto);
		});
	}
	
	public Pedido setSubTotal(Pedido pedido) {
		pedido.getItensPedido().forEach(item -> {
			double valorProduto = produtoService.findById(item.getProduto().getId()).getValor();
			int quantidade = item.getQuantidade();
			item.setSubTotal(valorProduto * quantidade);
		});
		return pedido;
	}
	
	public Pedido setTotal(Pedido pedido) {
		pedido.setTotal(0);
		pedido.getItensPedido().forEach(item -> {
			double total = pedido.getTotal() + item.getSubTotal();
			pedido.setTotal(total);
		});
		return pedido;
	}
	
	public List<Pedido> findAllCancelados() {
		return this.pedidoRepository.findAllByCancelado(true);
	}
	
	public List<Pedido> findAllByDateBetween (LocalDateTime initDate, LocalDateTime endDate){
		return this.pedidoRepository.findAllByDataPedidoBetween(initDate, endDate);
	}
}
