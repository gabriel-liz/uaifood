package com.uaifood.infrastructure.repository;

import java.util.List;

import com.uaifood.domain.model.Permissao;
import com.uaifood.domain.repository.PermissaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> todas() {
		return manager.createQuery("from Permissao", Permissao.class)
				.getResultList();
	}

	@Override
	public Permissao porId(Long id) {
		return manager.find(Permissao.class, id); 
	}

	@Override
	public Permissao adicionar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Override
	public void remover(Permissao permissao) {
		permissao = porId(permissao.getId());
		manager.remove(permissao);
		
	}

}
