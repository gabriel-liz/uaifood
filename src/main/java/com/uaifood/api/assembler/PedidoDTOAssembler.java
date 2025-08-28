package com.uaifood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.PedidoDTO;
import com.uaifood.domain.model.Pedido;

@Component
public class PedidoDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoDTO toDTO(Pedido pedido) {
		return modelMapper.map(pedido, PedidoDTO.class);
	}
	
	public List<PedidoDTO> toCollectionDTO(List<Pedido> pedidos){
		return pedidos.stream()
				.map(pedido -> toDTO(pedido))
				.collect(Collectors.toList());
	}
	
}
