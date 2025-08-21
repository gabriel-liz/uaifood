package com.uaifood.api.assembler;

import org.springframework.stereotype.Component;

import com.uaifood.api.model.input.RestauranteInputDTO;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
//Desmonta o RestauranteDTO e monta um Restaurante
	public Restaurante toDomainObject(RestauranteInputDTO restauranteInputDTO) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInputDTO.getNome());
		restaurante.setTaxaFrete(restauranteInputDTO.getTaxaFrete());

		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInputDTO.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return restaurante;

	}

}
