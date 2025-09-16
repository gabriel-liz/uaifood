package com.uaifood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.assembler.RestauranteDTOAssembler;
import com.uaifood.api.assembler.RestauranteInputDTODisassembler;
import com.uaifood.api.model.RestauranteDTO;
import com.uaifood.api.model.input.RestauranteInputDTO;
import com.uaifood.api.model.view.RestauranteView;
import com.uaifood.domain.exception.CidadeNaoEncontradaException;
import com.uaifood.domain.exception.CozinhaNaoEncontradaException;
import com.uaifood.domain.exception.NegocioException;
import com.uaifood.domain.exception.RestauranteNaoEncontradoException;
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
	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		List<RestauranteDTO> restaurantesDTO = restauranteDTOAssembler.toCollectionDTO(restaurantes);
		
		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesDTO);
		
		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
		
		if("apenas-nome".equals(projecao)) {
			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
		} else if ("completo".equals(projecao)){
			restaurantesWrapper.setSerializationView(null);
		}
		
		
		return restaurantesWrapper;
	}

//	@GetMapping
//	public List<RestauranteDTO> listar() {
//		return restauranteDTOAssembler.toCollectionDTO(restauranteRepository.findAll());
//	}
//	
//	@JsonView(RestauranteView.Resumo.class)
//	@GetMapping(params = "projecao=resumo")
//	public List<RestauranteDTO> listarResumido() {
//		return listar();
//	}
//	
//	@JsonView(RestauranteView.ApenasNome.class)
//	@GetMapping(params = "projecao=apenas-nome")
//	public List<RestauranteDTO> listarApenasNome() {
//		return listar();
//	}

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
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
		try {
	//		Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInputDTO);
			
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);			
			restauranteInputDTODisassembler.copyToDomainObject(restauranteInputDTO, restauranteAtual);

			//			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
	//				"produtos");
			return restauranteDTOAssembler.toDTO(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{restauranteId}")
	public void remover(@PathVariable Long restauranteId) {
		cadastroRestaurante.excluir(restauranteId);
	}	
	
	@PutMapping("{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);		
	}
	
	@DeleteMapping("{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);		
	}
	
	
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);		
		}catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}	
		
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inaativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.inativar(restauranteIds);
		}catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}				
	}
	
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);
	}	
	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);
	}
	
	

}