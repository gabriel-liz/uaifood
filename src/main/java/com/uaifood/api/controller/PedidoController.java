package com.uaifood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.uaifood.api.assembler.PedidoDTOAssembler;
import com.uaifood.api.assembler.PedidoInputDTODisassembler;
import com.uaifood.api.assembler.PedidoResumoDTOAssembler;
import com.uaifood.api.exceptionhandler.ApiExceptionHandler;
import com.uaifood.api.model.PedidoDTO;
import com.uaifood.api.model.PedidoResumoDTO;
import com.uaifood.api.model.input.PedidoInputDTO;
import com.uaifood.domain.exception.EntidadeNaoEncontradaException;
import com.uaifood.domain.exception.NegocioException;
import com.uaifood.domain.model.Pedido;
import com.uaifood.domain.model.Usuario;
import com.uaifood.domain.repository.PedidoRepository;
import com.uaifood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private final ApiExceptionHandler apiExceptionHandler;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
	
	@Autowired
	private PedidoInputDTODisassembler pedidoInputDTODisassembler;

    PedidoController(ApiExceptionHandler apiExceptionHandler) {
        this.apiExceptionHandler = apiExceptionHandler;
    }
    
    @GetMapping
	public MappingJacksonValue listar(@RequestParam(required = false) String campos){
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		List<PedidoResumoDTO> pedidosDTO = pedidoResumoDTOAssembler.toCollectionDTO(todosPedidos);

		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosDTO);
		
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
		
		if(StringUtils.isNotBlank(campos)) {
			filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
		}
		
		pedidosWrapper.setFilters(filterProvider);
		
		return pedidosWrapper;
	}
    
	
//	@GetMapping
//	public List<PedidoResumoDTO> listar(){
//		List<Pedido> todosPedidos = pedidoRepository.findAll();
//		
//		return pedidoResumoDTOAssembler.toCollectionDTO(todosPedidos);
//	}
	
	@GetMapping("/{codigoPedido}")
	public PedidoDTO buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		return pedidoDTOAssembler.toDTO(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO adicionar(@Valid @RequestBody PedidoInputDTO pedidoInputDTO) {
		try {
			Pedido novoPedido = pedidoInputDTODisassembler.toDomainObject(pedidoInputDTO);
			
			//TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);
			
			novoPedido = emissaoPedido.emitir(novoPedido);
			return pedidoDTOAssembler.toDTO(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	}
}
