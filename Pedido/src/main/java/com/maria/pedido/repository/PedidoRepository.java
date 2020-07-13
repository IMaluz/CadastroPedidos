package com.maria.pedido.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maria.pedido.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	public List<Pedido> findAllByCancelado(boolean cancelado);
	
	public List<Pedido> findAllByDataPedidoBetween(LocalDateTime initDate, LocalDateTime endDate);
}
