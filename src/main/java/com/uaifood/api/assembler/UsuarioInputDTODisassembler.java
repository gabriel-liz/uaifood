package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.UsuarioInputDTO;
import com.uaifood.domain.model.Usuario;

@Component
public class UsuarioInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioInputDTO usuarioInputDTO) {
		return modelMapper.map(usuarioInputDTO, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInputDTO usuarioInputDTO, Usuario usuario) {
		modelMapper.map(usuarioInputDTO, usuario);
	}

}
