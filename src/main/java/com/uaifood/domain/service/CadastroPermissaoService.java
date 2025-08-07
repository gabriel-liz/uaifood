package com.uaifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.uaifood.domain.exception.EntidadeEmUsoException;
import com.uaifood.domain.exception.EntidadeNaoEncontradaException;
import com.uaifood.domain.model.Permissao;
import com.uaifood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	public void excluir(Long permissaoId) {
		try {
			permissaoRepository.deleteById(permissaoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de Permissão com código %d", permissaoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Forma de Pagamento de código %d não pode ser removida, pois está em uso", permissaoId));
		}
		
	}

}
