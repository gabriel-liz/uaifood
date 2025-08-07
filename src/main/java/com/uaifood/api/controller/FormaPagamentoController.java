package com.uaifood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.domain.exception.EntidadeEmUsoException;
import com.uaifood.domain.exception.EntidadeNaoEncontradaException;
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
	
	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoRepository.findAll();
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId){
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);
		
		if(formaPagamento.isPresent()){
			return ResponseEntity.ok(formaPagamento.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
		return cadastroFormaPagamento.salvar(formaPagamento);
	}
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento){
		Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);
		
		if(formaPagamentoAtual.isPresent()) {
			BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");
			FormaPagamento formaPagamentoSalva = cadastroFormaPagamento.salvar(formaPagamentoAtual.get());
			return ResponseEntity.ok(formaPagamentoSalva);			
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<?> remover(@PathVariable Long formaPagamentoId){
		try {
			cadastroFormaPagamento.excluir(formaPagamentoId);
			return ResponseEntity.noContent().build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}	
	
}
