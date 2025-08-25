package com.uaifood.domain.repository;

import org.springframework.stereotype.Repository;

import com.uaifood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long>{

}
