package com.uaifood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.assembler.PedidoDTOAssembler;
import com.uaifood.api.model.PedidoDTO;
import com.uaifood.domain.model.Pedido;
import com.uaifood.domain.repository.PedidoRepository;
import com.uaifood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@GetMapping
	public List<PedidoDTO> listar(){
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		
		return pedidoDTOAssembler.toCollectionDTO(todosPedidos);
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoDTO buscar(@PathVariable Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
		return pedidoDTOAssembler.toDTO(pedido);
	}
}
