package com.uaifood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.PermissaoDTO;
import com.uaifood.domain.model.Permissao;

@Component
public class PermissaoDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PermissaoDTO toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDTO.class);
	}
	
	public List<PermissaoDTO> toCollectionDTO(Collection<Permissao> permissoes){
		return permissoes.stream()
				.map(permissao -> toModel(permissao))
				.collect(Collectors.toList());
	}	
	
}
