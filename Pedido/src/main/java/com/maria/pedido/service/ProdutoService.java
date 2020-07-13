package com.maria.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.pedido.models.Produto;
import com.maria.pedido.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> findAll(){
		return this.produtoRepository .findAll();
	}
	
	public Produto findById(long id) {
		return this.produtoRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Produto produto) {
		this.produtoRepository.save(produto);
	}
}
