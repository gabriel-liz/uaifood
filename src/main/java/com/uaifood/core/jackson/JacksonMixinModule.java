package com.uaifood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.uaifood.api.model.mixin.CidadeMixin;
import com.uaifood.api.model.mixin.CozinhaMixin;
import com.uaifood.api.model.mixin.RestauranteMixin;
import com.uaifood.domain.model.Cidade;
import com.uaifood.domain.model.Cozinha;
import com.uaifood.domain.model.Restaurante;


@Component
public class JacksonMixinModule extends SimpleModule{

	
	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);		
	    setMixInAnnotation(Cidade.class, CidadeMixin.class);
	    setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}
	
	
	

}
