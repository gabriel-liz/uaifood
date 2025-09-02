package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.PedidoInputDTO;
import com.uaifood.domain.model.Pedido;

@Component
public class PedidoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Pedido toDomainObject(PedidoInputDTO pedidoInputDTO) {
		return modelMapper.map(pedidoInputDTO, Pedido.class);
	}
	
	public void copyToDomainObject(PedidoInputDTO pedidoInputDTO, Pedido pedido) {
		modelMapper.map(pedidoInputDTO, pedido);
	}
}
