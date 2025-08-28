package com.uaifood.domain.repository;

import org.springframework.stereotype.Repository;

import com.uaifood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>{

}
