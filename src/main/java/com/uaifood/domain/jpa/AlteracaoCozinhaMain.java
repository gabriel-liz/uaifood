package com.uaifood.domain.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.uaifood.UaifoodApiApplication;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = 
				new SpringApplicationBuilder(UaifoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("Brasileira");
		cozinhaRepository.salvar(cozinha);
		
	}

}
