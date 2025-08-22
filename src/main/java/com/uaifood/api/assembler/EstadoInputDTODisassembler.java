package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.EstadoInputDTO;
import com.uaifood.domain.model.Estado;

@Component
public class EstadoInputDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Estado toDomainObject(EstadoInputDTO estadoInputDTO) {
		return modelMapper.map(estadoInputDTO, Estado.class);
	}

	public void copyToDomanObject(EstadoInputDTO estadoInputDTO, Estado estado) {
		modelMapper.map(estadoInputDTO, estado);
	}
}
