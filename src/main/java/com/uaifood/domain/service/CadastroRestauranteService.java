package com.uaifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uaifood.domain.exception.EntidadeEmUsoException;
import com.uaifood.domain.exception.EntidadeNaoEncontradaException;
import com.uaifood.domain.exception.RestauranteNaoEncontradoException;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.model.Restaurante;
import com.uaifood.domain.repository.CozinhaRepository;
import com.uaifood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_RESTAURANTE_EM_USO = "Restauranet de código %d não pode ser removida, pois está em uso";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);

	}
	
	@Transactional
	public void ativar(Long restauranteId) {		
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		//Ao fazer o buscarOuFalhar, o findById. Essa instancia do restaurante fica em um estado gerenciado pelo contexto de persistencia do jpa
		//Qualquer modificacao feita sera sincronizada com o banco de dados. Sem precisar ser feito o .save
		//restauranteRestaurante.setAtivo(true);
		restauranteAtual.ativar();
		
	}
	
	@Transactional
	public void inativar(Long restauranteId) {		
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		//Ao fazer o buscarOuFalhar, o findById. Essa instancia do restaurante fica em um estado gerenciado pelo contexto de persistencia do jpa
		//Qualquer modificacao feita sera sincronizada com o banco de dados. Sem precisar ser feito o .save
		//restauranteAtual.setAtivo(false);
		restauranteAtual.inativar();
		 
	}
	
	
	@Transactional
	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
			restauranteRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(restauranteId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));

		}
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

}
