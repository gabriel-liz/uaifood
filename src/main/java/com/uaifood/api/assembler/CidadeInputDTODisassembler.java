package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.CidadeInputDTO;
import com.uaifood.domain.model.Cidade;
import com.uaifood.domain.model.Estado;

@Component
public class CidadeInputDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeInputDTO cidadeInputDTO) {
		return modelMapper.map(cidadeInputDTO, Cidade.class);

	}

	public void copyToDomainObject(CidadeInputDTO cidadeInputDTO, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// com.algaworks.algafood.domain.model.Estado was altered from 1 to 2
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeInputDTO, cidade);
	}
}
