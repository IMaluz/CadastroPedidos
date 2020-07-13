package com.maria.pedido.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maria.pedido.models.ItemPedido;
import com.maria.pedido.models.Pedido;
import com.maria.pedido.service.ItemPedidoService;
import com.maria.pedido.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ItemPedidoService itemPedidoService;

	@GetMapping("/")
	public List<Pedido> findAll() {
		return this.pedidoService.findAll();
	}

	@GetMapping("/{id}")
	public Pedido findById(@PathVariable("id") long id) {
		return this.pedidoService.findById(id);
	}

	@PostMapping("/")
	public void save(@RequestBody Pedido pedido) {
		pedido.setDataPedido(LocalDateTime.now());
		this.pedidoService.saveOrUpdate(pedido);
	}

	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody Pedido pedido) {
		Long id = pedido.getId();
		if(id != null && id != 0) {
			this.pedidoService.saveOrUpdate(pedido);
			return new ResponseEntity<String>("Pedido atualizado com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Para atualizar um pedido é necessário um id", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/cancelar/{id}")
	public void cancelar(@PathVariable("id") long id) {
		this.pedidoService.cancelById(id);
	}

	@GetMapping("/cancelados")
	public List<Pedido> cancelados(){
		return this.pedidoService.findAllCancelados();
	}

	@PostMapping("/{id}/add-item")
	public ResponseEntity<String> addItemPedido(@PathVariable("id") long id, @RequestBody ItemPedido itemPedido){
		try {
			Pedido pedido = this.pedidoService.findById(id);
			pedido.addItemPedido(itemPedido);
			this.pedidoService.saveOrUpdate(pedido);
			return new ResponseEntity<String>("Item Incluido Com Sucesso!!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}/remover-item/{idItem}")
	public ResponseEntity<String> removeItemPedido(@PathVariable("id") long idPedido, @PathVariable("idItem") long idItem){
		try {
			Pedido pedido = pedidoService.findById(idPedido);
			ItemPedido itemPedido = itemPedidoService.findById(idItem);
			pedido.removeItemPedido(itemPedido);
			this.pedidoService.saveOrUpdate(pedido);
			return new ResponseEntity<String>("Item removido com sucesso!!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/datebetween/{initDate}/{endDate}")
	public List<Pedido> findAllByDateBetween(@PathVariable("initDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime initDate,
											 @PathVariable("endDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate){
		return this.pedidoService.findAllByDateBetween(initDate, endDate);
	}
	
	
}
