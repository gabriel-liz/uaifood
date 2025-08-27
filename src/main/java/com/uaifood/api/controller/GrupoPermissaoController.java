package com.uaifood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.assembler.PermissaoDTOAssembler;
import com.uaifood.api.model.PermissaoDTO;
import com.uaifood.domain.model.Grupo;
import com.uaifood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	
	@GetMapping
	public List<PermissaoDTO> listar(@PathVariable Long grupoId){
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		return permissaoDTOAssembler.toCollectionDTO(grupo.getPermissoes());
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);
	}
	
}
