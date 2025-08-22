package com.uaifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.RestauranteInputDTO;
import com.uaifood.domain.model.Cidade;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
//Desmonta o RestauranteDTO e monta um Restaurante
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInputDTO restauranteInputDTO) {
		return modelMapper.map(restauranteInputDTO, Restaurante.class);

	}
	
	public void copyToDomainObject(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante) {
		//Esse set serve para que o jpa entenda que nao estamos tentando alterar o id da cozinha, e sim a cozinha do restaurante
		//Evita a exception:  org.hibernate.HibernateException: identifier of an instance of com.uaifood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		if(restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		modelMapper.map(restauranteInputDTO, restaurante);
	}

}
