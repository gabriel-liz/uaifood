package com.uaifood.domain.repository;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uaifood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>{
	
	//@Query("from Pedido where codigo = :codigo"), nao precisa pois o proprio spring jpa ja faz pra gente, acho que se o atributo tivesse outro nome e o metodo fosse bycodigo, precisaria ter essa linha
	Optional<Pedido> findByCodigo(String codigo);
	
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
}
