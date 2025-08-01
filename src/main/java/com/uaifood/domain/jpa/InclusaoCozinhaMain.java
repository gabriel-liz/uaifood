package com.uaifood.domain.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.uaifood.UaifoodApiApplication;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = 
				new SpringApplicationBuilder(UaifoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
				
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japonesa");
		
		cozinhaRepository.salvar(cozinha1);
		cozinhaRepository.salvar(cozinha2);
		
	}

}
