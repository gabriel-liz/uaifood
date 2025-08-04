package com.uaifood.api.controller;


import java.util.List;
import com.uaifood.infrastructure.repository.CozinhaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.model.CozinhasXmlWrapper;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.repository.CozinhaRepository;


@RestController
@RequestMapping(value = "/cozinhas" , produces = MediaType.APPLICATION_JSON_VALUE) 
public class CozinhaController {

    private final CozinhaRepositoryImpl cozinhaRepositoryImpl;
	
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

    CozinhaController(CozinhaRepositoryImpl cozinhaRepositoryImpl) {
        this.cozinhaRepositoryImpl = cozinhaRepositoryImpl;
    }	
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.listar();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml(){
		return new CozinhasXmlWrapper(cozinhaRepository.listar());
	}	
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {		
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicioanr(@RequestBody Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
}
