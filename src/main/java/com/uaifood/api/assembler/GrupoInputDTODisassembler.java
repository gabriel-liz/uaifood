package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.GrupoInputDTO;
import com.uaifood.domain.model.Grupo;

@Component
public class GrupoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Grupo toDomainObject(GrupoInputDTO grupoInputDTO) {
		return modelMapper.map(grupoInputDTO, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoInputDTO grupoINputDTO, Grupo grupo) {
		modelMapper.map(grupoINputDTO, grupo);
	}

}
