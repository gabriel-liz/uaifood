package com.uaifood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.assembler.ProdutoDTOAssembler;
import com.uaifood.api.assembler.ProdutoInputDTODisassembler;
import com.uaifood.api.model.ProdutoDTO;
import com.uaifood.api.model.input.ProdutoInputDTO;
import com.uaifood.domain.model.Produto;
import com.uaifood.domain.model.Restaurante;
import com.uaifood.domain.repository.ProdutoRepository;
import com.uaifood.domain.service.CadastroProdutoService;
import com.uaifood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private ProdutoDTOAssembler produtoDTOAssembler;
	
	@Autowired
	private ProdutoInputDTODisassembler produtoInputDTODisassembler;
	
	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		List<Produto> todosProdutos = produtoRepository.findByRestaurante(restaurante);
		
		return produtoDTOAssembler.toCollectionDTO(todosProdutos);		
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		
		return produtoDTOAssembler.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		Produto produto = produtoInputDTODisassembler.toDomainObject(produtoInputDTO);
		produto.setRestaurante(restaurante);
		
		produto = cadastroProduto.salvar(produto);
		
		return produtoDTOAssembler.toModel(produto);
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
		Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		
		produtoInputDTODisassembler.copyToDomainObject(produtoInputDTO, produtoAtual);
		produtoAtual = cadastroProduto.salvar(produtoAtual);
		return produtoDTOAssembler.toModel(produtoAtual);
	}

}
