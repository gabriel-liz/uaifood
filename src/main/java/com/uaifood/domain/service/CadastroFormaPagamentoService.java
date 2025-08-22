package com.uaifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.uaifood.domain.exception.EntidadeEmUsoException;
import com.uaifood.domain.exception.EntidadeNaoEncontradaException;
import com.uaifood.domain.model.FormaPagamento;
import com.uaifood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	public void excluir(Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
			formaPagamentoRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de forma de pagamento com código %d", formaPagamentoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Forma de Pagamento de código %d não pode ser removido, pois está em uso", formaPagamentoId));
			
		}
		
	}
}
