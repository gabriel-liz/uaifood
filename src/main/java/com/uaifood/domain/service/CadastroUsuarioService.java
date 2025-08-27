package com.uaifood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uaifood.domain.exception.NegocioException;
import com.uaifood.domain.exception.UsuarioNaoEncontradoException;
import com.uaifood.domain.model.Grupo;
import com.uaifood.domain.model.Usuario;
import com.uaifood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		//comando abaixo para que o JPA nao sincronize a requisicao com o banco de dados, pois a fazer a query que deveria retornar apenas 1 resultado, ira retornar 2
		usuarioRepository.detach(usuario);
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario) ) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com esse e-mail %s", usuario.getEmail()));
		}		
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		usuario.setSenha(novaSenha);
		
	}	
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	
	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		usuario.removerGrupo(grupo);
	}
	
	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		usuario.adicionarGrupo(grupo);
	}

}
