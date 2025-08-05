package com.uaifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaifood.domain.model.Restaurante;
import com.uaifood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		return restauranteRepository.salvar(restaurante);
			
	}

}
