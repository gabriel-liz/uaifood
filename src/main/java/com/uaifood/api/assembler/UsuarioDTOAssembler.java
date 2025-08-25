package com.uaifood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.UsuarioDTO;
import com.uaifood.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioDTO toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
	
	public List<UsuarioDTO> toCollectionModel(List<Usuario> usuarios){
		return usuarios.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}

}
