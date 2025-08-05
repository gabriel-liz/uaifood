package com.uaifood.domain.repository;

import java.util.List;

import com.uaifood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Long id);
}
