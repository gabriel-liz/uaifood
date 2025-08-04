package com.uaifood.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uaifood.api.model.CozinhasXmlWrapper;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.repository.CozinhaRepository;


@RestController
@RequestMapping(value = "/cozinhas" , produces = MediaType.APPLICATION_JSON_VALUE) 
public class CozinhaController {
	
	
	@Autowired
	private CozinhaRepository cozinhaRepository;	
	
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
		
		//Dessa forma nao retorna o corpo com o resultado
		
		//return ResponseEntity.status(HttpStatus.OK).build();
		//geralmente o response entity dessa forma eh utilizado quando podem haver outras formas de resposta dependendo de um iF
		//return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		
		//Aqui uma forma caso a url/end-point esteja indiposnivel, podemos indicar qual é o que esta sendo usado temporariamente
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://url-temporario.uaifood.com/cozinhas");
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
	
		//Aqui apenas o retorno padrao
		return ResponseEntity.ok(cozinha);
	}
}
