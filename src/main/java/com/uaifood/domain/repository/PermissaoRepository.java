package com.uaifood.domain.repository;

import java.util.List;

import com.uaifood.domain.model.Permissao;

public interface PermissaoRepository {
	List<Permissao> todas();
	Permissao porId(Long id);
	Permissao adicionar(Permissao permissao);
	void remover(Permissao permissao);
}
