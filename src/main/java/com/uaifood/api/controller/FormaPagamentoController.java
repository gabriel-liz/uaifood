package com.uaifood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.uaifood.api.assembler.FormaPagamentoDTOAssembler;
import com.uaifood.api.assembler.FormaPagamentoInputDTODisassembler;
import com.uaifood.api.model.FormaPagamentoDTO;
import com.uaifood.api.model.input.FormaPagamentoInputDTO;
import com.uaifood.domain.model.FormaPagamento;
import com.uaifood.domain.repository.FormaPagamentoRepository;
import com.uaifood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@Autowired
	private FormaPagamentoInputDTODisassembler formaPagamentoInputDTODisassembler;
	
	@GetMapping
	public List<FormaPagamentoDTO> listar(){
		List<FormaPagamento> todasFormasPagamento = formaPagamentoRepository.findAll(); 
		return formaPagamentoDTOAssembler.toCollectionModel(todasFormasPagamento);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId) {
		
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		return formaPagamentoDTOAssembler.toModel(formaPagamento);		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInputDTO) {
		
		FormaPagamento formaPagamento = formaPagamentoInputDTODisassembler.toDomainObject(formaPagamentoInputDTO);
		formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);
		
		return formaPagamentoDTOAssembler.toModel(formaPagamento);
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInputDTO){
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		formaPagamentoInputDTODisassembler.copyToDomainObject(formaPagamentoInputDTO, formaPagamentoAtual);
		formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);
		
		return formaPagamentoDTOAssembler.toModel(formaPagamentoAtual);
		
	}	
	
	@DeleteMapping("/{formaPagamentoId}")
	public void remover(@PathVariable Long formaPagamentoId){
		cadastroFormaPagamento.equals(formaPagamentoId);
	}	
	
}
