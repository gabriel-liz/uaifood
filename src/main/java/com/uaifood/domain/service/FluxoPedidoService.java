package com.uaifood.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uaifood.domain.exception.NegocioException;
import com.uaifood.domain.model.Pedido;
import com.uaifood.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {
	
	
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
		
		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(
					String.format("Status do pedido %d não pode ser alterado de %s para %s", 
					pedido.getId(), pedido.getStatus().getDescricao(),
					StatusPedido.CONFIRMADO.getDescricao()));				
		}
		
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
		//como esta dentro do Transactional ja será feito o commit
	}
	

}
