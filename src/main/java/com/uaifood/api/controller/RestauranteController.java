package com.uaifood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.assembler.RestauranteDTOAssembler;
import com.uaifood.api.assembler.RestauranteInputDTODisassembler;
import com.uaifood.api.model.RestauranteDTO;
import com.uaifood.api.model.input.RestauranteInputDTO;
import com.uaifood.domain.exception.CozinhaNaoEncontradaException;
import com.uaifood.domain.exception.NegocioException;
import com.uaifood.domain.model.Restaurante;
import com.uaifood.domain.repository.RestauranteRepository;
import com.uaifood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;	
	
	@Autowired
	private RestauranteDTOAssembler restauranteDTOAssembler;
	
	@Autowired
	private RestauranteInputDTODisassembler restauranteInputDTODisassembler;   

	@GetMapping
	public List<RestauranteDTO> listar() {
		return restauranteDTOAssembler.toCollectionDTO(restauranteRepository.findAll());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteDTO buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		//RestauranteDTO restauranteDTO = toDTO(restaurante);		
		
		return restauranteDTOAssembler.toDTO(restaurante);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
		try {
			
			Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInputDTO);
			
			return restauranteDTOAssembler.toDTO(cadastroRestaurante.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
		try {
			Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInputDTO);
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
					"produtos");
			return restauranteDTOAssembler.toDTO(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{restauranteId}")
	public void remover(@PathVariable Long restauranteId) {
		cadastroRestaurante.excluir(restauranteId);
	}	
	

}