package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.RestauranteInputDTO;
import com.uaifood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
//Desmonta o RestauranteDTO e monta um Restaurante
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInputDTO restauranteInputDTO) {
		return modelMapper.map(restauranteInputDTO, Restaurante.class);

	}

}
