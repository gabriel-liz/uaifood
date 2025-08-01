package com.uaifood.domain.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uaifood.domain.model.Cozinha;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class CadastroCozinha {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
		return query.getResultList();
		//return manager.createQuery("from Cozinha", Cozinha.class)
				//.getResultList();
	}

}
