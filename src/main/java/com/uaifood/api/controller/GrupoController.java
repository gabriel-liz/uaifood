package com.uaifood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.assembler.GrupoDTOAssembler;
import com.uaifood.api.assembler.GrupoInputDTODisassembler;
import com.uaifood.api.model.GrupoDTO;
import com.uaifood.api.model.input.GrupoInputDTO;
import com.uaifood.domain.model.Grupo;
import com.uaifood.domain.repository.GrupoRepository;
import com.uaifood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	private GrupoInputDTODisassembler grupoInputDTODisassembler;
	
	@GetMapping
	public List<GrupoDTO> listar(){
		List<Grupo> todosGrupos = grupoRepository.findAll();
		
		return grupoDTOAssembler.toCollectionModel(todosGrupos);
	}
	
	@GetMapping("/{grupoId}")
	public GrupoDTO buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		return grupoDTOAssembler.toModel(grupo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoDTO adicionar(@RequestBody @Valid GrupoInputDTO grupoInputDTO) {
		Grupo grupo = grupoInputDTODisassembler.toDomainObject(grupoInputDTO);
		grupo = cadastroGrupo.salvar(grupo);
		
		return grupoDTOAssembler.toModel(grupo);
	}
	
	@PutMapping("/{grupoId}")
	public GrupoDTO atualizar(@PathVariable Long grupoId,
			@RequestBody @Valid GrupoInputDTO grupoInputDTO) {
		
		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
		
		grupoInputDTODisassembler.copyToDomainObject(grupoInputDTO, grupoAtual);
		
		grupoAtual = cadastroGrupo.salvar(grupoAtual);
		
		return grupoDTOAssembler.toModel(grupoAtual);				
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupo.equals(grupoId);
	}
	

}
